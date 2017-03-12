package cz.vhromada.catalog.web.movies.controllers;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.web.commons.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for removing movie.
 *
 * @author Vladimir Hromada
 */
@Component("moviesRemoveController")
public class MoviesRemoveController extends ResultController<IModel<Movie>> {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Creates a new instance of MoviesRemoveController.
     *
     * @param movieFacade facade for movies
     * @throws IllegalArgumentException if facade for movies is null
     */
    @Autowired
    public MoviesRemoveController(final MovieFacade movieFacade) {
        Assert.notNull(movieFacade, "Facade for movies mustn't be null.");

        this.movieFacade = movieFacade;
    }

    @Override
    public void handle(final IModel<Movie> data) {
        addResults(movieFacade.remove(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_REMOVE;
    }

}
