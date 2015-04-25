package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.generator.ObjectGenerator;
import cz.vhromada.test.DeepAsserts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link SongTO} to {@link SongMO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testWebConvertersContext.xml")
public class SongTOToSongMOConverterTest extends ObjectGeneratorTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    @Qualifier("webDozerConverter")
    private Converter converter;

    /**
     * Instance of {@link ObjectGenerator}
     */
    @Autowired
    private ObjectGenerator objectGenerator;

    /**
     * Test method for {@link Converter#convert(Object, Class)}.
     */
    @Test
    public void testConvert() {
        final SongTO songTO = objectGenerator.generate(SongTO.class);

        final SongMO songMO = converter.convert(songTO, SongMO.class);

        DeepAsserts.assertNotNull(songMO);
        DeepAsserts.assertEquals(songTO, songMO, "length", "music");
        final Time time = new Time(songTO.getLength());
        DeepAsserts.assertEquals(time, songMO.getLength(), "length", "hours", "minutes", "seconds");
        DeepAsserts.assertEquals(time.getData(Time.TimeData.HOUR), songMO.getLength().getHours());
        DeepAsserts.assertEquals(time.getData(Time.TimeData.MINUTE), songMO.getLength().getMinutes());
        DeepAsserts.assertEquals(time.getData(Time.TimeData.SECOND), songMO.getLength().getSeconds());
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} with null argument.
     */
    @Test
    public void testConvertWithNullArgument() {
        assertNull(converter.convert(null, SongMO.class));
    }

}