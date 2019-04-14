package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.season.mo.SeasonMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for seasons.
 *
 * @author Vladimir Hromada
 */
@Mapper
public interface SeasonMapper {

    /**
     * Returns MO for season.
     *
     * @param source season
     * @return MO for season
     */
    SeasonMO map(Season source);

    /**
     * Returns season.
     *
     * @param source MO for season
     * @return season
     */
    Season mapBack(SeasonMO source);

}
