package cz.vhromada.catalog.web.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNumber().intValue(), actual.getNumber());
        assertEquals(expected.getName(), actual.getName());
        TimeUtils.assertTimeDeepEquals(expected.getLength(), actual.getLength());
        assertEquals(expected.getNote(), actual.getNote());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

}
