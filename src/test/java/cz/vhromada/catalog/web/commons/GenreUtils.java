package cz.vhromada.catalog.web.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.web.genres.mo.GenreMO;

/**
 * A class represents utility class for genres.
 *
 * @author Vladimir Hromada
 */
public final class GenreUtils {

    /**
     * Creates a new instance of GenreUtils.
     */
    private GenreUtils() {
    }

    /**
     * Returns MO for genre.
     *
     * @return MO for genre
     */
    public static GenreMO getGenreMO() {
        final GenreMO genre = new GenreMO();
        genre.setId(CatalogUtils.ID);
        genre.setName(CatalogUtils.NAME);
        genre.setPosition(CatalogUtils.POSITION);

        return genre;
    }

    /**
     * Returns genre.
     *
     * @return genre
     */
    public static Genre getGenre() {
        final Genre genre = new Genre();
        genre.setId(CatalogUtils.ID);
        genre.setName(CatalogUtils.NAME);
        genre.setPosition(CatalogUtils.POSITION);

        return genre;
    }


    /**
     * Asserts genres deep equals.
     *
     * @param expected expected list of genres
     * @param actual   actual list of genre
     */
    public static void assertGenresDeepEquals(final List<GenreMO> expected, final List<Genre> actual) {
        assertNotNull(actual);
        assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertGenreDeepEquals(expected.get(i), actual.get(i));
        }
    }

    /**
     * Asserts genre deep equals.
     *
     * @param expected expected MO for genre
     * @param actual   actual genre
     */
    public static void assertGenreDeepEquals(final GenreMO expected, final Genre actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

}
