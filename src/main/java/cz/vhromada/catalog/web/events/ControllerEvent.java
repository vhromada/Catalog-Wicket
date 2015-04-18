package cz.vhromada.catalog.web.events;

import cz.vhromada.catalog.web.controllers.Flow;

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
     */
    public ControllerEvent(final Flow flow) {
        this(flow, null);
    }

    /**
     * Creates a new instance of ControllerEvent.
     *
     * @param flow flow
     * @param data data
     */
    public ControllerEvent(final Flow flow, final Object data) {
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
