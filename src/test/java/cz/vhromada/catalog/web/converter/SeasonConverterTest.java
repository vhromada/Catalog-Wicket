package cz.vhromada.catalog.web.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.common.SeasonUtils;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.converter.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link SeasonMO} to {@link Season}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
public class SeasonConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertSeasonMO() {
        final SeasonMO seasonMO = SeasonUtils.getSeasonMO();

        final Season season = converter.convert(seasonMO, Season.class);

        SeasonUtils.assertSeasonDeepEquals(seasonMO, season);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for season.
     */
    @Test
    public void convertSeasonMO_NullSeasonMO() {
        assertThat(converter.convert(null, Season.class), is(nullValue()));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertSeason() {
        final Season season = SeasonUtils.getSeason();

        final SeasonMO seasonMO = converter.convert(season, SeasonMO.class);

        SeasonUtils.assertSeasonDeepEquals(seasonMO, season);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null season.
     */
    @Test
    public void convertSeason_NullSeason() {
        assertThat(converter.convert(null, SeasonMO.class), is(nullValue()));
    }

}
