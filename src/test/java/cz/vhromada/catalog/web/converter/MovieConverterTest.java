package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.common.MovieUtils;
import cz.vhromada.catalog.web.movie.mo.MovieMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link MovieMO} to {@link Movie}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class MovieConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertMovieMO() {
        final MovieMO movieMO = MovieUtils.getMovieMO();

        final Movie movie = converter.convert(movieMO, Movie.class);

        MovieUtils.assertMovieDeepEquals(movieMO, movie);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for movie.
     */
    @Test
    void convertMovieMO_NullMovieMO() {
        assertThat(converter.convert(null, Movie.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertMovie() {
        final Movie movie = MovieUtils.getMovie();

        final MovieMO movieMO = converter.convert(movie, MovieMO.class);

        MovieUtils.assertMovieDeepEquals(movieMO, movie);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null movie.
     */
    @Test
    void convertMovie_NullMovie() {
        assertThat(converter.convert(null, MovieMO.class)).isNull();
    }

}
