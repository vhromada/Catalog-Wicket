package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving up show.
 *
 * @author Vladimir Hromada
 */
@Component("showsMoveUpController")
public class ShowsMoveUpController extends Controller<IModel<ShowTO>> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Creates a new instance of ShowsMoveUpController.
     *
     * @param showFacade facade for shows
     * @throws IllegalArgumentException if facade for shows is null
     */
    @Autowired
    public ShowsMoveUpController(final ShowFacade showFacade) {
        Validators.validateArgumentNotNull(showFacade, "Facade for shows");

        this.showFacade = showFacade;
    }

    @Override
    public void handle(final IModel<ShowTO> data) {
        showFacade.moveUp(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_MOVE_UP;
    }

}