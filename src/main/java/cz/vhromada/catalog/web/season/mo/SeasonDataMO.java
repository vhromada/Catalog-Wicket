package cz.vhromada.catalog.web.season.mo;

import java.io.Serializable;
import java.util.Objects;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.entity.Season;

/**
 * A class represents MO for season data.
 *
 * @author Vladimir Hromada
 */
public class SeasonDataMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Season
     */
    private Season season;

    /**
     * Count of episodes
     */
    private int episodesCount;

    /**
     * Total length
     */
    private Time totalLength;

    /**
     * Returns season.
     *
     * @return season
     */
    public Season getSeason() {
        return season;
    }

    /**
     * Sets a new value to season.
     *
     * @param season new value
     */
    public void setSeason(final Season season) {
        this.season = season;
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
        if (!(obj instanceof SeasonDataMO)) {
            return false;
        }

        return Objects.equals(season, ((SeasonDataMO) obj).season);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(season);
    }

    @Override
    public String toString() {
        return String.format("SeasonDataMO [season=%s, episodesCount=%d, totalLength=%s]", season, episodesCount, totalLength);
    }

}
