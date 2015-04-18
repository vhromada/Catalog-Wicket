package cz.vhromada.catalog.web.components;

import org.apache.wicket.WicketRuntimeException;

/**
 * A class represents link for IMDB.
 *
 * @author Vladimir Hromada
 */
public class ImdbLink extends WebLink<Integer> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ImdbLink.
     *
     * @param id    ID
     * @param value value
     * @throws WicketRuntimeException if ID is null
     */
    public ImdbLink(final String id, final Integer value) {
        super(id, value);
    }

    @Override
    protected String getBaseUrl() {
        return "http://www.imdb.com/title/tt";
    }

    @Override
    protected String getLabel() {
        return "IMDB";
    }

    @Override
    protected boolean isLinkVisible() {
        return getModelObject() > 0;
    }
}