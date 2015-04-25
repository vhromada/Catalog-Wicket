package cz.vhromada.catalog.web.categories.controllers;

import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.web.categories.mo.BookCategoryMO;
import cz.vhromada.catalog.web.categories.panels.BookCategoriesMenuPanel;
import cz.vhromada.catalog.web.categories.panels.BookCategoryFormPanel;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating book category.
 *
 * @author Vladimir Hromada
 */
@Component("updateBookCategoryController")
public class UpdateBookCategoryController extends Controller<IModel<BookCategoryTO>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateBookCategoryController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateBookCategoryController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<BookCategoryTO> data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.BOOK_CATEGORIES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData panelData = new PanelData(BookCategoryFormPanel.ID,
                new CompoundPropertyModel<>(converter.convert(data.getObject(), BookCategoryMO.class)));
        final PanelData menuData = new PanelData(BookCategoriesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit book category", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_UPDATE;
    }

}
