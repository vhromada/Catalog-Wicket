package cz.vhromada.catalog.web.system;

import org.apache.wicket.Session;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.request.Request;

/**
 * A class represents session.
 *
 * @author Vladimir Hromada
 */
public class CatalogSession extends WebSession {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of CatalogSession.
     *
     * @param request current request
     */
    public CatalogSession(final Request request) {
        super(request);
    }

    /**
     * Returns session.
     *
     * @return session
     */
    public static CatalogSession get() {
        return (CatalogSession) Session.get();
    }

}
