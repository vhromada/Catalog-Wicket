package cz.vhromada.catalog.web.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;

/**
 * A class represents utility class for episodes.
 *
 * @author Vladimir Hromada
 */
public final class EpisodeUtils {

    /**
     * Creates a new instance of EpisodeUtils.
     */
    private EpisodeUtils() {
    }

    /**
     * Returns MO for episode.
     *
     * @return MO for episode
     */
    public static EpisodeMO getEpisodeMO() {
        final EpisodeMO episode = new EpisodeMO();
        episode.setId(CatalogUtils.ID);
        episode.setNumber(CatalogUtils.NUMBER);
        episode.setName(CatalogUtils.NAME);
        episode.setLength(TimeUtils.getTimeMO());
        episode.setNote(CatalogUtils.NOTE);
        episode.setPosition(CatalogUtils.POSITION);

        return episode;
    }

    /**
     * Returns episode.
     *
     * @return episode
     */
    public static Episode getEpisode() {
        final Episode episode = new Episode();
        episode.setId(CatalogUtils.ID);
        episode.setNumber(CatalogUtils.NUMBER);
        episode.setName(CatalogUtils.NAME);
        episode.setLength(CatalogUtils.LENGTH);
        episode.setNote(CatalogUtils.NOTE);
        episode.setPosition(CatalogUtils.POSITION);

        return episode;
    }

    /**
     * Asserts episode deep equals.
     *
     * @param expected expected MO for episode
     * @param actual   actual episode
     */
    public static void assertEpisodeDeepEquals(final EpisodeMO expected, final Episode actual) {
        assertThat(actual, is(notNullValue()));
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getNumber(), is(expected.getNumber()));
        assertThat(actual.getName(), is(expected.getName()));
        TimeUtils.assertTimeDeepEquals(expected.getLength(), actual.getLength());
        assertThat(actual.getNote(), is(expected.getNote()));
        assertThat(actual.getPosition(), is(expected.getPosition()));
    }

}
