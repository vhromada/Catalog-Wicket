package cz.vhromada.catalog.web.movies.controllers;

import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating positions.
 *
 * @author Vladimir Hromada
 */
@Component("moviesUpdatePositionsController")
public class MoviesUpdatePositionsController extends ResultController<Void> {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Creates a new instance of MoviesUpdatePositionsController.
     *
     * @param movieFacade facade for movies
     * @throws IllegalArgumentException if facade for movies is null
     */
    @Autowired
    public MoviesUpdatePositionsController(final MovieFacade movieFacade) {
        Assert.notNull(movieFacade, "Facade for movies mustn't be null.");

        this.movieFacade = movieFacade;
    }

    @Override
    public void handle(final Void data) {
        addResults(movieFacade.updatePositions());

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_UPDATE_POSITION;
    }

}
