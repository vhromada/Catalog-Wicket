package cz.vhromada.catalog.web.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.common.MovieUtils;
import cz.vhromada.catalog.web.movie.mo.MovieMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link MovieMO} to {@link Movie}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
public class MovieConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertMovieMO() {
        final MovieMO movieMO = MovieUtils.getMovieMO();

        final Movie movie = converter.convert(movieMO, Movie.class);

        MovieUtils.assertMovieDeepEquals(movieMO, movie);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for movie.
     */
    @Test
    public void convertMovieMO_NullMovieMO() {
        assertThat(converter.convert(null, Movie.class), is(nullValue()));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertMovie() {
        final Movie movie = MovieUtils.getMovie();

        final MovieMO movieMO = converter.convert(movie, MovieMO.class);

        MovieUtils.assertMovieDeepEquals(movieMO, movie);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null movie.
     */
    @Test
    public void convertMovie_NullMovie() {
        assertThat(converter.convert(null, MovieMO.class), is(nullValue()));
    }

}
