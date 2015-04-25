package cz.vhromada.catalog.web.categories.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.facade.BookCategoryFacade;
import cz.vhromada.catalog.facade.BookFacade;
import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.web.categories.mo.BookCategoriesMO;
import cz.vhromada.catalog.web.categories.mo.BookCategoryDataMO;
import cz.vhromada.catalog.web.categories.panels.BookCategoriesListPanel;
import cz.vhromada.catalog.web.categories.panels.BookCategoriesMenuPanel;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of book category.
 *
 * @author Vladimir Hromada
 */
@Component("bookCategoriesListController")
public class BookCategoriesListController extends Controller<Void> {

    /**
     * Facade for book categories
     */
    private BookCategoryFacade bookCategoryFacade;

    /**
     * Facade for books
     */
    private BookFacade bookFacade;

    /**
     * Creates a new instance of BookCategoriesListController.
     *
     * @param bookCategoryFacade facade for book categories
     * @param bookFacade         facade for books
     * @throws IllegalArgumentException if facade for book categories is null
     *                                  or facade for books is null
     */
    @Autowired
    public BookCategoriesListController(final BookCategoryFacade bookCategoryFacade,
            final BookFacade bookFacade) {
        Validators.validateArgumentNotNull(bookCategoryFacade, "Facade for book categories");
        Validators.validateArgumentNotNull(bookFacade, "Facade for books");

        this.bookCategoryFacade = bookCategoryFacade;
        this.bookFacade = bookFacade;
    }

    @Override
    public void handle(final Void data) {
        final List<BookCategoryDataMO> bookCategoryDataList = new ArrayList<>();
        for (final BookCategoryTO bookCategory : bookCategoryFacade.getBookCategories()) {
            final BookCategoryDataMO bookCategoryData = new BookCategoryDataMO();
            bookCategoryData.setBookCategory(bookCategory);
            bookCategoryData.setBooksCount(bookFacade.findBooksByBookCategory(bookCategory).size());
            bookCategoryDataList.add(bookCategoryData);
        }

        final BookCategoriesMO bookCategories = new BookCategoriesMO();
        bookCategories.setBookCategories(bookCategoryDataList);
        bookCategories.setBooksCount(bookCategoryFacade.getBooksCount());
        final PanelData panelData = new PanelData(BookCategoriesListPanel.ID, Model.of(bookCategories));
        final PanelData menuData = new PanelData(BookCategoriesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Books", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_LIST;
    }

}
