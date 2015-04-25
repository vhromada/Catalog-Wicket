package cz.vhromada.catalog.web.books.controllers;

import cz.vhromada.catalog.web.books.mo.BookMO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing book.
 *
 * @author Vladimir Hromada
 */
@Component("bookCancelController")
public class BookCancelController extends Controller<IModel<BookMO>> {

    @Override
    public void handle(final IModel<BookMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOKS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOKS_CANCEL;
    }

}
