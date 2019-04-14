package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.music.mo.MusicMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for music.
 *
 * @author Vladimir Hromada
 */
@Mapper
public interface MusicMapper {

    /**
     * Returns MO for music.
     *
     * @param source music
     * @return MO for music
     */
    MusicMO map(Music source);

    /**
     * Returns music.
     *
     * @param source MO for music
     * @return music
     */
    Music mapBack(MusicMO source);

}
