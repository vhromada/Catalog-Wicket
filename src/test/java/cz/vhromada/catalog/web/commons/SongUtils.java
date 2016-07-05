package cz.vhromada.catalog.web.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.songs.mo.SongMO;

/**
 * A class represents utility class for songs.
 *
 * @author Vladimir Hromada
 */
public final class SongUtils {

    /**
     * Creates a new instance of SongUtils.
     */
    private SongUtils() {
    }

    /**
     * Returns MO for song.
     *
     * @return MO for song
     */
    public static SongMO getSongMO() {
        final SongMO song = new SongMO();
        song.setId(CatalogUtils.ID);
        song.setName(CatalogUtils.NAME);
        song.setLength(TimeUtils.getTimeMO());
        song.setNote(CatalogUtils.NOTE);
        song.setPosition(CatalogUtils.POSITION);

        return song;
    }

    /**
     * Returns TO for song.
     *
     * @return TO for song
     */
    public static SongTO getSongTO() {
        final SongTO song = new SongTO();
        song.setId(CatalogUtils.ID);
        song.setName(CatalogUtils.NAME);
        song.setLength(CatalogUtils.LENGTH);
        song.setNote(CatalogUtils.NOTE);
        song.setPosition(CatalogUtils.POSITION);

        return song;
    }

    /**
     * Asserts song deep equals.
     *
     * @param expected expected MO for song
     * @param actual   actual TO for song
     */
    public static void assertSongDeepEquals(final SongMO expected, final SongTO actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        TimeUtils.assertTimeDeepEquals(expected.getLength(), actual.getLength());
        assertEquals(expected.getNote(), actual.getNote());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

}
