//package cz.vhromada.catalog.web.converters;
//
//import static org.junit.Assert.assertNull;
//
//import java.util.List;
//
//import cz.vhromada.catalog.commons.Time;
//import cz.vhromada.catalog.facade.to.MovieTO;
//import cz.vhromada.catalog.web.TimeMO;
//import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
//import cz.vhromada.catalog.web.movies.mo.MovieMO;
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
// * A class represents test for converter from {@link MovieTO} to {@link MovieMO}.
// *
// * @author Vladimir Hromada
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:testWebConvertersContext.xml")
//public class MovieTOToMovieMOConverterTest extends ObjectGeneratorTest {
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
//        final MovieTO movieTO = objectGenerator.generate(MovieTO.class);
//
//        final MovieMO movieMO = converter.convert(movieTO, MovieMO.class);
//
//        DeepAsserts.assertNotNull(movieMO, "allGenres");
//        DeepAsserts.assertEquals(movieTO, movieMO, "media", "allGenres");
//        assertMediaDeepEquals(movieTO.getMedia(), movieMO.getMedia());
//    }
//
//    /**
//     * Test method for {@link Converter#convert(Object, Class)} with null argument.
//     */
//    @Test
//    public void testConvertWithNullArgument() {
//        assertNull(converter.convert(null, MovieMO.class));
//    }
//
//    /**
//     * Assert media deep equals.
//     *
//     * @param expected expected media
//     * @param actual   actual media
//     */
//    private static void assertMediaDeepEquals(final List<Integer> expected, final List<TimeMO> actual) {
//        DeepAsserts.assertEquals(expected.size(), actual.size());
//        for (int i = 0; i < expected.size(); i++) {
//            assertMediumDeepEquals(expected.get(i), actual.get(i));
//        }
//    }
//
//    /**
//     * Assert medium deep equals.
//     *
//     * @param expected expected medium
//     * @param actual   actual medium
//     */
//    private static void assertMediumDeepEquals(final Integer expected, final TimeMO actual) {
//        final Time time = new Time(expected);
//        DeepAsserts.assertEquals(time, actual, "length", "hours", "minutes", "seconds");
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.HOUR), actual.getHours());
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.MINUTE), actual.getMinutes());
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.SECOND), actual.getSeconds());
//    }
//
//}
