package cz.vhromada.catalog.web.component;

import java.io.Serializable;

import org.apache.wicket.markup.html.link.ExternalLink;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.Model;

/**
 * An abstract class represents web link.
 *
 * @param <T> type of panel's model object
 * @author Vladimir Hromada
 */
public abstract class WebLink<T extends Serializable> extends GenericPanel<T> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of WebLink.
     *
     * @param id    ID
     * @param value value
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public WebLink(final String id, final T value) {
        super(id, Model.of(value));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new ExternalLink("link", getBaseUrl() + getModelObject(), getLabel()).setVisible(isLinkVisible()));
    }

    /**
     * Returns base link URL.
     *
     * @return base link URL
     */
    protected abstract String getBaseUrl();

    /**
     * Returns link label.
     *
     * @return link label
     */
    protected abstract String getLabel();

    /**
     * Returns true if link is visible.
     *
     * @return true if link is visible
     */
    protected abstract boolean isLinkVisible();

}
