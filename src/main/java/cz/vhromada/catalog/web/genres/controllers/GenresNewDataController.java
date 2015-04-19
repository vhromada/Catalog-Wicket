package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for creating new data.
 *
 * @author Vladimir Hromada
 */
@Component("genresNewDataController")
public class GenresNewDataController extends Controller<Void> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Creates a new instance of GenresNewDataController.
     *
     * @param genreFacade facade for genres
     * @throws IllegalArgumentException if facade for genres is null
     */
    @Autowired
    public GenresNewDataController(final GenreFacade genreFacade) {
        Validators.validateArgumentNotNull(genreFacade, "Facade for genres");

        this.genreFacade = genreFacade;
    }

    @Override
    public void handle(final Void data) {
        genreFacade.newData();

        getUi().fireEvent(new ControllerEvent(CatalogFlow.GENRES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_NEW_DATA;
    }

}
