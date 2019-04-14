package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.web.common.GenreUtils;
import cz.vhromada.catalog.web.genre.mo.GenreMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Genre} and {@link GenreMO}.
 *
 * @author Vladimir Hromada
 */
class GenreMapperTest {

    /**
     * Mapper for genres
     */
    private GenreMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(GenreMapper.class);
    }

    /**
     * Test method for {@link GenreMapper#map(Genre)}.
     */
    @Test
    void map() {
        final Genre genre = GenreUtils.getGenre();

        final GenreMO genreMO = mapper.map(genre);

        GenreUtils.assertGenreDeepEquals(genreMO, genre);
    }

    /**
     * Test method for {@link GenreMapper#map(Genre)} with null genre.
     */
    @Test
    void map_NullGenre() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link GenreMapper#mapBack(GenreMO)}.
     */
    @Test
    void mapBack() {
        final GenreMO genreMO = GenreUtils.getGenreMO();

        final Genre genre = mapper.mapBack(genreMO);

        GenreUtils.assertGenreDeepEquals(genreMO, genre);
    }

    /**
     * Test method for {@link GenreMapper#mapBack(GenreMO)} with null genre.
     */
    @Test
    void mapBack_NullGenre() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
