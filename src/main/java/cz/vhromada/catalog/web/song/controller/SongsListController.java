package cz.vhromada.catalog.web.song.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.controller.MusicSongsController;
import cz.vhromada.catalog.web.song.panel.SongsListPanel;
import cz.vhromada.catalog.web.song.panel.SongsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.validation.result.Result;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of songs.
 *
 * @author Vladimir Hromada
 */
@Component("songsListController")
public class SongsListController extends AbstractResultController<Void> {

    /**
     * Facade for songs
     */
    private SongFacade songFacade;

    /**
     * Creates a new instance of SongsListController.
     *
     * @param songFacade facade for songs
     * @throws IllegalArgumentException if facade for songs is null
     */
    @Autowired
    public SongsListController(final SongFacade songFacade) {
        Assert.notNull(songFacade, "Facade for songs mustn't be null.");

        this.songFacade = songFacade;
    }

    @Override
    public void handle(final Void data) {
        final Music music = CatalogApplication.getSessionAttribute(MusicSongsController.MUSIC_ATTRIBUTE);
        final Result<List<Song>> result = songFacade.find(music);

        addResults(result);

        if (processResult()) {
            final PanelData<List<Song>> panelData = new PanelData<>(SongsListPanel.ID, Model.ofList(result.getData()));
            final PanelData<Void> menuData = new PanelData<>(SongsMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Songs", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_LIST;
    }

}
