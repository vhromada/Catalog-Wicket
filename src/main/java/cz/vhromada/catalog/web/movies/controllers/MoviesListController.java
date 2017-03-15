package cz.vhromada.catalog.web.movies.controllers;

import java.util.List;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movies.mo.MoviesMO;
import cz.vhromada.catalog.web.movies.panels.MoviesListPanel;
import cz.vhromada.catalog.web.movies.panels.MoviesMenuPanel;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of movies.
 *
 * @author Vladimir Hromada
 */
@Component("moviesListController")
public class MoviesListController extends AbstractResultController<Void> {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Creates a new instance of MoviesListController.
     *
     * @param movieFacade facade for movies
     * @throws IllegalArgumentException if facade for movies is null
     */
    @Autowired
    public MoviesListController(final MovieFacade movieFacade) {
        Assert.notNull(movieFacade, "Facade for movies mustn't be null.");

        this.movieFacade = movieFacade;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Movie>> moviesResult = movieFacade.getAll();
        final Result<Integer> mediaCountResult = movieFacade.getTotalMediaCount();
        final Result<Time> totalLengthResult = movieFacade.getTotalLength();

        addResults(moviesResult, mediaCountResult, totalLengthResult);

        if (processResult()) {
            final MoviesMO movies = new MoviesMO();
            movies.setMovies(moviesResult.getData());
            movies.setMediaCount(mediaCountResult.getData());
            movies.setTotalLength(totalLengthResult.getData());
            final PanelData<MoviesMO> panelData = new PanelData<>(MoviesListPanel.ID, Model.of(movies));
            final PanelData<Void> menuData = new PanelData<>(MoviesMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Movies", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_LIST;
    }

}
