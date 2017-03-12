package cz.vhromada.catalog.web.episodes.controllers;

import java.util.List;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.episodes.panels.EpisodesListPanel;
import cz.vhromada.catalog.web.episodes.panels.EpisodesMenuPanel;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.controllers.SeasonEpisodesController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of episodes.
 *
 * @author Vladimir Hromada
 */
@Component("episodesListController")
public class EpisodesListController extends ResultController<Void> {

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Creates a new instance of EpisodesListController.
     *
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for episodes is null
     */
    @Autowired
    public EpisodesListController(final EpisodeFacade episodeFacade) {
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");

        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final Void data) {
        final Season season = CatalogApplication.getSessionAttribute(SeasonEpisodesController.SEASON_ATTRIBUTE);
        final Result<List<Episode>> result = episodeFacade.find(season);

        addResults(result);

        if (processResult()) {
            final PanelData<List<Episode>> panelData = new PanelData<>(EpisodesListPanel.ID, Model.ofList(result.getData()));
            final PanelData<Void> menuData = new PanelData<>(EpisodesMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Episodes", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_LIST;
    }

}
