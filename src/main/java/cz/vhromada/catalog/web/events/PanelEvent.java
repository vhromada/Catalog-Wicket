package cz.vhromada.catalog.web.events;

import org.apache.wicket.model.IModel;

/**
 * A class represents event for calling showing panel.
 *
 * @author Vladimir Hromada
 */
public class PanelEvent implements PageEvent {

    /**
     * ID
     */
    private String id;

    /**
     * Data
     */
    private IModel<?> data;

    /**
     * Title
     */
    private String title;

    /**
     * Navigation
     */
    private Navigation navigation;

    /**
     * Creates a new instance of PanelEvent.
     *
     * @param id         ID
     * @param title      title
     * @param navigation navigation
     */
    public PanelEvent(final String id, final String title, final Navigation navigation) {
        this(id, null, title, navigation);
    }

    /**
     * Creates a new instance of PanelEvent.
     *
     * @param id         ID
     * @param data       data
     * @param title      title
     * @param navigation navigation
     */
    public PanelEvent(final String id, final IModel<?> data, final String title, final Navigation navigation) {
        this.id = id;
        this.data = data;
        this.title = title;
        this.navigation = navigation;
    }

    /**
     * Returns ID.
     *
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * Returns data.
     *
     * @return data
     */
    public IModel<?> getData() {
        return data;
    }

    /**
     * Returns title.
     *
     * @return title
     */
    public String getTitle() {
        return title;
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
