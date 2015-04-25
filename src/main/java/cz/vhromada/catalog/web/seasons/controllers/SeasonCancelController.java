package cz.vhromada.catalog.web.seasons.controllers;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing season.
 *
 * @author Vladimir Hromada
 */
@Component("seasonCancelController")
public class SeasonCancelController extends Controller<IModel<SeasonMO>> {

    @Override
    public void handle(final IModel<SeasonMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_CANCEL;
    }

}
