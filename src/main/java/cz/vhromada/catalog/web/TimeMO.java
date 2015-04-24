package cz.vhromada.catalog.web;

import java.io.Serializable;
import java.util.Objects;

/**
 * A class represents MO for time.
 *
 * @author Vladimir Hromada
 */
public class TimeMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Hours
     */
    private Integer hours;

    /**
     * Minutes
     */
    private Integer minutes;

    /**
     * Seconds
     */
    private Integer seconds;

    /**
     * Returns hours.
     *
     * @return hours
     */
    public Integer getHours() {
        return hours;
    }

    /**
     * Sets a new value to hours.
     *
     * @param hours new value
     */
    public void setHours(final Integer hours) {
        this.hours = hours;
    }

    /**
     * Returns minutes.
     *
     * @return minutes
     */
    public Integer getMinutes() {
        return minutes;
    }

    /**
     * Sets a new value to minutes.
     *
     * @param minutes new value
     */
    public void setMinutes(final Integer minutes) {
        this.minutes = minutes;
    }

    /**
     * Returns seconds.
     *
     * @return seconds
     */
    public Integer getSeconds() {
        return seconds;
    }

    /**
     * Sets a new value to seconds.
     *
     * @param seconds new value
     */
    public void setSeconds(final Integer seconds) {
        this.seconds = seconds;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof TimeMO)) {
            return false;
        }
        final TimeMO time = (TimeMO) obj;
        return Objects.equals(hours, time.hours) && Objects.equals(minutes, time.minutes) && Objects.equals(seconds, time.seconds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes, seconds);
    }

    @Override
    public String toString() {
        return String.format("TimeMO [hours=%d, minutes=%d, seconds=%d]", hours, minutes, seconds);
    }

}
