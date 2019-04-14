package cz.vhromada.catalog.web.season.controller;

import java.util.ArrayList;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.SeasonMapper;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.catalog.web.season.panel.SeasonFormPanel;
import cz.vhromada.catalog.web.season.panel.SeasonsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for seasoning form for updating season.
 *
 * @author Vladimir Hromada
 */
@Component("updateSeasonController")
public class UpdateSeasonController extends Controller<IModel<Season>> {

    /**
     * Mapper for seasons
     */
    private final SeasonMapper seasonMapper;

    /**
     * Creates a new instance of UpdateSeasonController.
     */
    @Autowired
    public UpdateSeasonController() {
        this.seasonMapper = Mappers.getMapper(SeasonMapper.class);
    }

    @Override
    public void handle(final IModel<Season> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SEASONS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final SeasonMO season = seasonMapper.map(data.getObject());
        if (season.getSubtitles() == null) {
            season.setSubtitles(new ArrayList<>());
        }
        final PanelData<SeasonMO> panelData = new PanelData<>(SeasonFormPanel.ID, new CompoundPropertyModel<>(season));
        final PanelData<Void> menuData = new PanelData<>(SeasonsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit season", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_UPDATE;
    }

}
