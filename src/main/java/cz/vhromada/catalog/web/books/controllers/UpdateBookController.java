package cz.vhromada.catalog.web.books.controllers;

import java.util.ArrayList;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.facade.to.BookTO;
import cz.vhromada.catalog.web.books.mo.BookMO;
import cz.vhromada.catalog.web.books.panels.BookFormPanel;
import cz.vhromada.catalog.web.books.panels.BooksMenuPanel;
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
 * A class represents controller for showing form for updating book.
 *
 * @author Vladimir Hromada
 */
@Component("updateBookController")
public class UpdateBookController extends Controller<IModel<BookTO>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateBookController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateBookController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<BookTO> data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.BOOKS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final BookMO book = converter.convert(data.getObject(), BookMO.class);
        if (book.getLanguages() == null) {
            book.setLanguages(new ArrayList<Language>());
        }
        final PanelData panelData = new PanelData(BookFormPanel.ID, new CompoundPropertyModel<>(book));
        final PanelData menuData = new PanelData(BooksMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit book", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOKS_UPDATE;
    }

}
