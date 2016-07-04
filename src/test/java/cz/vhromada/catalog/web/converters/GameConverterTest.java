package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.GameTO;
import cz.vhromada.catalog.web.commons.GameUtils;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link GameMO} to {@link GameTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class GameConverterTest {

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
    public void testConvertGameMO() {
        final GameMO gameMO = GameUtils.getGameMO();

        final GameTO gameTO = converter.convert(gameMO, GameTO.class);

        GameUtils.assertGameDeepEquals(gameMO, gameTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertGameMO_NullArgument() {
        assertNull(converter.convert(null, GameTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertGameTO() {
        final GameTO gameTO = GameUtils.getGameTO();

        final GameMO gameMO = converter.convert(gameTO, GameMO.class);

        GameUtils.assertGameDeepEquals(gameMO, gameTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertGameTO_NullArgument() {
        assertNull(converter.convert(null, GameMO.class));
    }

}
