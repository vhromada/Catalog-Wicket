package cz.vhromada.catalog.web.categories.controllers;

import cz.vhromada.catalog.facade.BookCategoryFacade;
import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving up book category.
 *
 * @author Vladimir Hromada
 */
@Component("bookCategoriesMoveUpController")
public class BookCategoriesMoveUpController extends Controller<IModel<BookCategoryTO>> {

    /**
     * Facade for book categories
     */
    private BookCategoryFacade bookCategoryFacade;

    /**
     * Creates a new instance of BookCategoriesMoveUpController.
     *
     * @param bookCategoryFacade facade for book categories
     * @throws IllegalArgumentException if facade for book categories is null
     */
    @Autowired
    public BookCategoriesMoveUpController(final BookCategoryFacade bookCategoryFacade) {
        Validators.validateArgumentNotNull(bookCategoryFacade, "Facade for book categories");

        this.bookCategoryFacade = bookCategoryFacade;
    }

    @Override
    public void handle(final IModel<BookCategoryTO> data) {
        bookCategoryFacade.moveUp(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOK_CATEGORIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_MOVE_UP;
    }

}
