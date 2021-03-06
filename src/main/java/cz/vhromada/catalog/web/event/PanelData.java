package cz.vhromada.catalog.web.event;

import org.apache.wicket.model.IModel;
import org.springframework.util.Assert;

/**
 * A class represents panel data.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public class PanelData<T> {

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
        Assert.notNull(id, "ID mustn't be null.");

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
