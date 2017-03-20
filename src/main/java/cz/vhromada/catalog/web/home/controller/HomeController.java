package cz.vhromada.catalog.web.home.controller;

import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.home.panel.HomePanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

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
