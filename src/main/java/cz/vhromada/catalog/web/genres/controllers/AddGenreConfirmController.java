package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for adding genre.
 *
 * @author Vladimir Hromada
 */
@Component("addGenreConfirmController")
public class AddGenreConfirmController extends Controller<IModel<GenreMO>> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddGenreConfirmController.
     *
     * @param genreFacade facade for genres
     * @param converter   converter
     * @throws IllegalArgumentException if facade for genres is null
     *                                  or converter is null
     */
    @Autowired
    public AddGenreConfirmController(final GenreFacade genreFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(genreFacade, "Facade for genres");
        Validators.validateArgumentNotNull(converter, "converter");

        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GenreMO> data) {
        genreFacade.add(converter.convert(data.getObject(), GenreTO.class));

        getUi().fireEvent(new ControllerEvent(CatalogFlow.GENRES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_ADD_CONFIRM;
    }

}
