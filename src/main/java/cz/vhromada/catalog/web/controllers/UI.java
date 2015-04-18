package cz.vhromada.catalog.web.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.web.events.PageEvent;
import cz.vhromada.validators.Validators;

/**
 * A class represents user interface.
 *
 * @author Vladimir Hromada
 */
public class UI {

    /**
     * Events
     */
    private List<PageEvent> events = new ArrayList<>();

    /**
     * Fire event on UI.
     *
     * @param event event
     * @throws IllegalArgumentException if event is null
     */
    public void fireEvent(final PageEvent event) {
        Validators.validateArgumentNotNull(event, "Event");

        events.add(event);
    }

    /**
     * Returns events.
     *
     * @return events
     */
    public List<PageEvent> getEvents() {
        return events;
    }


}