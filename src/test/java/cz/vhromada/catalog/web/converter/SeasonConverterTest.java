package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.common.SeasonUtils;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link SeasonMO} to {@link Season}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class SeasonConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertSeasonMO() {
        final SeasonMO seasonMO = SeasonUtils.getSeasonMO();

        final Season season = converter.convert(seasonMO, Season.class);

        SeasonUtils.assertSeasonDeepEquals(seasonMO, season);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for season.
     */
    @Test
    void convertSeasonMO_NullSeasonMO() {
        assertThat(converter.convert(null, Season.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertSeason() {
        final Season season = SeasonUtils.getSeason();

        final SeasonMO seasonMO = converter.convert(season, SeasonMO.class);

        SeasonUtils.assertSeasonDeepEquals(seasonMO, season);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null season.
     */
    @Test
    void convertSeason_NullSeason() {
        assertThat(converter.convert(null, SeasonMO.class)).isNull();
    }

}
