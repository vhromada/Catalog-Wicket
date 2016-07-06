package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving down genre.
 *
 * @author Vladimir Hromada
 */
@Component("genresMoveDownController")
public class GenresMoveDownController extends Controller<IModel<GenreTO>> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Creates a new instance of GenresMoveDownController.
     *
     * @param genreFacade facade for genres
     * @throws IllegalArgumentException if facade for genres is null
     */
    @Autowired
    public GenresMoveDownController(final GenreFacade genreFacade) {
        Validators.validateArgumentNotNull(genreFacade, "Facade for genres");

        this.genreFacade = genreFacade;
    }

    @Override
    public void handle(final IModel<GenreTO> data) {
        genreFacade.moveDown(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.GENRES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_MOVE_DOWN;
    }

}