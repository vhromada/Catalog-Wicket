package cz.vhromada.catalog.web.books.panels;

import java.util.ArrayList;
import java.util.Arrays;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.web.books.mo.BookMO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

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
     * ID of feedback message for languages
     */
    private static final int LANGUAGES_FEEDBACK_MESSAGE_ID = 1;

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

        final ListView<Language> languages = new ListView<Language>("languages", Arrays.asList(Language.CZ, Language.EN)) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Language> item) {
                final BookMO book = BookFormPanel.this.getModelObject();
                if (book.getLanguages() == null) {
                    book.setLanguages(new ArrayList<Language>());
                }

                final Language languageValue = item.getModelObject();

                final IModel<Boolean> languageModel = new Model<>();
                languageModel.setObject(book.getLanguages().contains(languageValue));

                final AjaxCheckBox language = new AjaxCheckBox("language", languageModel) {

                    /**
                     * SerialVersionUID
                     */
                    private static final long serialVersionUID = 1L;

                    @Override
                    protected void onUpdate(final AjaxRequestTarget target) {
                        if (getModelObject()) {
                            book.getLanguages().add(languageValue);
                        } else {
                            book.getLanguages().remove(languageValue);
                        }
                    }

                };
                language.setMarkupId("language" + (item.getIndex() + 1));

                final Label languageLabel = new Label("languageLabel", item.getModel());

                item.add(language, languageLabel);
            }

        };

        final TextField<String> note = new TextField<>("note");

        getForm().add(author, title, languages, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.BOOKS_CANCEL;
    }

    @Override
    protected void onFormValidation(final Form<BookMO> panelForm) {
        final BookMO book = panelForm.getModelObject();
        if (CollectionUtils.isEmpty(book.getLanguages())) {
            addError(LANGUAGES_FEEDBACK_MESSAGE_ID, "Czech or english language must be selected.");
        } else {
            clearError(LANGUAGES_FEEDBACK_MESSAGE_ID);
        }
    }

    @Override
    protected void onFormSubmit(final Form<BookMO> panelForm) {
        final BookMO book = panelForm.getModelObject();
        if (book.getNote() == null) {
            book.setNote("");
        }
    }

}
