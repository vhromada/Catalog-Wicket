package cz.vhromada.catalog.web.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.common.MusicUtils;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link MusicMO} to {@link Music}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
public class MusicConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertMusicMO() {
        final MusicMO musicMO = MusicUtils.getMusicMO();

        final Music music = converter.convert(musicMO, Music.class);

        MusicUtils.assertMusicDeepEquals(musicMO, music);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for music.
     */
    @Test
    public void convertMusicMO_NullMusicMO() {
        assertThat(converter.convert(null, Music.class), is(nullValue()));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertMusic() {
        final Music music = MusicUtils.getMusic();

        final MusicMO musicMO = converter.convert(music, MusicMO.class);

        MusicUtils.assertMusicDeepEquals(musicMO, music);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null music.
     */
    @Test
    public void convertMusic_NullMusic() {
        assertThat(converter.convert(null, MusicMO.class), is(nullValue()));
    }

}
