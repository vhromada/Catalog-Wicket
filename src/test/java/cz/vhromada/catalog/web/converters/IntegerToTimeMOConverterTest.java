//package cz.vhromada.catalog.web.converters;
//
//import static org.junit.Assert.assertNull;
//
//import cz.vhromada.catalog.commons.Time;
//import cz.vhromada.catalog.web.TimeMO;
//import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
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
// * A class represents test for converter from {@link Integer} to {@link TimeMO}.
// *
// * @author Vladimir Hromada
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:testWebConvertersContext.xml")
//public class IntegerToTimeMOConverterTest extends ObjectGeneratorTest {
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
//        final Integer length = objectGenerator.generate(Integer.class);
//
//        final TimeMO result = converter.convert(length, TimeMO.class);
//
//        DeepAsserts.assertNotNull(result);
//        final Time time = new Time(length);
//        DeepAsserts.assertEquals(time, result, "length", "hours", "minutes", "seconds");
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.HOUR), result.getHours());
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.MINUTE), result.getMinutes());
//        DeepAsserts.assertEquals(time.getData(Time.TimeData.SECOND), result.getSeconds());
//    }
//
//    /**
//     * Test method for {@link Converter#convert(Object, Class)} with null argument.
//     */
//    @Test
//    public void testConvertWithNullArgument() {
//        assertNull(converter.convert(null, TimeMO.class));
//    }
//
//}
