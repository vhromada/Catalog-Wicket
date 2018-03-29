package cz.vhromada.catalog.web.song.controller;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.song.mo.SongMO;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating song.
 *
 * @author Vladimir Hromada
 */
@Component("updateSongConfirmController")
public class UpdateSongConfirmController extends AbstractResultController<IModel<SongMO>> {

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
    public UpdateSongConfirmController(final SongFacade songFacade, final Converter converter) {
        Assert.notNull(songFacade, "Facade for songs mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.songFacade = songFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<SongMO> data) {
        addResults(songFacade.update(converter.convert(data.getObject(), Song.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SONGS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_UPDATE_CONFIRM;
    }

}
