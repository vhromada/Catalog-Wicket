package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.web.commons.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for moving down show.
 *
 * @author Vladimir Hromada
 */
@Component("showsMoveDownController")
public class ShowsMoveDownController extends ResultController<IModel<Show>> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Creates a new instance of ShowsMoveDownController.
     *
     * @param showFacade facade for shows
     * @throws IllegalArgumentException if facade for shows is null
     */
    @Autowired
    public ShowsMoveDownController(final ShowFacade showFacade) {
        Assert.notNull(showFacade, "Facade for shows mustn't be null.");

        this.showFacade = showFacade;
    }

    @Override
    public void handle(final IModel<Show> data) {
        addResults(showFacade.moveDown(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_MOVE_DOWN;
    }

}
