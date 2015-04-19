package cz.vhromada.catalog.web.pages;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.home.panels.HomePanel;
import cz.vhromada.catalog.web.panels.MenuPanel;
import cz.vhromada.web.wicket.controllers.FlowRunner;
import cz.vhromada.web.wicket.events.PageEvent;
import cz.vhromada.web.wicket.pages.WicketPage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.head.CssHeaderItem;
import org.apache.wicket.markup.head.IHeaderResponse;
import org.apache.wicket.markup.head.JavaScriptReferenceHeaderItem;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.request.resource.CssResourceReference;
import org.apache.wicket.request.resource.JavaScriptResourceReference;

/**
 * A class represents catalog page.
 *
 * @author Vladimir Hromada
 */
public class CatalogPage extends WicketPage {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Label for title
     */
    private Label title;

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

    @Override
    protected void onPageEvent(final PageEvent event) {
        if (event instanceof ControllerEvent) {
            onControllerEvent((ControllerEvent) event);
        } else if (event instanceof PanelEvent) {
            onPanelEvent((PanelEvent) event, getAjaxRequestTarget());
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

