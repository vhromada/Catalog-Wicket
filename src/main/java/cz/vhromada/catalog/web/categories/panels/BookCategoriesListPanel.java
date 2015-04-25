package cz.vhromada.catalog.web.categories.panels;

import cz.vhromada.catalog.facade.to.BookCategoryTO;
import cz.vhromada.catalog.web.categories.mo.BookCategoriesMO;
import cz.vhromada.catalog.web.categories.mo.BookCategoryDataMO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of book category.
 *
 * @author Vladimir Hromada
 */
@Component(BookCategoriesListPanel.ID)
@Scope("prototype")
public class BookCategoriesListPanel extends BasePanel<BookCategoriesMO> {

    /**
     * ID
     */
    public static final String ID = "bookCategoryListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of BookCategoriesListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public BookCategoriesListPanel(final String id, final IModel<BookCategoriesMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer bookCategoriesTable = new WebMarkupContainer("bookCategoriesTable");
        bookCategoriesTable.setVisible(!getModelObject().getBookCategories().isEmpty());

        final ListView<BookCategoryDataMO> bookCategories = new ListView<BookCategoryDataMO>("bookCategories",
                Model.ofList(getModelObject().getBookCategories())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<BookCategoryDataMO> item) {
                final BookCategoryDataMO bookCategoryData = item.getModelObject();
                final BookCategoryTO bookCategory = bookCategoryData.getBookCategory();

                final Label name = new Label("name", bookCategory.getName());

                final Label booksCount = new Label("booksCount", bookCategoryData.getBooksCount());

                final Label note = new Label("note", bookCategory.getNote());

                final AjaxFlowLink<BookCategoryTO> books = new AjaxFlowLink<>("books", Model.of(bookCategory), CatalogFlow.BOOK_CATEGORIES_BOOKS);

                final AjaxFlowLink<BookCategoryTO> moveUp = new AjaxFlowLink<>("moveUp", Model.of(bookCategory), CatalogFlow.BOOK_CATEGORIES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<BookCategoryTO> moveDown = new AjaxFlowLink<>("moveDown", Model.of(bookCategory), CatalogFlow.BOOK_CATEGORIES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<BookCategoryTO> duplicate = new AjaxFlowLink<>("duplicate", Model.of(bookCategory), CatalogFlow.BOOK_CATEGORIES_DUPLICATE);

                final AjaxFlowLink<BookCategoryTO> edit = new AjaxFlowLink<>("edit", Model.of(bookCategory), CatalogFlow.BOOK_CATEGORIES_UPDATE);

                final AjaxFlowLink<BookCategoryTO> remove = new AjaxFlowLink<>("remove", Model.of(bookCategory), CatalogFlow.BOOK_CATEGORIES_REMOVE);

                item.add(name, booksCount, note, books, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noBookCategories = new WebMarkupContainer("noBookCategories");
        noBookCategories.setVisible(getModelObject().getBookCategories().isEmpty());

        final Label count = new Label("count", getModelObject().getBookCategories().size());

        final Label booksCount = new Label("booksCount", getModelObject().getBooksCount());

        bookCategoriesTable.add(bookCategories);
        add(bookCategoriesTable, noBookCategories, count, booksCount);
    }

}
