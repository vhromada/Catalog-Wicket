package cz.vhromada.catalog.web.categories.controllers;

import cz.vhromada.catalog.facade.BookCategoryFacade;
import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.web.categories.mo.BookCategoryMO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for adding book category.
 *
 * @author Vladimir Hromada
 */
@Component("addBookCategoryConfirmController")
public class AddBookCategoryConfirmController extends Controller<IModel<BookCategoryMO>> {

    /**
     * Facade for book categories
     */
    private BookCategoryFacade bookCategoryFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddBookCategoryConfirmController.
     *
     * @param bookCategoryFacade facade for book categories
     * @param converter          converter
     * @throws IllegalArgumentException if facade for book categories is null
     *                                  or converter is null
     */
    @Autowired
    public AddBookCategoryConfirmController(final BookCategoryFacade bookCategoryFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(bookCategoryFacade, "Facade for book categories");
        Validators.validateArgumentNotNull(converter, "converter");

        this.bookCategoryFacade = bookCategoryFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<BookCategoryMO> data) {
        bookCategoryFacade.add(converter.convert(data.getObject(), BookCategoryTO.class));

        getUi().fireEvent(new ControllerEvent(CatalogFlow.BOOK_CATEGORIES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_ADD_CONFIRM;
    }

}
