package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating music.
 *
 * @author Vladimir Hromada
 */
@Component("updateMusicConfirmController")
public class UpdateMusicConfirmController extends AbstractResultController<IModel<MusicMO>> {

    /**
     * Facade for music
     */
    private MusicFacade musicFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateMusicConfirmController.
     *
     * @param musicFacade facade for music
     * @param converter   converter
     * @throws IllegalArgumentException if facade for music is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateMusicConfirmController(final MusicFacade musicFacade,
            final Converter converter) {
        Assert.notNull(musicFacade, "Facade for music mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.musicFacade = musicFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<MusicMO> data) {
        addResults(musicFacade.update(converter.convert(data.getObject(), Music.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.MUSIC_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_UPDATE_CONFIRM;
    }

}
