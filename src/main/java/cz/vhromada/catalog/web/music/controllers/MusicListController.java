package cz.vhromada.catalog.web.music.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.facade.SongFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicDataMO;
import cz.vhromada.catalog.web.music.mo.MusicInfoMO;
import cz.vhromada.catalog.web.music.panels.MusicListPanel;
import cz.vhromada.catalog.web.music.panels.MusicMenuPanel;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of music.
 *
 * @author Vladimir Hromada
 */
@Component("musicListController")
public class MusicListController extends ResultController<Void> {

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
        Assert.notNull(musicFacade, "Facade for music mustn't be null.");
        Assert.notNull(songFacade, "Facade for songs mustn't be null.");

        this.musicFacade = musicFacade;
        this.songFacade = songFacade;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Music>> musicResult = musicFacade.getAll();
        final Result<Integer> mediaCountResult = musicFacade.getTotalMediaCount();
        final Result<Integer> songsCountResult = musicFacade.getSongsCount();
        final Result<Time> totalLengthResult = musicFacade.getTotalLength();

        addResults(musicResult, mediaCountResult, songsCountResult, totalLengthResult);

        if (isOk()) {
            final List<MusicDataMO> musicDataList = new ArrayList<>();
            for (final Music music : musicResult.getData()) {
                final MusicDataMO musicData = new MusicDataMO();
                musicData.setMusic(music);
                final Data musicInfoData = processSongs(music);
                musicData.setSongsCount(musicInfoData.getSongsCount());
                musicData.setTotalLength(new Time(musicInfoData.getTotalLength()));
                musicDataList.add(musicData);
            }

            final MusicInfoMO musicInfo = new MusicInfoMO();
            musicInfo.setMusicData(musicDataList);
            musicInfo.setMediaCount(mediaCountResult.getData());
            musicInfo.setSongsCount(songsCountResult.getData());
            musicInfo.setTotalLength(totalLengthResult.getData());
            final PanelData<MusicInfoMO> panelData = new PanelData<>(MusicListPanel.ID, Model.of(musicInfo));
            final PanelData<Void> menuData = new PanelData<>(MusicMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Music", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_LIST;
    }

    /**
     * Process songs.
     *
     * @param music music
     * @return music data
     */
    private Data processSongs(final Music music) {
        final Data data = new Data();
        final Result<List<Song>> result = songFacade.find(music);

        addResults(result);

        if (isOk()) {
            for (final Song song : result.getData()) {
                data.add(song.getLength());
            }
        }

        return data;
    }

    /**
     * A class represents music data.
     */
    private static final class Data {

        /**
         * Count of songs
         */
        private int songsCount;

        /**
         * Total length
         */
        private int totalLength;

        /**
         * Returns count of songs.
         *
         * @return count of songs
         */
        int getSongsCount() {
            return songsCount;
        }

        /**
         * Returns total length.
         *
         * @return total length
         */
        int getTotalLength() {
            return totalLength;
        }

        /**
         * Adds data.
         *
         * @param length total length
         */
        void add(final int length) {
            this.songsCount++;
            this.totalLength += length;
        }

    }

}
