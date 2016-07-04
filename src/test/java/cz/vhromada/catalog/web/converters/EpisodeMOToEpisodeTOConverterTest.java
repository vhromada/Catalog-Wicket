//package cz.vhromada.catalog.web.converters;
//
//import static org.junit.Assert.assertNull;
//
//import cz.vhromada.catalog.commons.Time;
//import cz.vhromada.catalog.facade.to.EpisodeTO;
//import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
//import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
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
// * A class represents test for converter from {@link EpisodeMO} to {@link EpisodeTO}.
// *
// * @author Vladimir Hromada
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:testWebConvertersContext.xml")
//public class EpisodeMOToEpisodeTOConverterTest extends ObjectGeneratorTest {
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
//        final EpisodeMO episodeMO = objectGenerator.generate(EpisodeMO.class);
//        final Time time = objectGenerator.generate(Time.class);
//        episodeMO.getLength().setHours(time.getData(Time.TimeData.HOUR));
//        episodeMO.getLength().setMinutes(time.getData(Time.TimeData.MINUTE));
//        episodeMO.getLength().setSeconds(time.getData(Time.TimeData.SECOND));
//
//        final EpisodeTO episodeTO = converter.convert(episodeMO, EpisodeTO.class);
//
//        DeepAsserts.assertNotNull(episodeTO, "season");
//        DeepAsserts.assertEquals(episodeMO, episodeTO, "length", "season");
//        DeepAsserts.assertEquals(time.getLength(), episodeTO.getLength());
//    }
//
//    /**
//     * Test method for {@link Converter#convert(Object, Class)} with null argument.
//     */
//    @Test
//    public void testConvertWithNullArgument() {
//        assertNull(converter.convert(null, EpisodeTO.class));
//    }
//
//}
