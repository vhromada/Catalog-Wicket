package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.web.genre.mo.GenreMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for genres.
 *
 * @author Vladimir Hromada
 */
@Mapper
public interface GenreMapper {

    /**
     * Returns MO for genre.
     *
     * @param source genre
     * @return MO for genre
     */
    GenreMO map(Genre source);

    /**
     * Returns genre.
     *
     * @param source MO for genre
     * @return genre
     */
    Genre mapBack(GenreMO source);

}
