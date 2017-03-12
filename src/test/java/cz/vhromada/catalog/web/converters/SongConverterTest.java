package cz.vhromada.catalog.web.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.WicketApplication;
import cz.vhromada.catalog.web.common.SongUtils;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link SongMO} to {@link Song}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WicketApplication.class)
public class SongConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertSongMO() {
        final SongMO songMO = SongUtils.getSongMO();

        final Song song = converter.convert(songMO, Song.class);

        SongUtils.assertSongDeepEquals(songMO, song);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for song.
     */
    @Test
    public void convertSongMO_NullSongMO() {
        assertThat(converter.convert(null, Song.class), is(nullValue()));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertSong() {
        final Song song = SongUtils.getSong();

        final SongMO songMO = converter.convert(song, SongMO.class);

        SongUtils.assertSongDeepEquals(songMO, song);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null song.
     */
    @Test
    public void convertSong_NullSong() {
        assertThat(converter.convert(null, SongMO.class), is(nullValue()));
    }

}
