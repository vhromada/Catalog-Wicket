package cz.vhromada.catalog.web.converter;

import cz.vhromada.catalog.common.Time;
import cz.vhromada.catalog.web.TimeMO;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

/**
 * A class represents converter between {@link TimeMO} and {@link Integer}.
 *
 * @author Vladimir Hromada
 */
@Component("timeConverter")
public class TimeConverter extends BidirectionalConverter<TimeMO, Integer> {

    @Override
    public Integer convertTo(final TimeMO source, final Type<Integer> type, final MappingContext mappingContext) {
        if (source == null) {
            return null;
        }

        return new Time(source.getHours(), source.getMinutes(), source.getSeconds()).getLength();
    }

    @Override
    public TimeMO convertFrom(final Integer source, final Type<TimeMO> type, final MappingContext mappingContext) {
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

}
