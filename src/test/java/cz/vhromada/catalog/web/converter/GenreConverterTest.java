package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.web.common.GenreUtils;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link GenreMO} to {@link Genre}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class GenreConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertGenreMO() {
        final GenreMO genreMO = GenreUtils.getGenreMO();

        final Genre genre = converter.convert(genreMO, Genre.class);

        GenreUtils.assertGenreDeepEquals(genreMO, genre);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for genre.
     */
    @Test
    void convertGenreMO_NullGenreMO() {
        assertThat(converter.convert(null, Genre.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertGenre() {
        final Genre genre = GenreUtils.getGenre();

        final GenreMO genreMO = converter.convert(genre, GenreMO.class);

        GenreUtils.assertGenreDeepEquals(genreMO, genre);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null genre.
     */
    @Test
    void convertGenre_NullGenre() {
        assertThat(converter.convert(null, GenreMO.class)).isNull();
    }

}
