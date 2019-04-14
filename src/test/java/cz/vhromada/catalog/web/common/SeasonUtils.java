package cz.vhromada.catalog.web.common;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import java.util.Collections;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.common.Language;

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
        season.setSubtitles(Collections.singletonList(Language.CZ));
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
        season.setSubtitles(Collections.singletonList(Language.CZ));
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
        assertSoftly(softly -> {
            softly.assertThat(expected).isNotNull();
            softly.assertThat(actual).isNotNull();
        });
        assertSoftly(softly -> {
            softly.assertThat(actual.getId()).isEqualTo(expected.getId());
            softly.assertThat(actual.getNumber()).isEqualTo(expected.getNumber());
            softly.assertThat(actual.getStartYear()).isEqualTo(expected.getStartYear());
            softly.assertThat(actual.getEndYear()).isEqualTo(expected.getEndYear());
            softly.assertThat(actual.getLanguage()).isEqualTo(expected.getLanguage());
            softly.assertThat(actual.getSubtitles()).isEqualTo(expected.getSubtitles());
            softly.assertThat(actual.getNote()).isEqualTo(expected.getNote());
            softly.assertThat(actual.getPosition()).isEqualTo(expected.getPosition());
        });
    }

}
