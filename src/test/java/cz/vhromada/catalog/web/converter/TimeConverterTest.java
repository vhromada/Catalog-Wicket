package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.common.TimeUtils;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link TimeMO} to {@link Integer}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class TimeConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to integer.
     */
    @Test
    void convertTimeMO() {
        final TimeMO time = TimeUtils.getTimeMO();

        final Integer result = converter.convert(time, Integer.class);

        TimeUtils.assertTimeDeepEquals(time, result);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to integer with null MO for time.
     */
    @Test
    void convertTimeMO_NullTimeMO() {
        assertThat(converter.convert(null, Integer.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from integer to MO.
     */
    @Test
    void convertInteger() {
        final Integer length = 100;

        final TimeMO time = converter.convert(length, TimeMO.class);

        TimeUtils.assertTimeDeepEquals(time, length);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from integer to MO with null integer.
     */
    @Test
    void convertInteger_NullInteger() {
        assertThat(converter.convert(null, TimeMO.class)).isNull();
    }

}
