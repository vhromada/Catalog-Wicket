package cz.vhromada.catalog.web.seasons.panels;

import java.util.Arrays;

import cz.vhromada.catalog.commons.Constants;
import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.catalog.web.seasons.validation.YearsValidator;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
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
                .add(RangeValidator.range(1, MAX_NUMBER));

        final NumberTextField<Integer> startYear = new NumberTextField<>("startYear");
        startYear.setMinimum(Constants.MIN_YEAR)
                .setMaximum(Constants.CURRENT_YEAR)
                .setLabel(Model.of("Starting year"))
                .setRequired(true)
                .add(RangeValidator.range(Constants.MIN_YEAR, Constants.CURRENT_YEAR));

        final NumberTextField<Integer> endYear = new NumberTextField<>("endYear");
        endYear.setMinimum(Constants.MIN_YEAR)
                .setMaximum(Constants.CURRENT_YEAR)
                .setLabel(Model.of("Ending year"))
                .setRequired(true)
                .add(RangeValidator.range(Constants.MIN_YEAR, Constants.CURRENT_YEAR));

        final RadioGroup<Language> language = new RadioGroup<>("language");
        language.setLabel(Model.of("Language"))
                .setRequired(true);

        final ListView<Language> languages = new ListView<Language>("languages", Arrays.asList(Language.values())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Language> item) {
                final Radio<Language> languageItem = new Radio<>("languageItem", item.getModel());
                languageItem.setMarkupId("language" + (item.getIndex() + 1));

                final Label languageItemLabel = new Label("languageItemLabel", item.getModel());

                item.add(languageItem, languageItemLabel);
            }

        };

        final CheckGroup<Language> subtitles = new CheckGroup<>("subtitles");

        final ListView<Language> subtitlesList = new ListView<Language>("subtitlesList", Arrays.asList(Language.CZ, Language.EN)) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Language> item) {
                final Check<Language> subtitlesItem = new Check<>("subtitlesItem", item.getModel());
                subtitlesItem.setMarkupId("subtitlesItem" + (item.getIndex() + 1));

                final Label subtitlesItemLabel = new Label("subtitlesItemLabel", item.getModel());

                item.add(subtitlesItem, subtitlesItemLabel);
            }

        };

        final TextField<String> note = new TextField<>("note");

        getForm().add(new YearsValidator(startYear, endYear));

        language.add(languages);
        subtitles.add(subtitlesList);
        getForm().add(number, startYear, endYear, language, subtitles, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.SEASONS_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<SeasonMO> panelForm) {
        final SeasonMO season = panelForm.getModelObject();
        if (season.getNote() == null) {
            season.setNote("");
        }
    }

}
