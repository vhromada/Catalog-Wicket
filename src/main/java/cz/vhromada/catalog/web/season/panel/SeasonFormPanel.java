package cz.vhromada.catalog.web.season.panel;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.panel.MultipleLanguagesPanel;
import cz.vhromada.catalog.web.panel.SingleLanguagePanel;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.catalog.web.season.validation.YearsValidator;
import cz.vhromada.common.utils.Constants;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for season.
 *
 * @author Vladimir Hromada
 */
@Component(SeasonFormPanel.ID)
@Scope("prototype")
public class SeasonFormPanel extends AbstractFormPanel<SeasonMO> {

    /**
     * ID
     */
    public static final String ID = "seasonFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Maximum number
     */
    private static final int MAX_NUMBER = 100;

    /**
     * Creates a new instance of SeasonFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SeasonFormPanel(final String id, final CompoundPropertyModel<SeasonMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final NumberTextField<Integer> number = new NumberTextField<>("number");
        number.setMinimum(1)
            .setMaximum(MAX_NUMBER)
            .setLabel(Model.of("Number"))
            .setRequired(true)
            .add(RangeValidator.range(1, MAX_NUMBER))
            .add(getValidationBehavior());

        final NumberTextField<Integer> startYear = new NumberTextField<>("startYear");
        startYear.setMinimum(Constants.MIN_YEAR)
            .setMaximum(Constants.CURRENT_YEAR)
            .setLabel(Model.of("Starting year"))
            .setRequired(true)
            .add(RangeValidator.range(Constants.MIN_YEAR, Constants.CURRENT_YEAR))
            .add(getValidationBehavior());

        final NumberTextField<Integer> endYear = new NumberTextField<>("endYear");
        endYear.setMinimum(Constants.MIN_YEAR)
            .setMaximum(Constants.CURRENT_YEAR)
            .setLabel(Model.of("Ending year"))
            .setRequired(true)
            .add(RangeValidator.range(Constants.MIN_YEAR, Constants.CURRENT_YEAR))
            .add(getValidationBehavior());

        final SingleLanguagePanel language = new SingleLanguagePanel("language") {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected AjaxFormChoiceComponentUpdatingBehavior getValidationBehavior() {
                return SeasonFormPanel.this.getChoiceValidationBehavior();
            }

        };

        final MultipleLanguagesPanel subtitles = new MultipleLanguagesPanel("subtitles", new PropertyModel<>(getModelObject(), "subtitles"), "Subtitles",
            "subtitlesItem");

        final TextField<String> note = new TextField<>("note");

        getForm().add(new YearsValidator(startYear, endYear));

        getForm().add(number, startYear, endYear, language, subtitles, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.SEASONS_CANCEL;
    }

    @Override
    protected void onFormSubmit() {
        final SeasonMO season = getForm().getModelObject();
        if (season.getNote() == null) {
            season.setNote("");
        }
    }

}
