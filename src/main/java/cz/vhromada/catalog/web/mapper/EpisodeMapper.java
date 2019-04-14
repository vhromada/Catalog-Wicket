package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.web.episode.mo.EpisodeMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for episodes.
 *
 * @author Vladimir Hromada
 */
@Mapper(uses = TimeMapper.class)
public interface EpisodeMapper {

    /**
     * Returns MO for episode.
     *
     * @param source episode
     * @return MO for episode
     */
    EpisodeMO map(Episode source);

    /**
     * Returns episode.
     *
     * @param source MO for episode
     * @return episode
     */
    Episode mapBack(EpisodeMO source);

}
