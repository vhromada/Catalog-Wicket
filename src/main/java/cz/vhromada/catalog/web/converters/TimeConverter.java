package cz.vhromada.catalog.web.converters;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.web.TimeMO;

import org.dozer.CustomConverter;
import org.dozer.MappingException;

/**
 * A class represents converter between MO for time and length.
 *
 * @author Vladimir Hromada
 */
public class TimeConverter implements CustomConverter {

    @Override
    public Object convert(final Object existingDestinationFieldValue, final Object sourceFieldValue, final Class<?> destinationClass,
            final Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return null;
        }

        if (sourceFieldValue instanceof TimeMO && sourceClass == TimeMO.class && (destinationClass == Integer.class || destinationClass == int.class)) {
            return convertTimeMO((TimeMO) sourceFieldValue);
        } else if (sourceFieldValue instanceof Integer && sourceClass == Integer.class && destinationClass == TimeMO.class) {
            return convertLength((Integer) sourceFieldValue);
        } else {
            throw new MappingException("Converter TimeConverter used incorrectly. Arguments passed in were:" + existingDestinationFieldValue + " and "
                    + sourceFieldValue);
        }
    }

    /**
     * Returns converted MO for time to length.
     *
     * @param source MO for time
     * @return converted MO for time to length
     */
    private static Integer convertTimeMO(final TimeMO source) {
        return new Time(source.getHours(), source.getMinutes(), source.getSeconds()).getLength();
    }

    /**
     * Returns converted length to MO for time.
     *
     * @param source length
     * @return converted length to MO for time
     */
    private static TimeMO convertLength(final Integer source) {
        final Time length = new Time(source);

        final TimeMO time = new TimeMO();
        time.setHours(length.getData(Time.TimeData.HOUR));
        time.setMinutes(length.getData(Time.TimeData.MINUTE));
        time.setSeconds(length.getData(Time.TimeData.SECOND));

        return time;
    }

}
