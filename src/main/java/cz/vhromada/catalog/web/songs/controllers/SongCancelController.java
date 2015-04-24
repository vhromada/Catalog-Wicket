package cz.vhromada.catalog.web.songs.controllers;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

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
