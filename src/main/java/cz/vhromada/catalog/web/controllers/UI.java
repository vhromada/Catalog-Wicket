package cz.vhromada.catalog.web.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.web.events.PageEvent;

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
     */
    public void fireEvent(final PageEvent event) {
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
