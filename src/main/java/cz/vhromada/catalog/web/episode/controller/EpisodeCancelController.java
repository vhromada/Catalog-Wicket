package cz.vhromada.catalog.web.episode.controller;

import cz.vhromada.catalog.web.episode.mo.EpisodeMO;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing episode.
 *
 * @author Vladimir Hromada
 */
@Component("episodeCancelController")
public class EpisodeCancelController extends Controller<IModel<EpisodeMO>> {

    @Override
    public void handle(final IModel<EpisodeMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_CANCEL;
    }

}
