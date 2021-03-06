package cz.vhromada.catalog.web.movie.controller;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for duplicating movie.
 *
 * @author Vladimir Hromada
 */
@Component("moviesDuplicateController")
public class MoviesDuplicateController extends AbstractResultController<IModel<Movie>> {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Creates a new instance of MoviesDuplicateController.
     *
     * @param movieFacade facade for movies
     * @throws IllegalArgumentException if facade for movies is null
     */
    @Autowired
    public MoviesDuplicateController(final MovieFacade movieFacade) {
        Assert.notNull(movieFacade, "Facade for movies mustn't be null.");

        this.movieFacade = movieFacade;
    }

    @Override
    public void handle(final IModel<Movie> data) {
        addResults(movieFacade.duplicate(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_DUPLICATE;
    }

}
