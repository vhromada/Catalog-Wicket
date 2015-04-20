package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing music.
 *
 * @author Vladimir Hromada
 */
@Component("musicCancelController")
public class MusicCancelController extends Controller<IModel<MusicMO>> {

    @Override
    public void handle(final IModel<MusicMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.MUSIC_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_CANCEL;
    }

}
