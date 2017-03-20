package cz.vhromada.catalog.web.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.web.common.EpisodeUtils;
import cz.vhromada.catalog.web.episode.mo.EpisodeMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link EpisodeMO} to {@link Episode}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
public class EpisodeConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertEpisodeMO() {
        final EpisodeMO episodeMO = EpisodeUtils.getEpisodeMO();

        final Episode episode = converter.convert(episodeMO, Episode.class);

        EpisodeUtils.assertEpisodeDeepEquals(episodeMO, episode);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for episode.
     */
    @Test
    public void convertEpisodeMO_NullEpisodeMO() {
        assertThat(converter.convert(null, Episode.class), is(nullValue()));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertEpisode() {
        final Episode episode = EpisodeUtils.getEpisode();

        final EpisodeMO episodeMO = converter.convert(episode, EpisodeMO.class);

        EpisodeUtils.assertEpisodeDeepEquals(episodeMO, episode);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null episode.
     */
    @Test
    public void convertEpisode_NullEpisode() {
        assertThat(converter.convert(null, EpisodeMO.class), is(nullValue()));
    }

}
