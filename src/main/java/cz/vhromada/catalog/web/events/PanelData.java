package cz.vhromada.catalog.web.events;

import cz.vhromada.validators.Validators;

import org.apache.wicket.model.IModel;

/**
 * A class represents panel data.
 */
public class PanelData {

    /**
     * ID
     */
    private String id;

    /**
     * Data
     */
    private IModel<?> data;

    /**
     * Creates a new instance of PanelData.
     *
     * @param id   ID
     * @param data data
     * @throws IllegalArgumentException if ID is null
     */
    public PanelData(final String id, final IModel<?> data) {
        Validators.validateArgumentNotNull(id, "ID");

        this.id = id;
        this.data = data;
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


}
