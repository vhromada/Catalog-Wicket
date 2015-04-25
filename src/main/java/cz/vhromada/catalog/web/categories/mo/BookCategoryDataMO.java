package cz.vhromada.catalog.web.categories.mo;

import java.io.Serializable;
import java.util.Objects;

import cz.vhromada.catalog.facade.to.BookCategoryTO;

/**
 * A class represents MO for book category data.
 *
 * @author Vladimir Hromada
 */
public class BookCategoryDataMO implements Serializable {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Book category
     */
    private BookCategoryTO bookCategory;

    /**
     * Count of books
     */
    private int booksCount;

    /**
     * Returns book category.
     *
     * @return book category
     */
    public BookCategoryTO getBookCategory() {
        return bookCategory;
    }

    /**
     * Sets a new value to book category.
     *
     * @param bookCategory new value
     */
    public void setBookCategory(final BookCategoryTO bookCategory) {
        this.bookCategory = bookCategory;
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

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof BookCategoryDataMO)) {
            return false;
        }
        final BookCategoryDataMO bookCategoryData = (BookCategoryDataMO) obj;
        return Objects.equals(bookCategory, bookCategoryData.bookCategory);
    }

    @Override
    public int hashCode() {
        return bookCategory.hashCode();
    }

    @Override
    public String toString() {
        return String.format("BookCategoryDataMO [bookCategory=%s, booksCount=%d]", bookCategory, booksCount);
    }

}
