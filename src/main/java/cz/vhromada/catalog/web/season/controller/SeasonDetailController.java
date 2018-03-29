package cz.vhromada.catalog.web.season.controller;

import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.season.mo.SeasonDataMO;
import cz.vhromada.catalog.web.season.panel.SeasonDetailPanel;
import cz.vhromada.catalog.web.season.panel.SeasonsMenuPanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of season.
 *
 * @author Vladimir Hromada
 */
@Component("seasonDetailController")
public class SeasonDetailController extends Controller<IModel<SeasonDataMO>> {

    @Override
    public void handle(final IModel<SeasonDataMO> data) {
        final PanelData<SeasonDataMO> panelData = new PanelData<>(SeasonDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(SeasonsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Season detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_DETAIL;
    }

}
