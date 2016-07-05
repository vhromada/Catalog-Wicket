package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.commons.SongUtils;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link SongMO} to {@link SongTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class SongConverterTest {

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
    public void testConvertSongMO() {
        final SongMO songMO = SongUtils.getSongMO();

        final SongTO songTO = converter.convert(songMO, SongTO.class);

        SongUtils.assertSongDeepEquals(songMO, songTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertSongMO_NullArgument() {
        assertNull(converter.convert(null, SongTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertSongTO() {
        final SongTO songTO = SongUtils.getSongTO();

        final SongMO songMO = converter.convert(songTO, SongMO.class);

        SongUtils.assertSongDeepEquals(songMO, songTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertSongTO_NullArgument() {
        assertNull(converter.convert(null, SongMO.class));
    }

}
