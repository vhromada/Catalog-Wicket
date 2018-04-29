package cz.vhromada.catalog.web.common;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.common.Time;

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
        assertSoftly(softly -> {
            softly.assertThat(expected).isNotNull();
            softly.assertThat(actual).isNotNull();
        });
        assertTimeDeepEquals(expected, new Time(actual));
    }

    /**
     * Asserts time deep equals.
     *
     * @param expected expected time
     * @param actual   actual length
     */
    public static void assertTimeDeepEquals(final TimeMO expected, final Time actual) {
        assertSoftly(softly -> {
            softly.assertThat(expected).isNotNull();
            softly.assertThat(actual).isNotNull();
        });
        assertSoftly(softly -> {
            softly.assertThat(actual.getData(Time.TimeData.HOUR)).isEqualTo(expected.getHours());
            softly.assertThat(actual.getData(Time.TimeData.MINUTE)).isEqualTo(expected.getMinutes());
            softly.assertThat(actual.getData(Time.TimeData.SECOND)).isEqualTo(expected.getSeconds());
        });
    }

}
