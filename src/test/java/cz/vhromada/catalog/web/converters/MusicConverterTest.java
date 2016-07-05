package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.web.commons.MusicUtils;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link MusicMO} to {@link MusicTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class MusicConverterTest {

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
    public void testConvertMusicMO() {
        final MusicMO musicMO = MusicUtils.getMusicMO();

        final MusicTO musicTO = converter.convert(musicMO, MusicTO.class);

        MusicUtils.assertMusicDeepEquals(musicMO, musicTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertMusicMO_NullArgument() {
        assertNull(converter.convert(null, MusicTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertMusicTO() {
        final MusicTO musicTO = MusicUtils.getMusicTO();

        final MusicMO musicMO = converter.convert(musicTO, MusicMO.class);

        MusicUtils.assertMusicDeepEquals(musicMO, musicTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertMusicTO_NullArgument() {
        assertNull(converter.convert(null, MusicMO.class));
    }

}
