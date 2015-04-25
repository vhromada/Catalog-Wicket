package cz.vhromada.catalog.web.books.controllers;

import cz.vhromada.catalog.facade.BookFacade;
import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.web.books.panels.BooksListPanel;
import cz.vhromada.catalog.web.books.panels.BooksMenuPanel;
import cz.vhromada.catalog.web.categories.controllers.BookCategoryBooksController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of books.
 *
 * @author Vladimir Hromada
 */
@Component("booksListController")
public class BooksListController extends Controller<Void> {

    /**
     * Facade for books
     */
    private BookFacade bookFacade;

    /**
     * Creates a new instance of BooksListController.
     *
     * @param bookFacade facade for books
     * @throws IllegalArgumentException if facade for books is null
     */
    @Autowired
    public BooksListController(final BookFacade bookFacade) {
        Validators.validateArgumentNotNull(bookFacade, "Facade for books");

        this.bookFacade = bookFacade;
    }

    @Override
    public void handle(final Void data) {
        final BookCategoryTO bookCategory = CatalogApplication.getSessionAttribute(BookCategoryBooksController.BOOK_CATEGORIES_ATTRIBUTE);
        final PanelData panelData = new PanelData(BooksListPanel.ID, Model.ofList(bookFacade.findBooksByBookCategory(bookCategory)));
        final PanelData menuData = new PanelData(BooksMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Books", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOKS_LIST;
    }

}
