package cz.vhromada.catalog.web.events;

import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.springframework.util.Assert;

/**
 * A class represents event for calling another controller.
 *
 * @author Vladimir Hromada
 */
public class ControllerEvent implements PageEvent {

    /**
     * Flow
     */
    private Flow flow;

    /**
     * Data
     */
    private Object data;

    /**
     * Creates a new instance of ControllerEvent.
     *
     * @param flow flow
     * @param data data
     * @throws IllegalArgumentException if flow is null
     */
    public ControllerEvent(final Flow flow, final Object data) {
        Assert.notNull(flow, "Flow mustn't be null.");

        this.flow = flow;
        this.data = data;
    }

    /**
     * Returns flow.
     *
     * @return flow
     */
    public Flow getFlow() {
        return flow;
    }

    /**
     * Returns data.
     *
     * @return data
     */
    public Object getData() {
        return data;
    }

}
