package cz.vhromada.catalog.web.songs.controllers;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for duplicating song.
 *
 * @author Vladimir Hromada
 */
@Component("songsDuplicateController")
public class SongsDuplicateController extends ResultController<IModel<Song>> {

    /**
     * Facade for songs
     */
    private SongFacade songFacade;

    /**
     * Creates a new instance of SongsDuplicateController.
     *
     * @param songFacade facade for songs
     * @throws IllegalArgumentException if facade for songs is null
     */
    @Autowired
    public SongsDuplicateController(final SongFacade songFacade) {
        Assert.notNull(songFacade, "Facade for songs mustn't be null.");

        this.songFacade = songFacade;
    }

    @Override
    public void handle(final IModel<Song> data) {
        addResults(songFacade.duplicate(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_DUPLICATE;
    }

}
