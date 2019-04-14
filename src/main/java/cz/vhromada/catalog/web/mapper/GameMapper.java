package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.game.mo.GameMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for games.
 *
 * @author Vladimir Hromada
 */
@Mapper
public interface GameMapper {

    /**
     * Returns MO for game.
     *
     * @param source game
     * @return MO for game
     */
    GameMO map(Game source);

    /**
     * Returns game.
     *
     * @param source MO for game
     * @return game
     */
    Game mapBack(GameMO source);

}
