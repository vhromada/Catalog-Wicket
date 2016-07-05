package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.commons.TimeUtils;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link TimeMO} to {@link Integer}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class TimeConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    @Qualifier("webDozerConverter")
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to integer.
     */
    @Test
    public void testConvertTimeMO() {
        final TimeMO time = TimeUtils.getTimeMO();

        final Integer result = converter.convert(time, Integer.class);

        TimeUtils.assertTimeDeepEquals(time, result);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to integer with null argument.
     */
    @Test
    public void testConvertTimeMO_NullArgument() {
        assertNull(converter.convert(null, Integer.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from integer to MO.
     */
    @Test
    public void testConvertInteger() {
        final Integer length = 100;

        final TimeMO time = converter.convert(length, TimeMO.class);

        TimeUtils.assertTimeDeepEquals(time, length);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from integer to MO with null argument.
     */
    @Test
    public void testConvertInteger_NullArgument() {
        assertNull(converter.convert(null, Integer.class));
    }

}
