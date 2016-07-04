//package cz.vhromada.catalog.web.converters;
//
//import static org.junit.Assert.assertNull;
//
//import cz.vhromada.catalog.commons.Time;
//import cz.vhromada.catalog.facade.to.SongTO;
//import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
//import cz.vhromada.catalog.web.songs.mo.SongMO;
//import cz.vhromada.converters.Converter;
//import cz.vhromada.generator.ObjectGenerator;
//import cz.vhromada.test.DeepAsserts;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * A class represents test for converter from {@link SongMO} to {@link SongTO}.
// *
// * @author Vladimir Hromada
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:testWebConvertersContext.xml")
//public class SongMOToSongTOConverterTest extends ObjectGeneratorTest {
//
//    /**
//     * Instance of {@link Converter}
//     */
//    @Autowired
//    @Qualifier("webDozerConverter")
//    private Converter converter;
//
//    /**
//     * Instance of {@link ObjectGenerator}
//     */
//    @Autowired
//    private ObjectGenerator objectGenerator;
//
//    /**
//     * Test method for {@link Converter#convert(Object, Class)}.
//     */
//    @Test
//    public void testConvert() {
//        final SongMO songMO = objectGenerator.generate(SongMO.class);
//        final Time time = objectGenerator.generate(Time.class);
//        songMO.getLength().setHours(time.getData(Time.TimeData.HOUR));
//        songMO.getLength().setMinutes(time.getData(Time.TimeData.MINUTE));
//        songMO.getLength().setSeconds(time.getData(Time.TimeData.SECOND));
//
//        final SongTO songTO = converter.convert(songMO, SongTO.class);
//
//        DeepAsserts.assertNotNull(songTO, "music");
//        DeepAsserts.assertEquals(songMO, songTO, "length", "music");
//        DeepAsserts.assertEquals(time.getLength(), songTO.getLength());
//    }
//
//    /**
//     * Test method for {@link Converter#convert(Object, Class)} with null argument.
//     */
//    @Test
//    public void testConvertWithNullArgument() {
//        assertNull(converter.convert(null, SongTO.class));
//    }
//
//}
