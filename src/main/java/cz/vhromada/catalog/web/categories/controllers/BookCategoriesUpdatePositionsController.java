package cz.vhromada.catalog.web.categories.controllers;

import cz.vhromada.catalog.facade.BookCategoryFacade;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating positions.
 *
 * @author Vladimir Hromada
 */
@Component("bookCategoriesUpdatePositionsController")
public class BookCategoriesUpdatePositionsController extends Controller<Void> {

    /**
     * Facade for book categories
     */
    private BookCategoryFacade bookCategoryFacade;

    /**
     * Creates a new instance of BookCategoriesUpdatePositionsController.
     *
     * @param bookCategoryFacade facade for book categories
     * @throws IllegalArgumentException if facade for book categories is null
     */
    @Autowired
    public BookCategoriesUpdatePositionsController(final BookCategoryFacade bookCategoryFacade) {
        Validators.validateArgumentNotNull(bookCategoryFacade, "Facade for book categories");

        this.bookCategoryFacade = bookCategoryFacade;
    }

    @Override
    public void handle(final Void data) {
        bookCategoryFacade.updatePositions();

        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOK_CATEGORIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_UPDATE_POSITION;
    }

}