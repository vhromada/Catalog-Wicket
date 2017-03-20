package cz.vhromada.catalog.web.song.controller;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.controller.MusicSongsController;
import cz.vhromada.catalog.web.song.mo.SongMO;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding song.
 *
 * @author Vladimir Hromada
 */
@Component("addSongConfirmController")
public class AddSongConfirmController extends AbstractResultController<IModel<SongMO>> {

    /**
     * Facade for songs
     */
    private SongFacade songFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddSongConfirmController.
     *
     * @param songFacade facade for songs
     * @param converter  converter
     * @throws IllegalArgumentException if facade for songs is null
     *                                  or converter is null
     */
    @Autowired
    public AddSongConfirmController(final SongFacade songFacade,
            final Converter converter) {
        Assert.notNull(songFacade, "Facade for songs mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.songFacade = songFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<SongMO> data) {
        final Music music = CatalogApplication.getSessionAttribute(MusicSongsController.MUSIC_ATTRIBUTE);
        final Song song = converter.convert(data.getObject(), Song.class);

        addResults(songFacade.add(music, song));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_ADD_CONFIRM;
    }

}
