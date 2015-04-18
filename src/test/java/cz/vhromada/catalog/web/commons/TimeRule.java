package cz.vhromada.catalog.web.commons;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.generator.configuration.Rule;

/**
 * A class represents generating rule for time.
 *
 * @author Vladimir Hromada
 */
public class TimeRule extends Rule<Time> {

    /**
     * Creates a new instance of TimeRule.
     */
    public TimeRule() {
        setClazz(Time.class);
        setObject(new Time(0));
    }

}
