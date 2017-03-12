package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.WicketApplication;
import cz.vhromada.catalog.web.commons.SeasonUtils;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.converters.Converter;
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
@ContextConfiguration(classes = WicketApplication.class)
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
        assertNull(converter.convert(null, Season.class));
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
        assertNull(converter.convert(null, SeasonMO.class));
    }

}
