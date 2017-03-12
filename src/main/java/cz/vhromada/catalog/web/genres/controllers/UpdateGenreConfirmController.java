package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating genre.
 *
 * @author Vladimir Hromada
 */
@Component("updateGenreConfirmController")
public class UpdateGenreConfirmController extends ResultController<IModel<GenreMO>> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateGenreConfirmController.
     *
     * @param genreFacade facade for genres
     * @param converter   converter
     * @throws IllegalArgumentException if facade for genres is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateGenreConfirmController(final GenreFacade genreFacade,
            final Converter converter) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GenreMO> data) {
        addResults(genreFacade.update(converter.convert(data.getObject(), Genre.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GENRES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_UPDATE_CONFIRM;
    }

}
