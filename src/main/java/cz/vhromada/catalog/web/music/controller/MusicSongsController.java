package cz.vhromada.catalog.web.music.controller;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing music songs.
 *
 * @author Vladimir Hromada
 */
@Component("musicSongsController")
public class MusicSongsController extends Controller<IModel<Music>> {

    /**
     * Name of music attribute in session
     */
    public static final String MUSIC_ATTRIBUTE = "music";

    @Override
    public void handle(final IModel<Music> data) {
        final WebSession session = WebSession.get();
        session.setAttribute(MUSIC_ATTRIBUTE, data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_SONGS;
    }

}
