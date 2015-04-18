package cz.vhromada.catalog.web.components;

import cz.vhromada.validators.Validators;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.util.string.Strings;

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
     * @throws WicketRuntimeException   if ID is null
     * @throws IllegalArgumentException if country is null
     */
    public WikipediaLink(final String id, final String value, final Country country) {
        super(id, value);

        Validators.validateArgumentNotNull(country, "Country");

        this.country = country;
    }

    @Override
    protected String getBaseUrl() {
        return "http://" + country.getValue() + ".wikipedia.org/wiki/";
    }

    @Override
    protected String getLabel() {
        return "Wikipedia";
    }

    @Override
    protected boolean isLinkVisible() {
        return !Strings.isEmpty(getModelObject());
    }

    /**
     * An enumeration represents country.
     */
    public enum Country {

        CZ("cz"),
        EN("en");

        /**
         * Value
         */
        private final String value;

        /**
         * Creates a new instance of Country.
         *
         * @param value value
         */
        Country(final String value) {
            this.value = value;
        }

        /**
         * Returns value.
         *
         * @return value
         */
        public String getValue() {
            return value;
        }

    }

}
