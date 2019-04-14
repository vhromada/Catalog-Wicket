package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.common.Time;

import org.mapstruct.Mapper;

/**
 * An abstract class represents mapper for time.
 *
 * @author Vladimir Hromada
 */
@Mapper
public abstract class TimeMapper {

    /**
     * Returns MO for time.
     *
     * @param source length
     * @return time
     */
    public TimeMO map(final Integer source) {
        if (source == null) {
            return null;
        }

        final Time time = new Time(source);

        final TimeMO result = new TimeMO();
        result.setHours(time.getData(Time.TimeData.HOUR));
        result.setMinutes(time.getData(Time.TimeData.MINUTE));
        result.setSeconds(time.getData(Time.TimeData.SECOND));
        return result;
    }

    /**
     * Returns length.
     *
     * @param source MO for time
     * @return length
     */
    public Integer mapBack(final TimeMO source) {
        if (source == null) {
            return null;
        }

        return new Time(source.getHours(), source.getMinutes(), source.getSeconds()).getLength();
    }

}
