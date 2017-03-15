package cz.vhromada.catalog.web.seasons.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.mo.SeasonDataMO;
import cz.vhromada.catalog.web.seasons.panels.SeasonsListPanel;
import cz.vhromada.catalog.web.seasons.panels.SeasonsMenuPanel;
import cz.vhromada.catalog.web.shows.controllers.ShowSeasonsController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for seasoning list of seasons.
 *
 * @author Vladimir Hromada
 */
@Component("seasonsListController")
public class SeasonsListController extends AbstractResultController<Void> {

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
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");

        this.seasonFacade = seasonFacade;
        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final Void data) {
        final Show show = CatalogApplication.getSessionAttribute(ShowSeasonsController.SHOW_ATTRIBUTE);
        final Result<List<Season>> result = seasonFacade.find(show);

        addResults(result);

        final List<SeasonDataMO> seasons = new ArrayList<>();
        if (isOk()) {
            for (final Season season : result.getData()) {
                final SeasonDataMO seasonData = new SeasonDataMO();
                seasonData.setSeason(season);
                final Data episodesData = processEpisodes(season);
                seasonData.setEpisodesCount(episodesData.getEpisodesCount());
                seasonData.setTotalLength(new Time(episodesData.getTotalLength()));
                seasons.add(seasonData);
            }
        }

        if (processResult()) {
            final PanelData<List<SeasonDataMO>> panelData = new PanelData<>(SeasonsListPanel.ID, Model.ofList(seasons));
            final PanelData<Void> menuData = new PanelData<>(SeasonsMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Seasons", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_LIST;
    }

    /**
     * Process episodes.
     *
     * @param season season
     * @return season data
     */
    private Data processEpisodes(final Season season) {
        final Data data = new Data();
        final Result<List<Episode>> result = episodeFacade.find(season);

        addResults(result);

        if (isOk()) {
            for (final Episode episode : result.getData()) {
                data.add(episode.getLength());
            }
        }

        return data;
    }

    /**
     * A class represents season data.
     */
    private static final class Data {

        /**
         * Count of episodes
         */
        private int episodesCount;

        /**
         * Total length
         */
        private int totalLength;

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
         *
         * @param length total length
         */
        void add(final int length) {
            this.episodesCount++;
            this.totalLength += length;
        }

    }

}
