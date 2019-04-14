package cz.vhromada.catalog.web.episode.controller;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.web.episode.mo.EpisodeMO;
import cz.vhromada.catalog.web.episode.panel.EpisodeFormPanel;
import cz.vhromada.catalog.web.episode.panel.EpisodesMenuPanel;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.EpisodeMapper;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
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
 * A class represents controller for showing form for updating episode.
 *
 * @author Vladimir Hromada
 */
@Component("updateEpisodeController")
public class UpdateEpisodeController extends Controller<IModel<Episode>> {

    /**
     * Mapper for episodes
     */
    private final EpisodeMapper episodeMapper;

    /**
     * Creates a new instance of UpdateEpisodeController.
     */
    @Autowired
    public UpdateEpisodeController() {
        this.episodeMapper = Mappers.getMapper(EpisodeMapper.class);
    }

    @Override
    public void handle(final IModel<Episode> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.EPISODES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<EpisodeMO> panelData = new PanelData<>(EpisodeFormPanel.ID, new CompoundPropertyModel<>(episodeMapper.map(data.getObject())));
        final PanelData<Void> menuData = new PanelData<>(EpisodesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit episode", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_UPDATE;
    }

}
