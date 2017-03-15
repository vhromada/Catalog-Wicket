package cz.vhromada.catalog.web.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.common.TimeUtils;
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
@ContextConfiguration(classes = ConverterConfiguration.class)
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
        assertThat(converter.convert(null, Integer.class), is(nullValue()));
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
        assertThat(converter.convert(null, TimeMO.class), is(nullValue()));
    }

}
