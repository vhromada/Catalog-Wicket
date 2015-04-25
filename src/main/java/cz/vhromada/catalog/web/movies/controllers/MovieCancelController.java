package cz.vhromada.catalog.web.movies.controllers;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing movie.
 *
 * @author Vladimir Hromada
 */
@Component("movieCancelController")
public class MovieCancelController extends Controller<IModel<MovieMO>> {

    @Override
    public void handle(final IModel<MovieMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.MOVIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_CANCEL;
    }

}
