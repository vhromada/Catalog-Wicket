package cz.vhromada.catalog.web.episodes.mo;

import java.io.Serializable;
import java.util.Objects;

import cz.vhromada.catalog.web.TimeMO;

/**
 * A class represents MO for episode.
 *
 * @author Vladimir Hromada
 */
public class EpisodeMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * Number of episode
     */
    private Integer number;

    /**
     * Name
     */
    private String name;

    /**
     * Length
     */
    private TimeMO length;

    /**
     * Note
     */
    private String note;

    /**
     * Position
     */
    private int position;

    /**
     * Returns ID.
     *
     * @return ID
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets a new value to ID.
     *
     * @param id new value
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Returns number of episode.
     *
     * @return number of episode
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets a new value to number of episode.
     *
     * @param number new value
     */
    public void setNumber(final Integer number) {
        this.number = number;
    }

    /**
     * Returns name.
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets a new value to name.
     *
     * @param name new value
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Returns length.
     *
     * @return length
     */
    public TimeMO getLength() {
        return length;
    }

    /**
     * Sets a new value to length.
     *
     * @param length new value
     */
    public void setLength(final TimeMO length) {
        this.length = length;
    }

    /**
     * Returns note.
     *
     * @return note
     */
    public String getNote() {
        return note;
    }

    /**
     * Sets a new value to note.
     *
     * @param note new value
     */
    public void setNote(final String note) {
        this.note = note;
    }

    /**
     * Returns position.
     *
     * @return position
     */
    public int getPosition() {
        return position;
    }

    /**
     * Sets a new value to position.
     *
     * @param position new value
     */
    public void setPosition(final int position) {
        this.position = position;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof EpisodeMO) || id == null) {
            return false;
        }

        return id.equals(((EpisodeMO) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("EpisodeMO [id=%d, number=%d, name=%s, length=%s, note=%s, position=%d]", id, number, name, length, note, position);
    }

}
