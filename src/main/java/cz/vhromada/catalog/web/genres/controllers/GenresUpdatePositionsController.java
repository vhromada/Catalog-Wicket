package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating positions.
 *
 * @author Vladimir Hromada
 */
@Component("genresUpdatePositionsController")
public class GenresUpdatePositionsController extends ResultController<Void> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Creates a new instance of GenresUpdatePositionsController.
     *
     * @param genreFacade facade for genres
     * @throws IllegalArgumentException if facade for genres is null
     */
    @Autowired
    public GenresUpdatePositionsController(final GenreFacade genreFacade) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");

        this.genreFacade = genreFacade;
    }

    @Override
    public void handle(final Void data) {
        addResults(genreFacade.updatePositions());

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GENRES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_UPDATE_POSITION;
    }

}
