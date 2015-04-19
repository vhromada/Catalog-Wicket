package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing genre.
 *
 * @author Vladimir Hromada
 */
@Component("genreCancelController")
public class GenreCancelController extends Controller<IModel<GenreMO>> {

    @Override
    public void handle(final IModel<GenreMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.GENRES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_CANCEL;
    }

}
