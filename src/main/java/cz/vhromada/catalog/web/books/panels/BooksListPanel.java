package cz.vhromada.catalog.web.books.panels;

import java.util.List;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.facade.to.BookTO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of books.
 *
 * @author Vladimir Hromada
 */
@Component(BooksListPanel.ID)
@Scope("prototype")
public class BooksListPanel extends BasePanel<List<BookTO>> {

    /**
     * ID
     */
    public static final String ID = "booksListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of BooksListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public BooksListPanel(final String id, final IModel<List<BookTO>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer booksTable = new WebMarkupContainer("booksTable");
        booksTable.setVisible(!getModelObject().isEmpty());

        final ListView<BookTO> books = new ListView<BookTO>("books", getModel()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<BookTO> item) {
                final BookTO book = item.getModelObject();

                final Label author = new Label("author", book.getAuthor());

                final Label title = new Label("title", book.getTitle());

                final Label languages = new Label("languages", getLanguages(book));

                final Label note = new Label("note", book.getNote());

                final AjaxFlowLink<BookTO> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.BOOKS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<BookTO> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.BOOKS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<BookTO> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.BOOKS_DUPLICATE);

                final AjaxFlowLink<BookTO> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.BOOKS_UPDATE);

                final AjaxFlowLink<BookTO> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.BOOKS_REMOVE);

                item.add(author, title, languages, note, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noBooks = new WebMarkupContainer("noBooks");
        noBooks.setVisible(getModelObject().isEmpty());

        booksTable.add(books);
        add(booksTable, noBooks);
    }

    /**
     * Returns languages.
     *
     * @param book TO for book
     * @return languages
     */
    private static String getLanguages(final BookTO book) {
        final List<Language> languages = book.getLanguages();

        if (languages == null || languages.isEmpty()) {
            return "";
        }

        final StringBuilder result = new StringBuilder();
        for (final Language language : languages) {
            result.append(language);
            result.append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

}
