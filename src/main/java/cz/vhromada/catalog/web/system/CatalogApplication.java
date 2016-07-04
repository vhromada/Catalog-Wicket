package cz.vhromada.catalog.web.system;

import cz.vhromada.catalog.web.pages.CatalogPage;

import org.apache.wicket.Session;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.request.Request;
import org.apache.wicket.request.Response;
import org.apache.wicket.request.resource.JavaScriptResourceReference;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;

/**
 * A class represents application.
 *
 * @author Vladimir Hromada
 */
public class CatalogApplication extends WebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return CatalogPage.class;
    }

    @Override
    public Session newSession(final Request request, final Response response) {
        return new CatalogSession(request);
    }

    /**
     * Returns session.
     *
     * @return session
     */
    public static CatalogSession getSession() {
        return CatalogSession.getSession();
    }

    /**
     * Returns attribute from session.
     *
     * @param name attribute name
     * @param <T>  type of attribute
     * @return attribute from session
     */
    @SuppressWarnings("unchecked")
    public static <T> T getSessionAttribute(final String name) {
        return (T) getSession().getAttribute(name);
    }

    @Override
    protected void init() {
        super.init();

        getMarkupSettings().setDefaultMarkupEncoding("UTF-8");
        getRequestCycleSettings().setResponseRequestEncoding("UTF-8");

        getMarkupSettings().setStripWicketTags(true);

        getComponentInstantiationListeners().add(new SpringComponentInjector(this));

        getJavaScriptLibrarySettings().setJQueryReference(new JavaScriptResourceReference(CatalogPage.class, "js/jquery-2.1.3.js"));

        if (usesDevelopmentConfig()) {
            getDebugSettings().setDevelopmentUtilitiesEnabled(true);
        }
    }

}
