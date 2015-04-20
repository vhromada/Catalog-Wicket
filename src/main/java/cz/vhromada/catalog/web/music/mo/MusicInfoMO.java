package cz.vhromada.catalog.web.music.mo;

import java.io.Serializable;
import java.util.List;

import cz.vhromada.catalog.commons.Time;

/**
 * A class represents MO for music info
 *
 * @author Vladimir Hromada
 */
public class MusicInfoMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of music data
     */
    private List<MusicDataMO> musicData;

    /**
     * Count of media
     */
    private int mediaCount;

    /**
     * Count of songs
     */
    private int songsCount;

    /**
     * Total length
     */
    private Time totalLength;

    /**
     * Returns music data.
     *
     * @return music data
     */
    public List<MusicDataMO> getMusicData() {
        return musicData;
    }

    /**
     * Sets a new value to music data.
     *
     * @param musicData new value
     */
    public void setMusicData(final List<MusicDataMO> musicData) {
        this.musicData = musicData;
    }

    /**
     * Returns count of media.
     *
     * @return count of media
     */
    public int getMediaCount() {
        return mediaCount;
    }

    /**
     * Sets a new value to count of media.
     *
     * @param mediaCount new value
     */
    public void setMediaCount(final int mediaCount) {
        this.mediaCount = mediaCount;
    }

    /**
     * Returns count of songs.
     *
     * @return count of songs
     */
    public int getSongsCount() {
        return songsCount;
    }

    /**
     * Sets a new value to count of songs.
     *
     * @param songsCount new value
     */
    public void setSongsCount(final int songsCount) {
        this.songsCount = songsCount;
    }

    /**
     * Returns total length.
     *
     * @return total length
     */
    public Time getTotalLength() {
        return totalLength;
    }

    /**
     * Sets a new value to total length.
     *
     * @param totalLength new value
     */
    public void setTotalLength(final Time totalLength) {
        this.totalLength = totalLength;
    }

}
