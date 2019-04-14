package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.show.mo.ShowMO;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * An interface represents mapper for shows.
 *
 * @author Vladimir Hromada
 */
@Mapper(uses = GenreMapper.class)
public interface ShowMapper {

    /**
     * Returns MO for show.
     *
     * @param source show
     * @return MO for show
     */
    @Mapping(target = "allGenres", ignore = true)
    @Mapping(target = "allPictures", ignore = true)
    ShowMO map(Show source);

    /**
     * Returns show.
     *
     * @param source MO for show
     * @return show
     */
    Show mapBack(ShowMO source);


}
