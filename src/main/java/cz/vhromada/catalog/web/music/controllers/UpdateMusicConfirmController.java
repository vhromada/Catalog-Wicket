package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating music.
 *
 * @author Vladimir Hromada
 */
@Component("updateMusicConfirmController")
public class UpdateMusicConfirmController extends Controller<IModel<MusicMO>> {

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
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(musicFacade, "Facade for music");
        Validators.validateArgumentNotNull(converter, "converter");

        this.musicFacade = musicFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<MusicMO> data) {
        musicFacade.update(converter.convert(data.getObject(), MusicTO.class));

        getUi().fireEvent(new ControllerEvent(CatalogFlow.MUSIC_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_UPDATE_CONFIRM;
    }

}
