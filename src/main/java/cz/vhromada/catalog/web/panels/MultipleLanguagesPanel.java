package cz.vhromada.catalog.web.panels;

import java.util.Arrays;
import java.util.List;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.validators.Validators;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;

/**
 * A class represents panel for choosing multiple language.
 *
 * @author Vladimir Hromada
 */
public class MultipleLanguagesPanel extends Panel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Model with languages
     */
    private IModel<List<Language>> model;

    /**
     * Label
     */
    private String label;

    /**
     * Item ID
     */
    private String itemId;

    /**
     * Creates a new instance of MultipleLanguagesPanel.
     *
     * @param id ID
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MultipleLanguagesPanel(final String id) {
        super(id);

        this.label = "Languages";
        this.itemId = "language";
    }

    /**
     * Creates a new instance of MultipleLanguagesPanel.
     *
     * @param id     ID
     * @param model  model with languages
     * @param label  label
     * @param itemId item ID
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     * @throws IllegalArgumentException                 if model with languages is null
     *                                                  or label is null
     *                                                  or item ID is null
     */
    public MultipleLanguagesPanel(final String id, final IModel<List<Language>> model, final String label, final String itemId) {
        super(id);

        Validators.validateArgumentNotNull(model, "Model");
        Validators.validateArgumentNotNull(label, "Label");
        Validators.validateArgumentNotNull(itemId, "Item ID");

        this.model = model;
        this.label = label;
        this.itemId = itemId;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Label languagesLabel = new Label("languagesLabel", label);

        final CheckGroup<Language> languages = new CheckGroup<>("languages", model);
        languages.setLabel(Model.of(label))
                .setRequired(model == null);

        final ListView<Language> languagesList = new ListView<Language>("languagesList", Arrays.asList(Language.CZ, Language.EN)) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Language> item) {
                final Check<Language> language = new Check<>("language", item.getModel());
                language.setMarkupId(itemId + (item.getIndex() + 1));

                final Label languageLabel = new Label("languageLabel", item.getModel());

                item.add(language, languageLabel);
            }

        };

        languages.add(languagesList);
        add(languagesLabel, languages);
    }

}
