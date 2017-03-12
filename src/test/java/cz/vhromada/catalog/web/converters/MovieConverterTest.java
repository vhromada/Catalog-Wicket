package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.WicketApplication;
import cz.vhromada.catalog.web.commons.MovieUtils;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
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
@ContextConfiguration(classes = WicketApplication.class)
//TODO vladimir.hromada 06.03.2017: time converter, media converter
@org.junit.Ignore
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
        assertNull(converter.convert(null, Movie.class));
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
        assertNull(converter.convert(null, MovieMO.class));
    }

}
