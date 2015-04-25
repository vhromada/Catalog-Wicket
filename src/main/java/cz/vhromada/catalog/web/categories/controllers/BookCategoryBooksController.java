package cz.vhromada.catalog.web.categories.controllers;

import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing book category songs.
 *
 * @author Vladimir Hromada
 */
@Component("bookCategoryBooksController")
public class BookCategoryBooksController extends Controller<IModel<BookCategoryTO>> {

    /**
     * Name of book category attribute in session
     */
    public static final String BOOK_CATEGORIES_ATTRIBUTE = "bookCategory";

    @Override
    public void handle(final IModel<BookCategoryTO> data) {
        final CatalogSession session = CatalogSession.getSession();
        session.setAttribute(BOOK_CATEGORIES_ATTRIBUTE, data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOKS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_BOOKS;
    }

}
