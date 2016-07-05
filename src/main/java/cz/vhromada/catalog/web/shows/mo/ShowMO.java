package cz.vhromada.catalog.web.shows.mo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import cz.vhromada.catalog.web.genres.mo.GenreMO;

/**
 * A class represents MO for show.
 *
 * @author Vladimir Hromada
 */
public class ShowMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Integer id;

    /**
     * Czech name
     */
    private String czechName;

    /**
     * Original name
     */
    private String originalName;

    /**
     * URL to ČSFD page about show
     */
    private String csfd;

    /**
     * IMDB code
     */
    private Integer imdbCode;

    /**
     * URL to english Wikipedia page about show
     */
    private String wikiEn;

    /**
     * URL to czech Wikipedia page about show
     */
    private String wikiCz;

    /**
     * Path to file with show picture
     */
    private String picture;

    /**
     * Note
     */
    private String note;

    /**
     * Position
     */
    private int position;

    /**
     * Genres
     */
    private List<GenreMO> genres;

    /**
     * All genres
     */
    private List<GenreMO> allGenres;

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
     * Returns czech name.
     *
     * @return czech name
     */
    public String getCzechName() {
        return czechName;
    }

    /**
     * Sets a new value to czech name.
     *
     * @param czechName new value
     */
    public void setCzechName(final String czechName) {
        this.czechName = czechName;
    }

    /**
     * Returns original name.
     *
     * @return original name
     */
    public String getOriginalName() {
        return originalName;
    }

    /**
     * Sets a new value to original name.
     *
     * @param originalName new value
     */
    public void setOriginalName(final String originalName) {
        this.originalName = originalName;
    }

    /**
     * Returns URL to ČSFD page about show.
     *
     * @return URL to ČSFD page about show
     */
    public String getCsfd() {
        return csfd;
    }

    /**
     * Sets a new value to URL to ČSFD page about show.
     *
     * @param csfd new value
     */
    public void setCsfd(final String csfd) {
        this.csfd = csfd;
    }

    /**
     * Returns IMDB code.
     *
     * @return IMDB code
     */
    public Integer getImdbCode() {
        return imdbCode;
    }

    /**
     * Sets a new value to IMDB code.
     *
     * @param imdbCode new value
     */
    public void setImdbCode(final Integer imdbCode) {
        this.imdbCode = imdbCode;
    }

    /**
     * Returns URL to english Wikipedia page about show.
     *
     * @return URL to english Wikipedia page about show
     */
    public String getWikiEn() {
        return wikiEn;
    }

    /**
     * Sets a new value to URL to english Wikipedia page about show.
     *
     * @param wikiEn new value
     */
    public void setWikiEn(final String wikiEn) {
        this.wikiEn = wikiEn;
    }

    /**
     * Returns URL to czech Wikipedia page about show.
     *
     * @return URL to czech Wikipedia page about show
     */
    public String getWikiCz() {
        return wikiCz;
    }

    /**
     * Sets a new value to URL to czech Wikipedia page about show.
     *
     * @param wikiCz new value
     */
    public void setWikiCz(final String wikiCz) {
        this.wikiCz = wikiCz;
    }

    /**
     * Returns path to file with show picture.
     *
     * @return path to file with show picture
     */
    public String getPicture() {
        return picture;
    }

    /**
     * Sets a new value to path to file with show picture.
     *
     * @param picture new value
     */
    public void setPicture(final String picture) {
        this.picture = picture;
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

    /**
     * Returns genres.
     *
     * @return genres
     */
    public List<GenreMO> getGenres() {
        return genres;
    }

    /**
     * Sets a new value to genres.
     *
     * @param genres new value
     */
    public void setGenres(final List<GenreMO> genres) {
        this.genres = genres;
    }

    /**
     * Returns all genres.
     *
     * @return all genres
     */
    public List<GenreMO> getAllGenres() {
        return allGenres;
    }

    /**
     * Sets a new value to all genres.
     *
     * @param allGenres new value
     */
    public void setAllGenres(final List<GenreMO> allGenres) {
        this.allGenres = allGenres;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof ShowMO) || id == null) {
            return false;
        }

        return id.equals(((ShowMO) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("ShowMO [id=%d, czechName=%s, originalName=%s, csfd=%s, imdbCode=%d, wikiEn=%s, wikiCz=%s, picture=%s, note=%s, position=%d, "
                + "genres=%s, allGenres=%s]", id, czechName, originalName, csfd, imdbCode, wikiEn, wikiCz, picture, note, position, genres, allGenres);
    }

}
