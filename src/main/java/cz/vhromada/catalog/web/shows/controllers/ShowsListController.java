package cz.vhromada.catalog.web.shows.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.shows.mo.ShowDataMO;
import cz.vhromada.catalog.web.shows.mo.ShowsMO;
import cz.vhromada.catalog.web.shows.panels.ShowsListPanel;
import cz.vhromada.catalog.web.shows.panels.ShowsMenuPanel;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of shows.
 *
 * @author Vladimir Hromada
 */
@Component("showsListController")
public class ShowsListController extends Controller<Void> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Creates a new instance of ShowsListController.
     *
     * @param showFacade    facade for shows
     * @param seasonFacade  facade for seasons
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for shows is null
     *                                  or facade for seasons is null
     *                                  or facade for episodes is null
     */
    @Autowired
    public ShowsListController(final ShowFacade showFacade,
            final SeasonFacade seasonFacade,
            final EpisodeFacade episodeFacade) {
        Validators.validateArgumentNotNull(showFacade, "Facade for shows");
        Validators.validateArgumentNotNull(seasonFacade, "Facade for seasons");
        Validators.validateArgumentNotNull(episodeFacade, "Facade for episodes");

        this.showFacade = showFacade;
        this.seasonFacade = seasonFacade;
        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final Void data) {
        final List<ShowDataMO> showDataList = new ArrayList<>();
        for (final ShowTO show : showFacade.getShows()) {
            final ShowDataMO showData = new ShowDataMO();
            showData.setShow(show);
            int seasonsCount = 0;
            int episodesCount = 0;
            int length = 0;
            for (final SeasonTO season : seasonFacade.findSeasonsByShow(show)) {
                seasonsCount++;
                for (final EpisodeTO episode : episodeFacade.findEpisodesBySeason(season)) {
                    episodesCount++;
                    length += episode.getLength();
                }
            }
            showData.setSeasonsCount(seasonsCount);
            showData.setEpisodesCount(episodesCount);
            showData.setTotalLength(new Time(length));
            showDataList.add(showData);
        }

        final ShowsMO shows = new ShowsMO();
        shows.setShows(showDataList);
        shows.setSeasonsCount(showFacade.getSeasonsCount());
        shows.setEpisodesCount(showFacade.getEpisodesCount());
        shows.setTotalLength(showFacade.getTotalLength());
        final PanelData panelData = new PanelData(ShowsListPanel.ID, Model.of(shows));
        final PanelData menuData = new PanelData(ShowsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Shows", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_LIST;
    }

}
