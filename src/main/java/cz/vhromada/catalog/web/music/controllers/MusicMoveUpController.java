package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.web.commons.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for moving up music.
 *
 * @author Vladimir Hromada
 */
@Component("musicMoveUpController")
public class MusicMoveUpController extends ResultController<IModel<Music>> {

    /**
     * Facade for music
     */
    private MusicFacade musicFacade;

    /**
     * Creates a new instance of MusicMoveUpController.
     *
     * @param musicFacade facade for music
     * @throws IllegalArgumentException if facade for music is null
     */
    @Autowired
    public MusicMoveUpController(final MusicFacade musicFacade) {
        Assert.notNull(musicFacade, "Facade for music mustn't be null.");

        this.musicFacade = musicFacade;
    }

    @Override
    public void handle(final IModel<Music> data) {
        addResults(musicFacade.moveUp(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.MUSIC_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_MOVE_UP;
    }

}
