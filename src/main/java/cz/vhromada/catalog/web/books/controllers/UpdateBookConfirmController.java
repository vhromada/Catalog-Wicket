package cz.vhromada.catalog.web.books.controllers;

import cz.vhromada.catalog.facade.BookFacade;
import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.facade.to.BookTO;
import cz.vhromada.catalog.web.books.mo.BookMO;
import cz.vhromada.catalog.web.categories.controllers.BookCategoryBooksController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating book.
 *
 * @author Vladimir Hromada
 */
@Component("updateBookConfirmController")
public class UpdateBookConfirmController extends Controller<IModel<BookMO>> {

    /**
     * Facade for books
     */
    private BookFacade bookFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateBookConfirmController.
     *
     * @param bookFacade facade for books
     * @param converter  converter
     * @throws IllegalArgumentException if facade for books is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateBookConfirmController(final BookFacade bookFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(bookFacade, "Facade for books");
        Validators.validateArgumentNotNull(converter, "converter");

        this.bookFacade = bookFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<BookMO> data) {
        final BookCategoryTO bookCategory = CatalogApplication.getSessionAttribute(BookCategoryBooksController.BOOK_CATEGORY_ATTRIBUTE);
        final BookTO book = converter.convert(data.getObject(), BookTO.class);
        book.setBookCategory(bookCategory);
        bookFacade.update(book);

        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOKS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOKS_UPDATE_CONFIRM;
    }

}
