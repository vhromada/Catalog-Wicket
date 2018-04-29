package cz.vhromada.catalog.web.season.mo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import cz.vhromada.common.Language;

/**
 * A class represents MO for season.
 *
 * @author Vladimir Hromada
 */
public class SeasonMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * Number of season
     */
    private Integer number;

    /**
     * Starting year
     */
    private Integer startYear;

    /**
     * Ending year
     */
    private Integer endYear;

    /**
     * Language
     */
    private Language language;

    /**
     * Subtitles
     */
    private List<Language> subtitles;

    /**
     * Note
     */
    private String note;

    /**
     * Position
     */
    private Integer position;

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
     * Returns number of season.
     *
     * @return number of season
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * Sets a new value to number of season.
     *
     * @param number new value
     */
    public void setNumber(final Integer number) {
        this.number = number;
    }

    /**
     * Returns starting year.
     *
     * @return starting year
     */
    public Integer getStartYear() {
        return startYear;
    }

    /**
     * Sets a new value to starting year.
     *
     * @param startYear new value
     */
    public void setStartYear(final Integer startYear) {
        this.startYear = startYear;
    }

    /**
     * Returns ending year.
     *
     * @return ending year
     */
    public Integer getEndYear() {
        return endYear;
    }

    /**
     * Sets a new value to ending year.
     *
     * @param endYear new value
     */
    public void setEndYear(final Integer endYear) {
        this.endYear = endYear;
    }

    /**
     * Returns language.
     *
     * @return language
     */
    public Language getLanguage() {
        return language;
    }

    /**
     * Sets a new value to language.
     *
     * @param language new value
     * @throws IllegalArgumentException if new value is null
     */
    public void setLanguage(final Language language) {
        this.language = language;
    }

    /**
     * Returns subtitles.
     *
     * @return subtitles
     */
    public List<Language> getSubtitles() {
        return subtitles;
    }

    /**
     * Sets a new value to subtitles.
     *
     * @param subtitles new value
     */
    public void setSubtitles(final List<Language> subtitles) {
        this.subtitles = subtitles;
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
    public Integer getPosition() {
        return position;
    }

    /**
     * Sets a new value to position.
     *
     * @param position new value
     */
    public void setPosition(final Integer position) {
        this.position = position;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof SeasonMO) || id == null) {
            return false;
        }

        return id.equals(((SeasonMO) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("SeasonMO [id=%d, number=%d, startYear=%d, endYear=%d, language=%s, subtitles=%s, note=%s, position=%d]", id, number, startYear,
            endYear, language, subtitles, note, position);
    }

}
