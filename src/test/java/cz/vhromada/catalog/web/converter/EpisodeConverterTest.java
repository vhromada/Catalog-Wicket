package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.web.common.EpisodeUtils;
import cz.vhromada.catalog.web.episode.mo.EpisodeMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link EpisodeMO} to {@link Episode}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class EpisodeConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertEpisodeMO() {
        final EpisodeMO episodeMO = EpisodeUtils.getEpisodeMO();

        final Episode episode = converter.convert(episodeMO, Episode.class);

        EpisodeUtils.assertEpisodeDeepEquals(episodeMO, episode);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for episode.
     */
    @Test
    void convertEpisodeMO_NullEpisodeMO() {
        assertThat(converter.convert(null, Episode.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertEpisode() {
        final Episode episode = EpisodeUtils.getEpisode();

        final EpisodeMO episodeMO = converter.convert(episode, EpisodeMO.class);

        EpisodeUtils.assertEpisodeDeepEquals(episodeMO, episode);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null episode.
     */
    @Test
    void convertEpisode_NullEpisode() {
        assertThat(converter.convert(null, EpisodeMO.class)).isNull();
    }

}
