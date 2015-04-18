package cz.vhromada.catalog.web.home.controllers;

import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.PageEvent;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.home.panels.HomePanel;

import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing home page.
 *
 * @author Vladimir Hromada
 */
@Component("homeController")
public class HomeController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final PanelData panelData = new PanelData(HomePanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Catalog");

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return Flow.HOME;
    }

}
