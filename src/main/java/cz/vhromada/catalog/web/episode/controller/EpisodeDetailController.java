package cz.vhromada.catalog.web.episode.controller;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.web.episode.panel.EpisodeDetailPanel;
import cz.vhromada.catalog.web.episode.panel.EpisodesMenuPanel;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of episode.
 *
 * @author Vladimir Hromada
 */
@Component("episodeDetailController")
public class EpisodeDetailController extends Controller<IModel<Episode>> {

    @Override
    public void handle(final IModel<Episode> data) {
        final PanelData<Episode> panelData = new PanelData<>(EpisodeDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(EpisodesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Episode detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_DETAIL;
    }

}
