package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing show.
 *
 * @author Vladimir Hromada
 */
@Component("showCancelController")
public class ShowCancelController extends Controller<IModel<ShowMO>> {

    @Override
    public void handle(final IModel<ShowMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_CANCEL;
    }

}
