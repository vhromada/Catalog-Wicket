package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.web.commons.ResultController;
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
@Component("showsUpdatePositionsController")
public class ShowsUpdatePositionsController extends ResultController<Void> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Creates a new instance of ShowsUpdatePositionsController.
     *
     * @param showFacade facade for shows
     * @throws IllegalArgumentException if facade for shows is null
     */
    @Autowired
    public ShowsUpdatePositionsController(final ShowFacade showFacade) {
        Assert.notNull(showFacade, "Facade for shows mustn't be null.");

        this.showFacade = showFacade;
    }

    @Override
    public void handle(final Void data) {
        addResults(showFacade.updatePositions());

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_UPDATE_POSITION;
    }

}
