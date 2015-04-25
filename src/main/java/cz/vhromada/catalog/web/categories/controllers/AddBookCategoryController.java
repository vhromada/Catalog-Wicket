package cz.vhromada.catalog.web.categories.controllers;

import cz.vhromada.catalog.web.categories.mo.BookCategoryMO;
import cz.vhromada.catalog.web.categories.panels.BookCategoriesMenuPanel;
import cz.vhromada.catalog.web.categories.panels.BookCategoryFormPanel;
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
 * A class represents controller for showing form for adding book category.
 *
 * @author Vladimir Hromada
 */
@Component("addBookCategoryController")
public class AddBookCategoryController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.BOOK_CATEGORIES_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData panelData = new PanelData(BookCategoryFormPanel.ID, new CompoundPropertyModel<>(new BookCategoryMO()));
        final PanelData menuData = new PanelData(BookCategoriesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Add book category", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.BOOK_CATEGORIES_ADD;
    }

}
