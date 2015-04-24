package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing music songs.
 *
 * @author Vladimir Hromada
 */
@Component("musicSongsController")
public class MusicSongsController extends Controller<IModel<MusicTO>> {

    /**
     * Name of music attribute in session
     */
    public static final String MUSIC_ATTRIBUTE = "music";

    @Override
    public void handle(final IModel<MusicTO> data) {
        final CatalogSession session = CatalogSession.getSession();
        session.setAttribute(MUSIC_ATTRIBUTE, data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_SONGS;
    }

}
