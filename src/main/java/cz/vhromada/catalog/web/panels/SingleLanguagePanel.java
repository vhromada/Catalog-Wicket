package cz.vhromada.catalog.web.panels;

import java.util.Arrays;

import cz.vhromada.catalog.common.Language;

import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;

/**
 * An abstract class represents panel for choosing one language.
 *
 * @author Vladimir Hromada
 */
public abstract class SingleLanguagePanel extends Panel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SingleLanguagePanel.
     *
     * @param id ID
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SingleLanguagePanel(final String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RadioGroup<Language> language = new RadioGroup<>("language");
        language.setLabel(Model.of("Language"))
                .setRequired(true)
                .add(getValidationBehavior());

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

        language.add(languages);
        add(language);
    }

    /**
     * Returns validation behavior.
     *
     * @return validation behavior
     */
    protected abstract AjaxFormChoiceComponentUpdatingBehavior getValidationBehavior();

}
