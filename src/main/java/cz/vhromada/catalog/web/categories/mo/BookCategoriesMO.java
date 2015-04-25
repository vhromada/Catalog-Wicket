package cz.vhromada.catalog.web.categories.mo;

import java.io.Serializable;
import java.util.List;

/**
 * A class represents MO for book categories.
 *
 * @author Vladimir Hromada
 */
public class BookCategoriesMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * List of book categories
     */
    private List<BookCategoryDataMO> bookCategories;

    /**
     * Count of books
     */
    private int booksCount;

    /**
     * Returns book categories.
     *
     * @return book categories
     */
    public List<BookCategoryDataMO> getBookCategories() {
        return bookCategories;
    }

    /**
     * Sets a new value to book categories.
     *
     * @param bookCategories new value
     */
    public void setBookCategories(final List<BookCategoryDataMO> bookCategories) {
        this.bookCategories = bookCategories;
    }

    /**
     * Returns count of books.
     *
     * @return count of books
     */
    public int getBooksCount() {
        return booksCount;
    }

    /**
     * Sets a new value to count of books.
     *
     * @param booksCount new value
     */
    public void setBooksCount(final int booksCount) {
        this.booksCount = booksCount;
    }

}
