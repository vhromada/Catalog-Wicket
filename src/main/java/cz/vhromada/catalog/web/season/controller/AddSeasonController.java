package cz.vhromada.catalog.web.season.controller;

import java.util.ArrayList;

import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.catalog.web.season.panel.SeasonFormPanel;
import cz.vhromada.catalog.web.season.panel.SeasonsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for seasoning form for adding season.
 *
 * @author Vladimir Hromada
 */
@Component("addSeasonController")
public class AddSeasonController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SEASONS_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final SeasonMO season = new SeasonMO();
        season.setSubtitles(new ArrayList<>());
        final PanelData<SeasonMO> panelData = new PanelData<>(SeasonFormPanel.ID, new CompoundPropertyModel<>(season));
        final PanelData<Void> menuData = new PanelData<>(SeasonsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Add season", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_ADD;
    }

}
