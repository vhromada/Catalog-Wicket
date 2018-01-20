package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.common.SongUtils;
import cz.vhromada.catalog.web.song.mo.SongMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link SongMO} to {@link Song}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class SongConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertSongMO() {
        final SongMO songMO = SongUtils.getSongMO();

        final Song song = converter.convert(songMO, Song.class);

        SongUtils.assertSongDeepEquals(songMO, song);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for song.
     */
    @Test
    void convertSongMO_NullSongMO() {
        assertThat(converter.convert(null, Song.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertSong() {
        final Song song = SongUtils.getSong();

        final SongMO songMO = converter.convert(song, SongMO.class);

        SongUtils.assertSongDeepEquals(songMO, song);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null song.
     */
    @Test
    void convertSong_NullSong() {
        assertThat(converter.convert(null, SongMO.class)).isNull();
    }

}
