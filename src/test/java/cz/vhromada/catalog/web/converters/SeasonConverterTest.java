package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.web.commons.SeasonUtils;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link SeasonMO} to {@link SeasonTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class SeasonConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    @Qualifier("webDozerConverter")
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO.
     */
    @Test
    public void testConvertSeasonMO() {
        final SeasonMO seasonMO = SeasonUtils.getSeasonMO();

        final SeasonTO seasonTO = converter.convert(seasonMO, SeasonTO.class);

        SeasonUtils.assertSeasonDeepEquals(seasonMO, seasonTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertSeasonMO_NullArgument() {
        assertNull(converter.convert(null, SeasonTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertSeasonTO() {
        final SeasonTO seasonTO = SeasonUtils.getSeasonTO();

        final SeasonMO seasonMO = converter.convert(seasonTO, SeasonMO.class);

        SeasonUtils.assertSeasonDeepEquals(seasonMO, seasonTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertSeasonTO_NullArgument() {
        assertNull(converter.convert(null, SeasonMO.class));
    }

}
