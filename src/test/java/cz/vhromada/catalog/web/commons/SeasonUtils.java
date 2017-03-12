package cz.vhromada.catalog.web.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cz.vhromada.catalog.common.Language;
import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.utils.CollectionUtils;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;

/**
 * A class represents utility class for seasons.
 *
 * @author Vladimir Hromada
 */
public final class SeasonUtils {

    /**
     * Creates a new instance of SeasonUtils.
     */
    private SeasonUtils() {
    }

    /**
     * Returns MO for season.
     *
     * @return MO for season
     */
    public static SeasonMO getSeasonMO() {
        final SeasonMO season = new SeasonMO();
        season.setId(CatalogUtils.ID);
        season.setNumber(CatalogUtils.NUMBER);
        season.setStartYear(CatalogUtils.YEAR);
        season.setEndYear(CatalogUtils.YEAR + 1);
        season.setLanguage(Language.EN);
        season.setSubtitles(CollectionUtils.newList(Language.CZ));
        season.setNote(CatalogUtils.NOTE);
        season.setPosition(CatalogUtils.POSITION);

        return season;
    }

    /**
     * Returns season.
     *
     * @return season
     */
    public static Season getSeason() {
        final Season season = new Season();
        season.setId(CatalogUtils.ID);
        season.setNumber(CatalogUtils.NUMBER);
        season.setStartYear(CatalogUtils.YEAR);
        season.setEndYear(CatalogUtils.YEAR + 1);
        season.setLanguage(Language.EN);
        season.setSubtitles(CollectionUtils.newList(Language.CZ));
        season.setNote(CatalogUtils.NOTE);
        season.setPosition(CatalogUtils.POSITION);

        return season;
    }

    /**
     * Asserts season deep equals.
     *
     * @param expected expected MO for season
     * @param actual   actual season
     */
    public static void assertSeasonDeepEquals(final SeasonMO expected, final Season actual) {
        assertNotNull(actual);
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getNumber().intValue(), actual.getNumber());
        assertEquals(expected.getStartYear().intValue(), actual.getStartYear());
        assertEquals(expected.getEndYear().intValue(), actual.getEndYear());
        assertEquals(expected.getLanguage(), actual.getLanguage());
        assertEquals(expected.getSubtitles(), actual.getSubtitles());
        assertEquals(expected.getNote(), actual.getNote());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

}
