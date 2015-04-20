package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for creating new data.
 *
 * @author Vladimir Hromada
 */
@Component("musicNewDataController")
public class MusicNewDataController extends Controller<Void> {

    /**
     * Facade for music
     */
    private MusicFacade musicFacade;

    /**
     * Creates a new instance of MusicNewDataController.
     *
     * @param musicFacade facade for music
     * @throws IllegalArgumentException if facade for music is null
     */
    @Autowired
    public MusicNewDataController(final MusicFacade musicFacade) {
        Validators.validateArgumentNotNull(musicFacade, "Facade for music");

        this.musicFacade = musicFacade;
    }

    @Override
    public void handle(final Void data) {
        musicFacade.newData();

        getUi().fireEvent(new ControllerEvent(CatalogFlow.MUSIC_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_NEW_DATA;
    }

}
