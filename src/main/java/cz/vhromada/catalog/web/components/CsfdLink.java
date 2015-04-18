package cz.vhromada.catalog.web.components;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.string.Strings;

/**
 * A class represents link for ČSFD.
 *
 * @author Vladimir Hromada
 */
public class CsfdLink extends WebLink<String> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of CsfdLink.
     *
     * @param id    ID
     * @param value value
     * @throws WicketRuntimeException if ID is null
     */
    public CsfdLink(final String id, final String value) {
        super(id, value);
    }

    @Override
    protected String getBaseUrl() {
        return "http://www.csfd.cz/film/";
    }

    @Override
    protected String getLabel() {
        return "ČSFD";
    }

    @Override
    protected boolean isLinkVisible() {
        return !Strings.isEmpty(getModelObject());
    }

}
