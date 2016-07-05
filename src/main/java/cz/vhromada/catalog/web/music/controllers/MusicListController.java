package cz.vhromada.catalog.web.music.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicDataMO;
import cz.vhromada.catalog.web.music.mo.MusicInfoMO;
import cz.vhromada.catalog.web.music.panels.MusicListPanel;
import cz.vhromada.catalog.web.music.panels.MusicMenuPanel;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of music.
 *
 * @author Vladimir Hromada
 */
@Component("musicListController")
public class MusicListController extends Controller<Void> {

    /**
     * Facade for music
     */
    private MusicFacade musicFacade;

    /**
     * Facade for songs
     */
    private SongFacade songFacade;

    /**
     * Creates a new instance of MusicListController.
     *
     * @param musicFacade facade for music
     * @param songFacade  facade for songs
     * @throws IllegalArgumentException if facade for music is null
     *                                  or facade for songs is null
     */
    @Autowired
    public MusicListController(final MusicFacade musicFacade,
            final SongFacade songFacade) {
        Validators.validateArgumentNotNull(musicFacade, "Facade for music");
        Validators.validateArgumentNotNull(songFacade, "Facade for songs");

        this.musicFacade = musicFacade;
        this.songFacade = songFacade;
    }

    @Override
    public void handle(final Void data) {
        final List<MusicDataMO> musicDataList = new ArrayList<>();
        for (final MusicTO music : musicFacade.getMusic()) {
            final MusicDataMO musicData = new MusicDataMO();
            musicData.setMusic(music);
            int count = 0;
            int length = 0;
            for (final SongTO song : songFacade.findSongsByMusic(music)) {
                count++;
                length += song.getLength();
            }
            musicData.setSongsCount(count);
            musicData.setTotalLength(new Time(length));
            musicDataList.add(musicData);
        }

        final MusicInfoMO musicInfo = new MusicInfoMO();
        musicInfo.setMusicData(musicDataList);
        musicInfo.setMediaCount(musicFacade.getTotalMediaCount());
        musicInfo.setSongsCount(musicFacade.getSongsCount());
        musicInfo.setTotalLength(musicFacade.getTotalLength());
        final PanelData<MusicInfoMO> panelData = new PanelData<>(MusicListPanel.ID, Model.of(musicInfo));
        final PanelData<Void> menuData = new PanelData<>(MusicMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Music", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_LIST;
    }

}
