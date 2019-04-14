package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.common.MusicUtils;
import cz.vhromada.catalog.web.music.mo.MusicMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Music} and {@link MusicMO}.
 *
 * @author Vladimir Hromada
 */
class MusicMapperTest {

    /**
     * Mapper for music
     */
    private MusicMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(MusicMapper.class);
    }

    /**
     * Test method for {@link MusicMapper#map(Music)}.
     */
    @Test
    void map() {
        final Music music = MusicUtils.getMusic();

        final MusicMO musicMO = mapper.map(music);

        MusicUtils.assertMusicDeepEquals(musicMO, music);
    }

    /**
     * Test method for {@link MusicMapper#map(Music)} with null music.
     */
    @Test
    void map_NullMusic() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link MusicMapper#mapBack(MusicMO)}.
     */
    @Test
    void mapBack() {
        final MusicMO musicMO = MusicUtils.getMusicMO();

        final Music music = mapper.mapBack(musicMO);

        MusicUtils.assertMusicDeepEquals(musicMO, music);
    }

    /**
     * Test method for {@link MusicMapper#mapBack(MusicMO)} with null music.
     */
    @Test
    void mapBack_NullMusic() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
