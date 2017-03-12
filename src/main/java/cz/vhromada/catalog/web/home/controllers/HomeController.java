package cz.vhromada.catalog.web.home.controllers;

import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.home.panels.HomePanel;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

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
        final PanelData<Void> panelData = new PanelData<>(HomePanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Catalog"));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.HOME;
    }

}
