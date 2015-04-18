package cz.vhromada.catalog.web.events;

/**
 * An interface represents navigation event.
 *
 * @author Vladimir Hromada
 */
public class NavigationEvent implements PageEvent {

    /**
     * Navigation
     */
    private Navigation navigation;

    /**
     * Creates a new instance of NavigationEvent.
     *
     * @param navigation navigation
     */
    public NavigationEvent(final Navigation navigation) {
        this.navigation = navigation;
    }

    /**
     * Returns navigation.
     *
     * @return navigation
     */
    public Navigation getNavigation() {
        return navigation;
    }

}
