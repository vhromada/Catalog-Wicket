package cz.vhromada.catalog.web.controllers;

/**
 * A class represents front controller's request.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public class FrontControllerRequest<T> {

    /**
     * Flow
     */
    private Flow flow;

    /**
     * Data
     */
    private T data;

    /**
     * Creates a new instance of FrontControllerRequest.
     *
     * @param flow flow
     */
    public FrontControllerRequest(final Flow flow) {
        this(flow, null);
    }

    /**
     * Creates a new instance of FrontControllerRequest.
     *
     * @param flow flow
     * @param data data
     */
    public FrontControllerRequest(final Flow flow, final T data) {
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
    public T getData() {
        return data;
    }

}
