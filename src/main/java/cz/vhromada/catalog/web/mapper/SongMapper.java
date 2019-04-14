package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.song.mo.SongMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for songs.
 *
 * @author Vladimir Hromada
 */
@Mapper(uses = TimeMapper.class)
public interface SongMapper {

    /**
     * Returns MO for song.
     *
     * @param source song
     * @return MO for song
     */
    SongMO map(Song source);

    /**
     * Returns song.
     *
     * @param source MO for song
     * @return song
     */
    Song mapBack(SongMO source);

}
