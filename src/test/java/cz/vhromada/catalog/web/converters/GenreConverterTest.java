package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.CatalogConfiguration;
import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.web.WicketApplication;
import cz.vhromada.catalog.web.commons.GenreUtils;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link GenreMO} to {@link Genre}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WicketApplication.class)
public class GenreConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertGenreMO() {
        final GenreMO genreMO = GenreUtils.getGenreMO();

        final Genre genre = converter.convert(genreMO, Genre.class);

        GenreUtils.assertGenreDeepEquals(genreMO, genre);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for genre.
     */
    @Test
    public void convertGenreMO_NullGenreMO() {
        assertNull(converter.convert(null, Genre.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertGenre() {
        final Genre genre = GenreUtils.getGenre();

        final GenreMO genreMO = converter.convert(genre, GenreMO.class);

        GenreUtils.assertGenreDeepEquals(genreMO, genre);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null genre.
     */
    @Test
    public void convertGenre_NullGenre() {
        assertNull(converter.convert(null, GenreMO.class));
    }

}
