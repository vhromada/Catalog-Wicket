package cz.vhromada.catalog.web.show.controller;

import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.show.mo.ShowDataMO;
import cz.vhromada.catalog.web.show.panel.ShowDetailPanel;
import cz.vhromada.catalog.web.show.panel.ShowsMenuPanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of show.
 *
 * @author Vladimir Hromada
 */
@Component("showDetailController")
public class ShowDetailController extends Controller<IModel<ShowDataMO>> {

    @Override
    public void handle(final IModel<ShowDataMO> data) {
        final PanelData<ShowDataMO> panelData = new PanelData<>(ShowDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(ShowsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Show detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_DETAIL;
    }

}
