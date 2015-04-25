package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.facade.ShowFacade;
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
@Component("showsNewDataController")
public class ShowsNewDataController extends Controller<Void> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Creates a new instance of ShowsNewDataController.
     *
     * @param showFacade facade for shows
     * @throws IllegalArgumentException if facade for shows is null
     */
    @Autowired
    public ShowsNewDataController(final ShowFacade showFacade) {
        Validators.validateArgumentNotNull(showFacade, "Facade for shows");

        this.showFacade = showFacade;
    }

    @Override
    public void handle(final Void data) {
        showFacade.newData();

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_NEW_DATA;
    }

}
