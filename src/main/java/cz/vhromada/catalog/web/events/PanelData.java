package cz.vhromada.catalog.web.events;

import java.io.Serializable;

import cz.vhromada.validators.Validators;

import org.apache.wicket.model.IModel;

/**
 * A class represents panel data.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public class PanelData<T extends Serializable> {

    /**
     * ID
     */
    private String id;

    /**
     * Data
     */
    private IModel<T> data;

    /**
     * Creates a new instance of PanelData.
     *
     * @param id   ID
     * @param data data
     * @throws IllegalArgumentException if ID is null
     */
    public PanelData(final String id, final IModel<T> data) {
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
    public IModel<T> getData() {
        return data;
    }

}
