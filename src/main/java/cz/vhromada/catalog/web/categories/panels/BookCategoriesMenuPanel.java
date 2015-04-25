package cz.vhromada.catalog.web.categories.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for book category.
 *
 * @author Vladimir Hromada
 */
@Component(BookCategoriesMenuPanel.ID)
@Scope("prototype")
public class BookCategoriesMenuPanel extends BasePanel<Void> {

    /**
     * ID
     */
    public static final String ID = "bookCategoriesMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of BookCategoriesMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public BookCategoriesMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allBookCategory = new AjaxFlowLink<>("allBookCategories", CatalogFlow.BOOK_CATEGORIES_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.BOOK_CATEGORIES_NEW_DATA);

        final AjaxFlowLink<Void> addBookCategory = new AjaxFlowLink<>("addBookCategory", CatalogFlow.BOOK_CATEGORIES_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", CatalogFlow.BOOK_CATEGORIES_UPDATE_POSITION);

        add(allBookCategory, newData, addBookCategory, updatePositions);
    }

}
