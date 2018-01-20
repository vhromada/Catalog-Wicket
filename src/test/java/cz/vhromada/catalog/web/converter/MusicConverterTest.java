package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.common.MusicUtils;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link MusicMO} to {@link Music}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class MusicConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertMusicMO() {
        final MusicMO musicMO = MusicUtils.getMusicMO();

        final Music music = converter.convert(musicMO, Music.class);

        MusicUtils.assertMusicDeepEquals(musicMO, music);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for music.
     */
    @Test
    void convertMusicMO_NullMusicMO() {
        assertThat(converter.convert(null, Music.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertMusic() {
        final Music music = MusicUtils.getMusic();

        final MusicMO musicMO = converter.convert(music, MusicMO.class);

        MusicUtils.assertMusicDeepEquals(musicMO, music);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null music.
     */
    @Test
    void convertMusic_NullMusic() {
        assertThat(converter.convert(null, MusicMO.class)).isNull();
    }

}
