package cz.vhromada.catalog.web.books.panels;

import cz.vhromada.catalog.web.categories.panels.BookCategoriesMenuPanel;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for books.
 *
 * @author Vladimir Hromada
 */
@Component(BooksMenuPanel.ID)
@Scope("prototype")
public class BooksMenuPanel extends BasePanel<Void> {

    /**
     * ID
     */
    public static final String ID = "booksMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of BooksMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public BooksMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Panel bookCategoriesMenuPanel = getPanel(BookCategoriesMenuPanel.ID, "bookCategoriesMenuPanel", null);

        final AjaxFlowLink<Void> allBooks = new AjaxFlowLink<>("allBooks", CatalogFlow.BOOKS_LIST);

        final AjaxFlowLink<Void> addBook = new AjaxFlowLink<>("addBook", CatalogFlow.BOOKS_ADD);

        add(bookCategoriesMenuPanel, allBooks, addBook);
    }

}
