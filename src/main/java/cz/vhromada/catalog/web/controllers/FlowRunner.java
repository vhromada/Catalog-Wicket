package cz.vhromada.catalog.web.controllers;

import org.apache.wicket.Component;
import org.apache.wicket.event.Broadcast;

/**
 * A class represents flow runner.
 *
 * @author Vladimir Hromada
 */
public class FlowRunner {

    /**
     * Creates a new instance of FlowRunner.
     *
     * @param source source
     * @param flow   flow
     */
    public FlowRunner(final Component source, final Flow flow) {
        this(source, new FrontControllerRequest(flow));
    }

    /**
     * Creates a new instance of FlowRunner.
     *
     * @param source source
     * @param flow   flow
     * @param data   data
     * @param <T>    type of data
     */
    public <T> FlowRunner(final Component source, final Flow flow, final T data) {
        this(source, new FrontControllerRequest<>(flow, data));
    }

    /**
     * Creates a new instance of FlowRunner.
     *
     * @param source  source
     * @param request front controller request
     */
    public FlowRunner(final Component source, final FrontControllerRequest<?> request) {
        source.send(source.getPage(), Broadcast.EXACT, request);
    }

}
