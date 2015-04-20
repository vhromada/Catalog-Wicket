package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for duplicating music.
 *
 * @author Vladimir Hromada
 */
@Component("musicDuplicateController")
public class MusicDuplicateController extends Controller<IModel<MusicTO>> {

    /**
     * Facade for music
     */
    private MusicFacade musicFacade;

    /**
     * Creates a new instance of MusicDuplicateController.
     *
     * @param musicFacade facade for music
     * @throws IllegalArgumentException if facade for music is null
     */
    @Autowired
    public MusicDuplicateController(final MusicFacade musicFacade) {
        Validators.validateArgumentNotNull(musicFacade, "Facade for music");

        this.musicFacade = musicFacade;
    }

    @Override
    public void handle(final IModel<MusicTO> data) {
        musicFacade.duplicate(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.MUSIC_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_DUPLICATE;
    }

}
