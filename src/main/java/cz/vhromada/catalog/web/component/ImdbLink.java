package cz.vhromada.catalog.web.component;

import org.apache.commons.lang3.StringUtils;

/**
 * A class represents link for IMDB.
 *
 * @author Vladimir Hromada
 */
public class ImdbLink extends WebLink<String> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * IMDB code value
     */
    private Integer value;

    /**
     * Creates a new instance of ImdbLink.
     *
     * @param id    ID
     * @param value value
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ImdbLink(final String id, final Integer value) {
        super(id, StringUtils.leftPad(Integer.toString(value), 7, "0"));

        this.value = value;
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
        return value > 0;
    }

}
