package cz.vhromada.catalog.web.books.controllers;

import cz.vhromada.catalog.facade.BookFacade;
import cz.vhromada.catalog.facade.to.BookTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving down book.
 *
 * @author Vladimir Hromada
 */
@Component("booksMoveDownController")
public class BooksMoveDownController extends Controller<IModel<BookTO>> {

    /**
     * Facade for books
     */
    private BookFacade bookFacade;

    /**
     * Creates a new instance of BooksMoveDownController.
     *
     * @param bookFacade facade for books
     * @throws IllegalArgumentException if facade for books is null
     */
    @Autowired
    public BooksMoveDownController(final BookFacade bookFacade) {
        Validators.validateArgumentNotNull(bookFacade, "Facade for books");

        this.bookFacade = bookFacade;
    }

    @Override
    public void handle(final IModel<BookTO> data) {
        bookFacade.moveDown(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOKS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOKS_MOVE_DOWN;
    }

}
