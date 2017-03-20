package cz.vhromada.catalog.web.movie.controller;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movie.mo.MovieMO;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating movie.
 *
 * @author Vladimir Hromada
 */
@Component("updateMovieConfirmController")
public class UpdateMovieConfirmController extends AbstractResultController<IModel<MovieMO>> {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateMovieConfirmController.
     *
     * @param movieFacade facade for movies
     * @param converter   converter
     * @throws IllegalArgumentException if facade for movies is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateMovieConfirmController(final MovieFacade movieFacade,
            final Converter converter) {
        Assert.notNull(movieFacade, "Facade for movies mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.movieFacade = movieFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<MovieMO> data) {
        addResults(movieFacade.update(converter.convert(data.getObject(), Movie.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_UPDATE_CONFIRM;
    }

}