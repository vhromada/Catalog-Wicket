package cz.vhromada.catalog.web.games.mo;

import java.io.Serializable;
import java.util.List;

import cz.vhromada.catalog.facade.to.GameTO;

/**
 * A class represents MO for games.
 *
 * @author Vladimir Hromada
 */
public class GamesMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of games
     */
    private List<GameTO> games;

    /**
     * Count of media
     */
    private int mediaCount;

    /**
     * Returns list of games.
     *
     * @return list of games
     */
    public List<GameTO> getGames() {
        return games;
    }

    /**
     * Sets a new value to list of games.
     *
     * @param games new value
     */
    public void setGames(final List<GameTO> games) {
        this.games = games;
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

}
