package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.common.GameUtils;
import cz.vhromada.catalog.web.game.mo.GameMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link GameMO} to {@link Game}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class GameConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertGameMO() {
        final GameMO gameMO = GameUtils.getGameMO();

        final Game game = converter.convert(gameMO, Game.class);

        GameUtils.assertGameDeepEquals(gameMO, game);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for game.
     */
    @Test
    void convertGameMO_NullGameMO() {
        assertThat(converter.convert(null, Game.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertGame() {
        final Game game = GameUtils.getGame();

        final GameMO gameMO = converter.convert(game, GameMO.class);

        GameUtils.assertGameDeepEquals(gameMO, game);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null game.
     */
    @Test
    void convertGame_NullGame() {
        assertThat(converter.convert(null, GameMO.class)).isNull();
    }

}
