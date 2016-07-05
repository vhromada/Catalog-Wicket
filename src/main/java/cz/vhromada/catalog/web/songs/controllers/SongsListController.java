package cz.vhromada.catalog.web.songs.controllers;

import java.util.List;

import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.controllers.MusicSongsController;
import cz.vhromada.catalog.web.songs.panels.SongsListPanel;
import cz.vhromada.catalog.web.songs.panels.SongsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of songs.
 *
 * @author Vladimir Hromada
 */
@Component("songsListController")
public class SongsListController extends Controller<Void> {

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
        Validators.validateArgumentNotNull(songFacade, "Facade for songs");

        this.songFacade = songFacade;
    }

    @Override
    public void handle(final Void data) {
        final MusicTO music = CatalogApplication.getSessionAttribute(MusicSongsController.MUSIC_ATTRIBUTE);
        final PanelData<List<SongTO>> panelData = new PanelData<>(SongsListPanel.ID, Model.ofList(songFacade.findSongsByMusic(music)));
        final PanelData<Void> menuData = new PanelData<>(SongsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Songs", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_LIST;
    }

}
