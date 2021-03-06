package cz.vhromada.catalog.web.system;

import cz.vhromada.catalog.web.page.CatalogPage;

import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.protocol.http.WebApplication;
import org.apache.wicket.protocol.http.WebSession;
import org.apache.wicket.spring.injection.annot.SpringComponentInjector;
import org.springframework.stereotype.Component;

/**
 * A class represents application.
 *
 * @author Vladimir Hromada
 */
@Component
public class CatalogApplication extends WebApplication {

    @Override
    public Class<? extends WebPage> getHomePage() {
        return CatalogPage.class;
    }

    /**
     * Returns session.
     *
     * @return session
     */
    public static WebSession getSession() {
        return WebSession.get();
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

        if (usesDevelopmentConfig()) {
            getDebugSettings().setDevelopmentUtilitiesEnabled(true);
        }
    }

}
