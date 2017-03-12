package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.WicketApplication;
import cz.vhromada.catalog.web.commons.TimeUtils;
import cz.vhromada.converters.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link TimeMO} to {@link Integer}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WicketApplication.class)
//TODO vladimir.hromada 06.03.2017: time converter
@org.junit.Ignore
public class TimeConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to integer.
     */
    @Test
    public void convertTimeMO() {
        final TimeMO time = TimeUtils.getTimeMO();

        final Integer result = converter.convert(time, Integer.class);

        TimeUtils.assertTimeDeepEquals(time, result);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to integer with null MO for time.
     */
    @Test
    public void convertTimeMO_NullTimeMO() {
        assertNull(converter.convert(null, Integer.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from integer to MO.
     */
    @Test
    public void convertInteger() {
        final Integer length = 100;

        final TimeMO time = converter.convert(length, TimeMO.class);

        TimeUtils.assertTimeDeepEquals(time, length);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from integer to MO with null integer.
     */
    @Test
    public void convertInteger_NullInteger() {
        assertNull(converter.convert(null, Integer.class));
    }

}
