package cz.vhromada.catalog.web.shows.mo;

import java.io.Serializable;
import java.util.List;

import cz.vhromada.catalog.commons.Time;

/**
 * A class represents MO for shows.
 *
 * @author Vladimir Hromada
 */
public class ShowsMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of shows
     */
    private List<ShowDataMO> shows;

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
     * Returns list of shows.
     *
     * @return list of shows
     */
    public List<ShowDataMO> getShows() {
        return shows;
    }

    /**
     * Sets a new value to list of shows.
     *
     * @param shows new value
     */
    public void setShows(final List<ShowDataMO> shows) {
        this.shows = shows;
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

}
