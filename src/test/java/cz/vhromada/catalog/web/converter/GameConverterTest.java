package cz.vhromada.catalog.web.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.common.GameUtils;
import cz.vhromada.catalog.web.game.mo.GameMO;
import cz.vhromada.converter.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link GameMO} to {@link Game}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
public class GameConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertGameMO() {
        final GameMO gameMO = GameUtils.getGameMO();

        final Game game = converter.convert(gameMO, Game.class);

        GameUtils.assertGameDeepEquals(gameMO, game);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for game.
     */
    @Test
    public void convertGameMO_NullGameMO() {
        assertThat(converter.convert(null, Game.class), is(nullValue()));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertGame() {
        final Game game = GameUtils.getGame();

        final GameMO gameMO = converter.convert(game, GameMO.class);

        GameUtils.assertGameDeepEquals(gameMO, game);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null game.
     */
    @Test
    public void convertGame_NullGame() {
        assertThat(converter.convert(null, GameMO.class), is(nullValue()));
    }

}
