package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.common.SongUtils;
import cz.vhromada.catalog.web.song.mo.SongMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Song} and {@link SongMO}.
 *
 * @author Vladimir Hromada
 */
class SongMapperTest {

    /**
     * Mapper for songs
     */
    private SongMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(SongMapper.class);
    }

    /**
     * Test method for {@link SongMapper#map(Song)}.
     */
    @Test
    void map() {
        final Song song = SongUtils.getSong();

        final SongMO songMO = mapper.map(song);

        SongUtils.assertSongDeepEquals(songMO, song);
    }

    /**
     * Test method for {@link SongMapper#map(Song)} with null song.
     */
    @Test
    void map_NullSong() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link SongMapper#mapBack(SongMO)}.
     */
    @Test
    void mapBack() {
        final SongMO songMO = SongUtils.getSongMO();

        final Song song = mapper.mapBack(songMO);

        SongUtils.assertSongDeepEquals(songMO, song);
    }

    /**
     * Test method for {@link SongMapper#mapBack(SongMO)} with null song.
     */
    @Test
    void mapBack_NullSong() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
