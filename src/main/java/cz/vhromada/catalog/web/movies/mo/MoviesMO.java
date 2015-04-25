package cz.vhromada.catalog.web.movies.mo;

import java.io.Serializable;
import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.MovieTO;

/**
 * A class represents MO for movies.
 *
 * @author Vladimir Hromada
 */
public class MoviesMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of movies
     */
    private List<MovieTO> movies;

    /**
     * Count of media
     */
    private int mediaCount;

    /**
     * Total length
     */
    private Time totalLength;

    /**
     * Returns list of movies.
     *
     * @return list of movies
     */
    public List<MovieTO> getMovies() {
        return movies;
    }

    /**
     * Sets a new value to list of movies.
     *
     * @param movies new value
     */
    public void setMovies(final List<MovieTO> movies) {
        this.movies = movies;
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
