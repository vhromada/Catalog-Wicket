package cz.vhromada.catalog.web.categories.panels;

import cz.vhromada.catalog.web.categories.mo.BookCategoryMO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for book category.
 *
 * @author Vladimir Hromada
 */
@Component(BookCategoryFormPanel.ID)
@Scope("prototype")
public class BookCategoryFormPanel extends AbstractFormPanel<BookCategoryMO> {

    /**
     * ID
     */
    public static final String ID = "bookCategoryFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of BookCategoryFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public BookCategoryFormPanel(final String id, final CompoundPropertyModel<BookCategoryMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> name = new RequiredTextField<>("name");
        name.setLabel(Model.of("Name"));

        final TextField<String> note = new TextField<>("note");

        getForm().add(name, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.BOOK_CATEGORIES_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<BookCategoryMO> panelForm) {
        final BookCategoryMO bookCategory = panelForm.getModelObject();
        if (bookCategory.getNote() == null) {
            bookCategory.setNote("");
        }
    }

}
