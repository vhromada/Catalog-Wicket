package cz.vhromada.catalog.web.panels;

import java.util.Arrays;
import java.util.List;

import cz.vhromada.catalog.common.Language;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.util.Assert;

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

        Assert.notNull(model, "Model mustn't be null.");
        Assert.notNull(label, "Label mustn't be null.");
        Assert.notNull(itemId, "Item ID mustn't be null.");

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

        final ListView<Language> languagesList = new LanguagesListView("languagesList", Arrays.asList(Language.CZ, Language.EN), itemId);

        languages.add(languagesList);
        add(languagesLabel, languages);
    }

    /**
     * A class represents list view with languages.
     */
    private static final class LanguagesListView extends ListView<Language> {

        /**
         * SerialVersionUID
         */
        private static final long serialVersionUID = 1L;

        /**
         * Item ID
         */
        private String itemId;

        /**
         * Creates a new instance of LanguagesListView.
         *
         * @param id   ID
         * @param list list of data
         * @throws org.apache.wicket.WicketRuntimeException if ID is null
         */
        LanguagesListView(final String id, final List<Language> list, final String itemId) {
            super(id, list);

            this.itemId = itemId;
        }

        @Override
        protected void populateItem(final ListItem<Language> item) {
            final Check<Language> language = new Check<>("language", item.getModel());
            language.setMarkupId(itemId + (item.getIndex() + 1));

            final Label languageLabel = new Label("languageLabel", item.getModel());

            item.add(language, languageLabel);
        }

    }

}
