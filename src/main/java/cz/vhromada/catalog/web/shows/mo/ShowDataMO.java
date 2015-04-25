package cz.vhromada.catalog.web.shows.mo;

import java.io.Serializable;
import java.util.Objects;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.ShowTO;

/**
 * A class represents MO for show data.
 *
 * @author Vladimir Hromada
 */
public class ShowDataMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Show
     */
    private ShowTO show;

    /**
     * Count of seasons
     */
    private int seasonsCount;

    /**
     * Count of episodes
     */
    private int episodesCount;

    /**
     * Total length
     */
    private Time totalLength;

    /**
     * Returns show.
     *
     * @return show
     */
    public ShowTO getShow() {
        return show;
    }

    /**
     * Sets a new value to show.
     *
     * @param show new value
     */
    public void setShow(final ShowTO show) {
        this.show = show;
    }

    /**
     * Returns count of seasons.
     *
     * @return count of seasons
     */
    public int getSeasonsCount() {
        return seasonsCount;
    }

    /**
     * Sets a new value to count of seasons.
     *
     * @param seasonsCount new value
     */
    public void setSeasonsCount(final int seasonsCount) {
        this.seasonsCount = seasonsCount;
    }

    /**
     * Returns count of episodes.
     *
     * @return count of episodes
     */
    public int getEpisodesCount() {
        return episodesCount;
    }

    /**
     * Sets a new value to count of episodes.
     *
     * @param episodesCount new value
     */
    public void setEpisodesCount(final int episodesCount) {
        this.episodesCount = episodesCount;
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
        if (obj == null || !(obj instanceof ShowDataMO)) {
            return false;
        }
        final ShowDataMO showData = (ShowDataMO) obj;
        return Objects.equals(show, showData.show);
    }

    @Override
    public int hashCode() {
        return show.hashCode();
    }

    @Override
    public String toString() {
        return String.format("ShowDataMO [show=%s, seasonsCount=%d, episodesCount=%d, totalLength=%s]", show, seasonsCount, episodesCount, totalLength);
    }

}
