package cz.vhromada.catalog.web.episodes.controllers;

import java.util.List;

import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.web.episodes.panels.EpisodesListPanel;
import cz.vhromada.catalog.web.episodes.panels.EpisodesMenuPanel;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.controllers.SeasonEpisodesController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of episodes.
 *
 * @author Vladimir Hromada
 */
@Component("episodesListController")
public class EpisodesListController extends Controller<Void> {

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
        Validators.validateArgumentNotNull(episodeFacade, "Facade for episodes");

        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final Void data) {
        final SeasonTO season = CatalogApplication.getSessionAttribute(SeasonEpisodesController.SEASON_ATTRIBUTE);
        final PanelData<List<EpisodeTO>> panelData = new PanelData<>(EpisodesListPanel.ID, Model.ofList(episodeFacade.findEpisodesBySeason(season)));
        final PanelData<Void> menuData = new PanelData<>(EpisodesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Episodes", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_LIST;
    }

}
