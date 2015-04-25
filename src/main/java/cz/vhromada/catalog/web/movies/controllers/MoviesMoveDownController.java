package cz.vhromada.catalog.web.movies.controllers;

import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.facade.to.MovieTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving down movie.
 *
 * @author Vladimir Hromada
 */
@Component("moviesMoveDownController")
public class MoviesMoveDownController extends Controller<IModel<MovieTO>> {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Creates a new instance of MoviesMoveDownController.
     *
     * @param movieFacade facade for movies
     * @throws IllegalArgumentException if facade for movies is null
     */
    @Autowired
    public MoviesMoveDownController(final MovieFacade movieFacade) {
        Validators.validateArgumentNotNull(movieFacade, "Facade for movies");

        this.movieFacade = movieFacade;
    }

    @Override
    public void handle(final IModel<MovieTO> data) {
        movieFacade.moveDown(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_MOVE_DOWN;
    }

}
