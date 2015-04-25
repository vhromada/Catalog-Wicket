package cz.vhromada.catalog.web.movies.controllers;

import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating positions.
 *
 * @author Vladimir Hromada
 */
@Component("moviesUpdatePositionsController")
public class MoviesUpdatePositionsController extends Controller<Void> {

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
        Validators.validateArgumentNotNull(movieFacade, "Facade for movies");

        this.movieFacade = movieFacade;
    }

    @Override
    public void handle(final Void data) {
        movieFacade.updatePositions();

        getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_UPDATE_POSITION;
    }

}
