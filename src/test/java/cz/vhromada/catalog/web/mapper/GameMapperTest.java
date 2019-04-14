package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.common.GameUtils;
import cz.vhromada.catalog.web.game.mo.GameMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Game} and {@link GameMO}.
 *
 * @author Vladimir Hromada
 */
class GameMapperTest {

    /**
     * Mapper for games
     */
    private GameMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(GameMapper.class);
    }

    /**
     * Test method for {@link GameMapper#map(Game)}.
     */
    @Test
    void map() {
        final Game game = GameUtils.getGame();

        final GameMO gameMO = mapper.map(game);

        GameUtils.assertGameDeepEquals(gameMO, game);
    }

    /**
     * Test method for {@link GameMapper#map(Game)} with null game.
     */
    @Test
    void map_NullGame() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link GameMapper#mapBack(GameMO)}.
     */
    @Test
    void mapBack() {
        final GameMO gameMO = GameUtils.getGameMO();

        final Game game = mapper.mapBack(gameMO);

        GameUtils.assertGameDeepEquals(gameMO, game);
    }

    /**
     * Test method for {@link GameMapper#mapBack(GameMO)} with null game.
     */
    @Test
    void mapBack_NullGame() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
