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
// * A class represents test for converter from {@link EpisodeTO} to {@link EpisodeMO}.
// *
// * @author Vladimir Hromada
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:testWebConvertersContext.xml")
//public class EpisodeTOToEpisodeMOConverterTest extends ObjectGeneratorTest {
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
//        final EpisodeTO episodeTO = objectGenerator.generate(EpisodeTO.class);
//
//        final EpisodeMO episodeMO = converter.convert(episodeTO, EpisodeMO.class);
//
//        DeepAsserts.assertNotNull(episodeMO);
//        DeepAsserts.assertEquals(episodeTO, episodeMO, "length", "season");
//        final Time time = new Time(episodeTO.getLength());
//        DeepAsserts.assertEquals(time, episodeMO.getLength(), "length", "hours", "minutes", "seconds");
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.HOUR), episodeMO.getLength().getHours());
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.MINUTE), episodeMO.getLength().getMinutes());
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.SECOND), episodeMO.getLength().getSeconds());
//    }
//
//    /**
//     * Test method for {@link Converter#convert(Object, Class)} with null argument.
//     */
//    @Test
//    public void testConvertWithNullArgument() {
//        assertNull(converter.convert(null, EpisodeMO.class));
//    }
//
//}
