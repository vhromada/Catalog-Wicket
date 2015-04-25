package cz.vhromada.catalog.web.books.controllers;

import cz.vhromada.catalog.web.books.mo.BookMO;
import cz.vhromada.catalog.web.books.panels.BookFormPanel;
import cz.vhromada.catalog.web.books.panels.BooksMenuPanel;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding book.
 *
 * @author Vladimir Hromada
 */
@Component("addBookController")
public class AddBookController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.BOOKS_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData panelData = new PanelData(BookFormPanel.ID, new CompoundPropertyModel<>(new BookMO()));
        final PanelData menuData = new PanelData(BooksMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Add book", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOKS_ADD;
    }

}
