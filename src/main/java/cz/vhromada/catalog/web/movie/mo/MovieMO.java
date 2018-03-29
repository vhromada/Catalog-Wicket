package cz.vhromada.catalog.web.movie.mo;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import cz.vhromada.catalog.common.Language;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.genre.mo.GenreMO;

/**
 * A class represents MO for movie.
 *
 * @author Vladimir Hromada
 */
public class MovieMO implements Serializable {

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
     * Year
     */
    private Integer year;

    /**
     * Language
     */
    private Language language;

    /**
     * Subtitles
     */
    private List<Language> subtitles;

    /**
     * Media
     */
    private List<TimeMO> media;

    /**
     * URL to ČSFD page about movie
     */
    private String csfd;

    /**
     * IMDB code
     */
    private Integer imdbCode;

    /**
     * URL to english Wikipedia page about movie
     */
    private String wikiEn;

    /**
     * URL to czech Wikipedia page about movie
     */
    private String wikiCz;

    /**
     * Path to file with movie picture
     */
    private Integer picture;

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
     * All pictures
     */
    private List<Integer> allPictures;

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
     * Returns year.
     *
     * @return year
     */
    public Integer getYear() {
        return year;
    }

    /**
     * Sets a new value to year.
     *
     * @param year new value
     */
    public void setYear(final Integer year) {
        this.year = year;
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
     * Returns media.
     *
     * @return media
     */
    public List<TimeMO> getMedia() {
        return media;
    }

    /**
     * Sets a new value to media.
     *
     * @param media new value
     */
    public void setMedia(final List<TimeMO> media) {
        this.media = media;
    }

    /**
     * Returns URL to ČSFD page about movie.
     *
     * @return URL to ČSFD page about movie
     */
    public String getCsfd() {
        return csfd;
    }

    /**
     * Sets a new value to URL to ČSFD page about movie.
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
     * Returns URL to english Wikipedia page about movie.
     *
     * @return URL to english Wikipedia page about movie
     */
    public String getWikiEn() {
        return wikiEn;
    }

    /**
     * Sets a new value to URL to english Wikipedia page about movie.
     *
     * @param wikiEn new value
     */
    public void setWikiEn(final String wikiEn) {
        this.wikiEn = wikiEn;
    }

    /**
     * Returns URL to czech Wikipedia page about movie.
     *
     * @return URL to czech Wikipedia page about movie
     */
    public String getWikiCz() {
        return wikiCz;
    }

    /**
     * Sets a new value to URL to czech Wikipedia page about movie.
     *
     * @param wikiCz new value
     */
    public void setWikiCz(final String wikiCz) {
        this.wikiCz = wikiCz;
    }

    /**
     * Returns path to file with movie picture.
     *
     * @return path to file with movie picture
     */
    public Integer getPicture() {
        return picture;
    }

    /**
     * Sets a new value to path to file with movie picture.
     *
     * @param picture new value
     */
    public void setPicture(final Integer picture) {
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

    /**
     * Returns all pictures.
     *
     * @return all pictures
     */
    public List<Integer> getAllPictures() {
        return allPictures;
    }

    /**
     * Sets a new value to all pictures.
     *
     * @param allPictures new value
     */
    public void setAllPictures(final List<Integer> allPictures) {
        this.allPictures = allPictures;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MovieMO) || id == null) {
            return false;
        }

        return id.equals(((MovieMO) obj).id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return String.format("MovieMO [id=%d, czechName=%s, originalName=%s, year=%d, language=%s, subtitles=%s, media=%s, csfd=%s, imdbCode=%d, wikiEn=%s, "
                + "wikiCz=%s, picture=%d, note=%s, position=%d, genres=%s, allGenres=%s, allPictures=%s]", id, czechName, originalName, year, language,
            subtitles, media, csfd, imdbCode, wikiEn, wikiCz, picture, note, position, genres, allGenres, allPictures);
    }

}
