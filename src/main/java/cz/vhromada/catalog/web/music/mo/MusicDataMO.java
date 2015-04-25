package cz.vhromada.catalog.web.music.mo;

import java.io.Serializable;
import java.util.Objects;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.MusicTO;

/**
 * A class represents MO for music data.
 *
 * @author Vladimir Hromada
 */
public class MusicDataMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Music
     */
    private MusicTO music;

    /**
     * Count of songs
     */
    private int songsCount;

    /**
     * Total length
     */
    private Time totalLength;

    /**
     * Returns music.
     *
     * @return music
     */
    public MusicTO getMusic() {
        return music;
    }

    /**
     * Sets a new value to music.
     *
     * @param music new value
     */
    public void setMusic(final MusicTO music) {
        this.music = music;
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

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof MusicDataMO)) {
            return false;
        }
        final MusicDataMO musicData = (MusicDataMO) obj;
        return Objects.equals(music, musicData.music);
    }

    @Override
    public int hashCode() {
        return music.hashCode();
    }

    @Override
    public String toString() {
        return String.format("MusicDataMO [music=%s, songsCount=%d, totalLength=%s]", music, songsCount, totalLength);
    }

}
