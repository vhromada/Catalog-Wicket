package cz.vhromada.catalog.web.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.web.TimeMO;

/**
 * A class represents utility class for time.
 *
 * @author Vladimir Hromada
 */
public final class TimeUtils {

    /**
     * Creates a new instance of TimeUtils.
     */
    private TimeUtils() {
    }

    /**
     * Returns MO for time.
     *
     * @return MO for time
     */
    public static TimeMO getTimeMO() {
        final TimeMO time = new TimeMO();
        time.setHours(1);
        time.setMinutes(2);
        time.setSeconds(3);

        return time;
    }

    /**
     * Asserts time deep equals.
     *
     * @param expected expected time
     * @param actual   actual length
     */
    public static void assertTimeDeepEquals(final TimeMO expected, final Integer actual) {
        assertThat(actual, is(notNullValue()));
        assertTimeDeepEquals(expected, new Time(actual));
    }

    /**
     * Asserts time deep equals.
     *
     * @param expected expected time
     * @param actual   actual length
     */
    public static void assertTimeDeepEquals(final TimeMO expected, final Time actual) {
        assertThat(actual, is(notNullValue()));
        assertThat(actual.getData(Time.TimeData.HOUR), is(expected.getHours()));
        assertThat(actual.getData(Time.TimeData.MINUTE), is(expected.getMinutes()));
        assertThat(actual.getData(Time.TimeData.SECOND), is(expected.getSeconds()));
    }

}
