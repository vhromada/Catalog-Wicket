package cz.vhromada.catalog.web.song.controller;

import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.song.mo.SongMO;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing song.
 *
 * @author Vladimir Hromada
 */
@Component("songCancelController")
public class SongCancelController extends Controller<IModel<SongMO>> {

    @Override
    public void handle(final IModel<SongMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_CANCEL;
    }

}
