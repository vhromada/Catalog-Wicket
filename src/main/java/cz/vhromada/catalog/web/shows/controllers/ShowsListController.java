package cz.vhromada.catalog.web.shows.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.web.commons.ResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.shows.mo.ShowDataMO;
import cz.vhromada.catalog.web.shows.mo.ShowsMO;
import cz.vhromada.catalog.web.shows.panels.ShowsListPanel;
import cz.vhromada.catalog.web.shows.panels.ShowsMenuPanel;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of shows.
 *
 * @author Vladimir Hromada
 */
@Component("showsListController")
public class ShowsListController extends ResultController<Void> {

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
        Assert.notNull(showFacade, "Facade for shows mustn't be null.");
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");

        this.showFacade = showFacade;
        this.seasonFacade = seasonFacade;
        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Show>> moviesResult = showFacade.getAll();
        final Result<Integer> seasonsCountResult = showFacade.getSeasonsCount();
        final Result<Integer> episodesCountResult = showFacade.getEpisodesCount();
        final Result<Time> totalLengthResult = showFacade.getTotalLength();

        addResults(moviesResult, seasonsCountResult, episodesCountResult, totalLengthResult);

        if (isOk()) {
            final List<ShowDataMO> showDataList = new ArrayList<>();
            for (final Show show : showFacade.getAll().getData()) {
                final ShowDataMO showData = new ShowDataMO();
                showData.setShow(show);
                final Data showInfoData = processSeasons(show);
                showData.setSeasonsCount(showInfoData.getSeasonsCount());
                showData.setEpisodesCount(showInfoData.getEpisodesCount());
                showData.setTotalLength(new Time(showInfoData.getTotalLength()));
                showDataList.add(showData);
            }

            final ShowsMO shows = new ShowsMO();
            shows.setShows(showDataList);
            shows.setSeasonsCount(seasonsCountResult.getData());
            shows.setEpisodesCount(episodesCountResult.getData());
            shows.setTotalLength(totalLengthResult.getData());
            final PanelData<ShowsMO> panelData = new PanelData<>(ShowsListPanel.ID, Model.of(shows));
            final PanelData<Void> menuData = new PanelData<>(ShowsMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Shows", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_LIST;
    }

    /**
     * Process seasons.
     *
     * @param show show
     * @return show data
     */
    private Data processSeasons(final Show show) {
        final Data data = new Data();
        final Result<List<Season>> seasonsResult = seasonFacade.find(show);

        addResults(seasonsResult);

        if (isOk()) {
            for (final Season season : seasonsResult.getData()) {
                data.add();

                final Result<List<Episode>> episodesResult = episodeFacade.find(season);

                addResults(episodesResult);

                if (isOk()) {
                    for (final Episode episode : episodeFacade.find(season).getData()) {
                        data.add(episode.getLength());
                    }
                } else {
                    break;
                }
            }
        }

        return data;
    }

    /**
     * A class represents show data.
     */
    private static final class Data {

        /**
         * Count of seasons
         */
        private int seasonsCount;

        /**
         * Count of episodes
         */
        private int episodesCount;

        /**
         * Total length
         */
        private int totalLength;

        /**
         * Returns count of seasons.
         *
         * @return count of seasons
         */
        int getSeasonsCount() {
            return seasonsCount;
        }

        /**
         * Returns count of episodes.
         *
         * @return count of episodes
         */
        int getEpisodesCount() {
            return episodesCount;
        }

        /**
         * Returns total length.
         *
         * @return total length
         */
        int getTotalLength() {
            return totalLength;
        }

        /**
         * Adds data.
         */
        void add() {
            this.seasonsCount++;
        }

        /**
         * Adds data.
         *
         * @param length total length
         */
        void add(final int length) {
            this.episodesCount++;
            this.totalLength += length;
        }

    }

}
