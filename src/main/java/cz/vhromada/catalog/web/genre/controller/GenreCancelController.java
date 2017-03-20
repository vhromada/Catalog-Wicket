package cz.vhromada.catalog.web.genre.controller;

import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

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
