package cz.vhromada.catalog.web.page;

import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.home.panel.HomePanel;
import cz.vhromada.catalog.web.panel.MenuPanel;
import cz.vhromada.web.wicket.controller.FlowRunner;
import cz.vhromada.web.wicket.event.PageEvent;
import cz.vhromada.web.wicket.page.WicketPage;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.EmptyPanel;
import org.apache.wicket.markup.html.panel.Panel;

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
     * ID of content panel
     */
    private static final String CONTENT_ID = "content";

    /**
     * ID of inner menu
     */
    private static final String INNER_MENU_ID = "innerMenu";

    /**
     * Label for title
     */
    private Label title;

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(getPanel(MenuPanel.ID, "menu", null));

        title = new Label("title", "Catalog");
        title.setOutputMarkupId(true);
        add(title);

        add(getPanel(HomePanel.ID, CONTENT_ID, null).setOutputMarkupId(true));
        add(new EmptyPanel(INNER_MENU_ID).setOutputMarkupId(true));
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
    @SuppressWarnings("unchecked")
    private void onPanelEvent(final PanelEvent event, final AjaxRequestTarget target) {
        title.setDefaultModelObject(event.getTitle());

        final PanelData contentPanelData = event.getPanel();
        final Panel contentPanel = getPanel(contentPanelData.getId(), CONTENT_ID, contentPanelData.getData());
        contentPanel.setOutputMarkupId(true);
        addOrReplace(contentPanel);

        final PanelData innerMenuPanelData = event.getMenu();
        final Panel innerMenuPanel;
        if (innerMenuPanelData != null) {
            innerMenuPanel = getPanel(innerMenuPanelData.getId(), INNER_MENU_ID, innerMenuPanelData.getData());
        } else {
            innerMenuPanel = new EmptyPanel(INNER_MENU_ID);
        }
        innerMenuPanel.setOutputMarkupId(true);
        addOrReplace(innerMenuPanel);

        target.add(title, contentPanel, innerMenuPanel);
    }

}
