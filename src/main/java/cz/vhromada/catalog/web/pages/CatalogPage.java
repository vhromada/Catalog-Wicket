package cz.vhromada.catalog.web.pages;

import java.util.List;

import cz.vhromada.catalog.web.ComponentProvider;
import cz.vhromada.catalog.web.EmptyAjaxRequestTarget;
import cz.vhromada.catalog.web.controllers.FlowRunner;
import cz.vhromada.catalog.web.controllers.FrontController;
import cz.vhromada.catalog.web.controllers.FrontControllerRequest;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.events.PageEvent;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.home.panels.HomePanel;
import cz.vhromada.catalog.web.panels.BasePanel;
import cz.vhromada.catalog.web.panels.MenuPanel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.event.Broadcast;
import org.apache.wicket.event.IEvent;
import org.apache.wicket.injection.Injector;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.request.cycle.RequestCycle;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.spring.injection.annot.SpringBean;

/**
 * A class represents catalog page.
 *
 * @author Vladimir Hromada
 */
public class CatalogPage extends WebPage {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Provider for components
     */
    @SpringBean
    @SuppressWarnings("unused")
    private ComponentProvider componentProvider;

    /**
     * Front controller
     */
    @SpringBean
    @SuppressWarnings("unused")
    private FrontController frontController;

    /**
     * Label for title
     */
    private Label title;

    /**
     * Creates a new instance of CatalogPage.
     */
    public CatalogPage() {
        Injector.get().inject(this);
    }

    @Override
    public void onEvent(final IEvent<?> event) {
        super.onEvent(event);

        final Object payload = event.getPayload();

        if (payload instanceof FrontControllerRequest<?>) {
            final List<PageEvent> pageEvents = frontController.dispatch((FrontControllerRequest<?>) payload);
            for (final PageEvent pageEvent : pageEvents) {
                send(this, Broadcast.BREADTH, pageEvent);
            }
        } else if (payload instanceof ControllerEvent) {
            onControllerEvent((ControllerEvent) payload);
        } else if (payload instanceof PanelEvent) {
            onPanelEvent((PanelEvent) payload, getAjaxRequestTarget());
        }
    }

    @Override
    public void renderHead(final IHeaderResponse response) {
        super.renderHead(response);

        response.render(JavaScriptReferenceHeaderItem.forReference(new JavaScriptResourceReference(getClass(), "js/catalog.js")));
        response.render(CssHeaderItem.forReference(new CssResourceReference(getClass(), "css/catalog.css")));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new MenuPanel("menu", null));

        title = new Label("title", "Catalog");
        title.setOutputMarkupId(true);
        add(title);

        add(getPanel(HomePanel.ID, "content", null).setOutputMarkupId(true));
        add(new EmptyPanel("innerMenu").setOutputMarkupId(true));
    }

    /**
     * Returns panel.
     *
     * @param id       Spring ID of panel
     * @param wicketId Wicket ID of panel
     * @param model    model of panel
     * @return panel
     */
    private BasePanel getPanel(final String id, final String wicketId, final IModel<?> model) {
        return componentProvider.getPanel(id, wicketId, model);
    }

    /**
     * Returns AJAX request target.
     *
     * @return AJAX request target
     */
    private static AjaxRequestTarget getAjaxRequestTarget() {
        final AjaxRequestTarget target = RequestCycle.get().find(AjaxRequestTarget.class);
        if (target != null) {
            return target;
        } else {
            return new EmptyAjaxRequestTarget();
        }
    }

    /**
     * Process controller event.
     *
     * @param event controller event
     */
    private void onControllerEvent(final ControllerEvent event) {
        new FlowRunner(this, event.getFlow(), event.getData());
    }

    /**
     * Process panel event.
     *
     * @param event  panel event
     * @param target AJAX request target
     */
    private void onPanelEvent(final PanelEvent event, final AjaxRequestTarget target) {
        title.setDefaultModelObject(event.getTitle());

        final PanelData contentPanelData = event.getPanel();
        final Panel contentPanel = getPanel(contentPanelData.getId(), "content", contentPanelData.getData());
        contentPanel.setOutputMarkupId(true);
        addOrReplace(contentPanel);

        final PanelData innerMenuPanelData = event.getMenu();
        final Panel innerMenuPanel;
        if (innerMenuPanelData != null) {
            innerMenuPanel = getPanel(innerMenuPanelData.getId(), "innerMenu", innerMenuPanelData.getData());
        } else {
            innerMenuPanel = new EmptyPanel("innerMenu");
        }
        innerMenuPanel.setOutputMarkupId(true);
        addOrReplace(innerMenuPanel);

        target.add(title, contentPanel, innerMenuPanel);
    }

}

