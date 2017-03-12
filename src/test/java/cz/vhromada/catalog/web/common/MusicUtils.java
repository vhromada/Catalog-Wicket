package cz.vhromada.catalog.web.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.music.mo.MusicMO;

/**
 * A class represents utility class for musics.
 *
 * @author Vladimir Hromada
 */
public final class MusicUtils {

    /**
     * Creates a new instance of MusicUtils.
     */
    private MusicUtils() {
    }

    /**
     * Returns MO for music.
     *
     * @return MO for music
     */
    public static MusicMO getMusicMO() {
        final MusicMO music = new MusicMO();
        music.setId(CatalogUtils.ID);
        music.setName(CatalogUtils.NAME);
        music.setWikiEn(CatalogUtils.EN_WIKI);
        music.setWikiCz(CatalogUtils.CZ_WIKI);
        music.setMediaCount(CatalogUtils.MEDIA);
        music.setNote(CatalogUtils.NOTE);
        music.setPosition(CatalogUtils.POSITION);

        return music;
    }

    /**
     * Returns music.
     *
     * @return music
     */
    public static Music getMusic() {
        final Music music = new Music();
        music.setId(CatalogUtils.ID);
        music.setName(CatalogUtils.NAME);
        music.setWikiEn(CatalogUtils.EN_WIKI);
        music.setWikiCz(CatalogUtils.CZ_WIKI);
        music.setMediaCount(CatalogUtils.MEDIA);
        music.setNote(CatalogUtils.NOTE);
        music.setPosition(CatalogUtils.POSITION);

        return music;
    }

    /**
     * Asserts music deep equals.
     *
     * @param expected expected MO for music
     * @param actual   actual music
     */
    public static void assertMusicDeepEquals(final MusicMO expected, final Music actual) {
        assertThat(actual, is(notNullValue()));
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getWikiEn(), is(expected.getWikiEn()));
        assertThat(actual.getWikiCz(), is(expected.getWikiCz()));
        assertThat(actual.getMediaCount(), is(expected.getMediaCount()));
        assertThat(actual.getNote(), is(expected.getNote()));
        assertThat(actual.getPosition(), is(expected.getPosition()));
    }

}
