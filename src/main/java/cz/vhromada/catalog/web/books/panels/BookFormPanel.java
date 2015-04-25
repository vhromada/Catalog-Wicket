package cz.vhromada.catalog.web.books.panels;

import java.util.Arrays;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.web.books.mo.BookMO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for book.
 *
 * @author Vladimir Hromada
 */
@Component(BookFormPanel.ID)
@Scope("prototype")
public class BookFormPanel extends AbstractFormPanel<BookMO> {

    /**
     * ID
     */
    public static final String ID = "bookFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of BookFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public BookFormPanel(final String id, final CompoundPropertyModel<BookMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> author = new RequiredTextField<>("author");
        author.setLabel(Model.of("Author"));

        final RequiredTextField<String> title = new RequiredTextField<>("title");
        title.setLabel(Model.of("Title"));

        final CheckGroup<Language> languages = new CheckGroup<>("languages");
        languages.setLabel(Model.of("Languages"))
                .setRequired(true);

        final ListView<Language> languagesList = new ListView<Language>("languagesList", Arrays.asList(Language.CZ, Language.EN)) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Language> item) {
                final Check<Language> language = new Check<>("language", item.getModel());
                language.setMarkupId("language" + (item.getIndex() + 1));

                final Label languageLabel = new Label("languageLabel", item.getModel());

                item.add(language, languageLabel);
            }

        };

        final TextField<String> note = new TextField<>("note");

        languages.add(languagesList);
        getForm().add(author, title, languages, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.BOOKS_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<BookMO> panelForm) {
        final BookMO book = panelForm.getModelObject();
        if (book.getNote() == null) {
            book.setNote("");
        }
    }

}
