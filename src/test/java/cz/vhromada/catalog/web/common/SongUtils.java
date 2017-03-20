package cz.vhromada.catalog.web.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.song.mo.SongMO;

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
     * Returns song.
     *
     * @return song
     */
    public static Song getSong() {
        final Song song = new Song();
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
     * @param actual   actual song
     */
    public static void assertSongDeepEquals(final SongMO expected, final Song actual) {
        assertThat(actual, is(notNullValue()));
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getName(), is(expected.getName()));
        TimeUtils.assertTimeDeepEquals(expected.getLength(), actual.getLength());
        assertThat(actual.getNote(), is(expected.getNote()));
        assertThat(actual.getPosition(), is(expected.getPosition()));
    }

}
