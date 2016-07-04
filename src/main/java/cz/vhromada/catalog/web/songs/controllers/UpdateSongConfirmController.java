package cz.vhromada.catalog.web.songs.controllers;

import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.controllers.MusicSongsController;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating song.
 *
 * @author Vladimir Hromada
 */
@Component("updateSongConfirmController")
public class UpdateSongConfirmController extends Controller<IModel<SongMO>> {

    /**
     * Facade for songs
     */
    private SongFacade songFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateSongConfirmController.
     *
     * @param songFacade facade for songs
     * @param converter  converter
     * @throws IllegalArgumentException if facade for songs is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateSongConfirmController(final SongFacade songFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(songFacade, "Facade for songs");
        Validators.validateArgumentNotNull(converter, "converter");

        this.songFacade = songFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<SongMO> data) {
        final MusicTO music = CatalogApplication.getSessionAttribute(MusicSongsController.MUSIC_ATTRIBUTE);
        final SongTO song = converter.convert(data.getObject(), SongTO.class);
        songFacade.update(song);

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_UPDATE_CONFIRM;
    }

}
