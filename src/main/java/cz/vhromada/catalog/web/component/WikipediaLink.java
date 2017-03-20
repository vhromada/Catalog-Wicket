package cz.vhromada.catalog.web.component;

import org.apache.wicket.util.string.Strings;
import org.springframework.util.Assert;

/**
 * A class represents link for Wikipedia.
 *
 * @author Vladimir Hromada
 */
public class WikipediaLink extends WebLink<String> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Country
     */
    private Country country;

    /**
     * Creates a new instance of WikipediaLink.
     *
     * @param id      ID
     * @param value   value
     * @param country country
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     * @throws IllegalArgumentException                 if country is null
     */
    public WikipediaLink(final String id, final String value, final Country country) {
        super(id, value);

        Assert.notNull(country, "Country mustn't be null.");

        this.country = country;
    }

    @Override
    protected String getBaseUrl() {
        return "http://" + country.getUrl() + ".wikipedia.org/wiki/";
    }

    @Override
    protected String getLabel() {
        return country.getLabel() + " Wikipedia";
    }

    @Override
    protected boolean isLinkVisible() {
        return !Strings.isEmpty(getModelObject());
    }

    /**
     * An enumeration represents country.
     */
    public enum Country {

        CZ("cz", "Czech"),
        EN("en", "English");

        /**
         * URL
         */
        private final String url;

        /**
         * Label
         */
        private final String label;

        /**
         * Creates a new instance of Country.
         *
         * @param url   URL
         * @param label label
         */
        Country(final String url, final String label) {
            this.url = url;
            this.label = label;
        }

        /**
         * Returns URL.
         *
         * @return URL
         */
        public String getUrl() {
            return url;
        }

        /**
         * Returns label.
         *
         * @return label
         */
        public String getLabel() {
            return label;
        }

    }

}
