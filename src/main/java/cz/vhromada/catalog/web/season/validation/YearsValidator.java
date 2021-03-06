package cz.vhromada.catalog.web.season.validation;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.FormComponent;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.validation.AbstractFormValidator;
import org.springframework.util.Assert;

/**
 * A class represents validator for years.
 *
 * @author Vladimir Hromada
 */
public class YearsValidator extends AbstractFormValidator {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Field for starting year
     */
    private NumberTextField<Integer> startYear;

    /**
     * Field for ending year
     */
    private NumberTextField<Integer> endYear;

    /**
     * Creates a new instance of YearsValidator.
     *
     * @param startYear field for starting year
     * @param endYear   field for ending year
     * @throws IllegalArgumentException if field for starting year is null
     *                                  or field for ending year is null
     */
    public YearsValidator(final NumberTextField<Integer> startYear, final NumberTextField<Integer> endYear) {
        Assert.notNull(startYear, "Starting year mustn't be null.");
        Assert.notNull(endYear, "Ending year mustn't be null.");

        this.startYear = startYear;
        this.endYear = endYear;
    }

    @Override
    public FormComponent<?>[] getDependentFormComponents() {
        return new FormComponent[]{ startYear, endYear };
    }

    @Override
    public void validate(final Form<?> form) {
        final Integer startingYear = startYear.getConvertedInput();
        final Integer endingYear = endYear.getConvertedInput();
        if (startingYear != null && endingYear != null && startingYear > endingYear) {
            error(startYear);
        }
    }

}
