package cz.vhromada.catalog.web.events;

import cz.vhromada.validators.Validators;

/**
 * A class represents event for calling showing panel.
 *
 * @author Vladimir Hromada
 */
public class PanelEvent implements PageEvent {

    /**
     * Panel
     */
    private PanelData panel;

    /**
     * Title
     */
    private String title;

    /**
     * Menu
     */
    private PanelData menu;

    /**
     * Creates a new instance of PanelEvent.
     *
     * @param panel panel
     * @param title title
     * @throws IllegalArgumentException if panel is null
     *                                  or title is null
     */
    public PanelEvent(final PanelData panel, final String title) {
        this(panel, title, null);
    }

    /**
     * Creates a new instance of PanelEvent.
     *
     * @param panel panel
     * @param title title
     * @param menu  menu
     * @throws IllegalArgumentException if panel is null
     *                                  or title is null
     */
    public PanelEvent(final PanelData panel, final String title, final PanelData menu) {
        Validators.validateArgumentNotNull(panel, "Panel");
        Validators.validateArgumentNotNull(title, "Title");

        this.panel = panel;
        this.title = title;
        this.menu = menu;
    }

    /**
     * Returns panel.
     *
     * @return panel
     */
    public PanelData getPanel() {
        return panel;
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
     * Returns menu.
     *
     * @return menu
     */
    public PanelData getMenu() {
        return menu;
    }

}
