package cz.vhromada.catalog.web.categories.controllers;

import cz.vhromada.catalog.web.categories.mo.BookCategoryMO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing book category.
 *
 * @author Vladimir Hromada
 */
@Component("bookCategoryCancelController")
public class BookCategoryCancelController extends Controller<IModel<BookCategoryMO>> {

    @Override
    public void handle(final IModel<BookCategoryMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOK_CATEGORIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_CANCEL;
    }

}
