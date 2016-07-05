package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.web.commons.EpisodeUtils;
import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link EpisodeMO} to {@link EpisodeTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class EpisodeConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    @Qualifier("webDozerConverter")
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO.
     */
    @Test
    public void testConvertEpisodeMO() {
        final EpisodeMO episodeMO = EpisodeUtils.getEpisodeMO();

        final EpisodeTO episodeTO = converter.convert(episodeMO, EpisodeTO.class);

        EpisodeUtils.assertEpisodeDeepEquals(episodeMO, episodeTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertEpisodeMO_NullArgument() {
        assertNull(converter.convert(null, EpisodeTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertEpisodeTO() {
        final EpisodeTO episodeTO = EpisodeUtils.getEpisodeTO();

        final EpisodeMO episodeMO = converter.convert(episodeTO, EpisodeMO.class);

        EpisodeUtils.assertEpisodeDeepEquals(episodeMO, episodeTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertEpisodeTO_NullArgument() {
        assertNull(converter.convert(null, EpisodeMO.class));
    }

}
