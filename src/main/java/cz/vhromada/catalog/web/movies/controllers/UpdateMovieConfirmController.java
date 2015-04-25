package cz.vhromada.catalog.web.movies.controllers;

import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.facade.to.MovieTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating movie.
 *
 * @author Vladimir Hromada
 */
@Component("updateMovieConfirmController")
public class UpdateMovieConfirmController extends Controller<IModel<MovieMO>> {

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
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(movieFacade, "Facade for movies");
        Validators.validateArgumentNotNull(converter, "converter");

        this.movieFacade = movieFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<MovieMO> data) {
        movieFacade.update(converter.convert(data.getObject(), MovieTO.class));

        getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_UPDATE_CONFIRM;
    }

}
