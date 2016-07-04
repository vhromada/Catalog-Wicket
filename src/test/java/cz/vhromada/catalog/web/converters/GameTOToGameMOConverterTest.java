//package cz.vhromada.catalog.web.converters;
//
//import static org.junit.Assert.assertNull;
//
//import cz.vhromada.catalog.facade.to.GameTO;
//import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
//import cz.vhromada.catalog.web.games.mo.GameMO;
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
// * A class represents test for converter from {@link GameTO} to {@link GameMO}.
// *
// * @author Vladimir Hromada
// */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:testWebConvertersContext.xml")
//public class GameTOToGameMOConverterTest extends ObjectGeneratorTest {
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
//        final GameTO gameTO = objectGenerator.generate(GameTO.class);
//
//        final GameMO gameMO = converter.convert(gameTO, GameMO.class);
//
//        DeepAsserts.assertNotNull(gameMO);
//        DeepAsserts.assertEquals(gameTO, gameMO);
//    }
//
//    /**
//     * Test method for {@link Converter#convert(Object, Class)} with null argument.
//     */
//    @Test
//    public void testConvertWithNullArgument() {
//        assertNull(converter.convert(null, GameMO.class));
//    }
//
//}
