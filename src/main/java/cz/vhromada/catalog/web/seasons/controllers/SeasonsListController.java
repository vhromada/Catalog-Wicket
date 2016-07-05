package cz.vhromada.catalog.web.seasons.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.mo.SeasonDataMO;
import cz.vhromada.catalog.web.seasons.panels.SeasonsListPanel;
import cz.vhromada.catalog.web.seasons.panels.SeasonsMenuPanel;
import cz.vhromada.catalog.web.shows.controllers.ShowSeasonsController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for seasoning list of seasons.
 *
 * @author Vladimir Hromada
 */
@Component("seasonsListController")
public class SeasonsListController extends Controller<Void> {

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Creates a new instance of SeasonsListController.
     *
     * @param seasonFacade  facade for seasons
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for seasons is null
     *                                  or facade for episodes is null
     */
    @Autowired
    public SeasonsListController(final SeasonFacade seasonFacade,
            final EpisodeFacade episodeFacade) {
        Validators.validateArgumentNotNull(seasonFacade, "Facade for seasons");
        Validators.validateArgumentNotNull(episodeFacade, "Facade for episodes");

        this.seasonFacade = seasonFacade;
        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final Void data) {
        final ShowTO show = CatalogApplication.getSessionAttribute(ShowSeasonsController.SHOW_ATTRIBUTE);
        final List<SeasonDataMO> seasons = new ArrayList<>();
        for (final SeasonTO season : seasonFacade.findSeasonsByShow(show)) {
            final SeasonDataMO seasonData = new SeasonDataMO();
            seasonData.setSeason(season);
            int episodesCount = 0;
            int length = 0;
            for (final EpisodeTO episode : episodeFacade.findEpisodesBySeason(season)) {
                episodesCount++;
                length += episode.getLength();
            }
            seasonData.setEpisodesCount(episodesCount);
            seasonData.setTotalLength(new Time(length));
            seasons.add(seasonData);
        }

        final PanelData<List<SeasonDataMO>> panelData = new PanelData<>(SeasonsListPanel.ID, Model.ofList(seasons));
        final PanelData<Void> menuData = new PanelData<>(SeasonsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Seasons", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_LIST;
    }

}
