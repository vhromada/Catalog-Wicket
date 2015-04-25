package cz.vhromada.catalog.web.songs.controllers;

import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving up song.
 *
 * @author Vladimir Hromada
 */
@Component("songsMoveUpController")
public class SongsMoveUpController extends Controller<IModel<SongTO>> {

    /**
     * Facade for songs
     */
    private SongFacade songFacade;

    /**
     * Creates a new instance of SongsMoveUpController.
     *
     * @param songFacade facade for songs
     * @throws IllegalArgumentException if facade for songs is null
     */
    @Autowired
    public SongsMoveUpController(final SongFacade songFacade) {
        Validators.validateArgumentNotNull(songFacade, "Facade for songs");

        this.songFacade = songFacade;
    }

    @Override
    public void handle(final IModel<SongTO> data) {
        songFacade.moveUp(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_MOVE_UP;
    }

}