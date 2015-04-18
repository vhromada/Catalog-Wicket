package cz.vhromada.catalog.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import cz.vhromada.catalog.web.events.PageEvent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents front controller.
 *
 * @author Vladimir Hromada
 */
@Component("frontController")
public class FrontController {

    /**
     * Map: flow -> controller
     */
    private static Map<Flow, Controller<?>> map;

    /**
     * Controllers
     */
    @Autowired
    private List<Controller<?>> controllers;

    /**
     * Dispatches request to appropriate controller and returns its response.
     *
     * @param request request
     * @return list of page events
     */
    public <T> List<PageEvent> dispatch(final FrontControllerRequest<T> request) {
        final Controller<T> controller = lookup(request.getFlow());
        if (controller == null) {
            throw new RuntimeException("Bad request: no controller available");
        }

        controller.setUi(new UI());
        try {
            controller.handle(request.getData());
            return controller.getUi().getEvents();
        } finally {
            controller.setUi(null);
        }
    }

    /**
     * Returns controller for specified flow.
     *
     * @param flow flow
     * @return controller for specified flo
     */
    @SuppressWarnings("unchecked")
    private <T> Controller<T> lookup(final Flow flow) {
        return (Controller<T>) map.get(flow);
    }

    /**
     * Initializes map between flow and controller.
     */
    @PostConstruct
    private void init() {
        map = new HashMap<>();
        for (Controller<?> controller : controllers) {
            map.put(controller.getFlow(), controller);
        }
    }

}
