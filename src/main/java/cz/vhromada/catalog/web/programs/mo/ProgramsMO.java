package cz.vhromada.catalog.web.programs.mo;

import java.io.Serializable;
import java.util.List;

import cz.vhromada.catalog.facade.to.ProgramTO;

/**
 * A class represents MO for programs.
 *
 * @author Vladimir Hromada
 */
public class ProgramsMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of programs
     */
    private List<ProgramTO> programs;

    /**
     * Count of media
     */
    private int mediaCount;

    /**
     * Returns list of programs.
     *
     * @return list of programs
     */
    public List<ProgramTO> getPrograms() {
        return programs;
    }

    /**
     * Sets a new value to list of programs.
     *
     * @param programs new value
     */
    public void setPrograms(final List<ProgramTO> programs) {
        this.programs = programs;
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