package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.common.MovieUtils;
import cz.vhromada.catalog.web.movie.mo.MovieMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Movie} and {@link MovieMO}.
 *
 * @author Vladimir Hromada
 */
class MovieMapperTest {

    /**
     * Mapper for movies
     */
    private MovieMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(MovieMapper.class);
    }

    /**
     * Test method for {@link MovieMapper#map(Movie)}.
     */
    @Test
    void map() {
        final Movie movie = MovieUtils.getMovie();

        final MovieMO movieMO = mapper.map(movie);

        MovieUtils.assertMovieDeepEquals(movieMO, movie);
    }

    /**
     * Test method for {@link MovieMapper#map(Movie)} with null movie.
     */
    @Test
    void map_NullMovie() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link MovieMapper#mapBack(MovieMO)}.
     */
    @Test
    void mapBack() {
        final MovieMO movieMO = MovieUtils.getMovieMO();

        final Movie movie = mapper.mapBack(movieMO);

        MovieUtils.assertMovieDeepEquals(movieMO, movie);
    }

    /**
     * Test method for {@link MovieMapper#mapBack(MovieMO)} with null movie.
     */
    @Test
    void mapBack_NullMovie() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
