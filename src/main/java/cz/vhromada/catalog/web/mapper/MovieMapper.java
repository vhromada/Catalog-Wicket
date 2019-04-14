package cz.vhromada.catalog.web.mapper;

import java.util.List;

import cz.vhromada.catalog.entity.Medium;
import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.movie.mo.MovieMO;

import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;
import org.springframework.util.CollectionUtils;

/**
 * An abstract class represents mapper for movies.
 *
 * @author Vladimir Hromada
 */
@Mapper(uses = GenreMapper.class)
public abstract class MovieMapper {

    /**
     * Returns MO for movie.
     *
     * @param source movie
     * @return MO for movie
     */
    @Mapping(target = "allGenres", ignore = true)
    @Mapping(target = "allPictures", ignore = true)
    public abstract MovieMO map(Movie source);

    /**
     * Returns movie.
     *
     * @param source MO for movie
     * @return movie
     */
    public abstract Movie mapBack(MovieMO source);

    /**
     * Returns MO for time.
     *
     * @param source medium
     * @return MO for time
     */
    TimeMO mapMedium(final Medium source) {
        if (source == null) {
            return null;
        }

        return Mappers.getMapper(TimeMapper.class).map(source.getLength());
    }

    /**
     * Returns medium.
     *
     * @param source MO for time
     * @return medium
     */

    Medium mapMediumBack(final TimeMO source) {
        if (source == null) {
            return null;
        }

        final Medium medium = new Medium();
        medium.setLength(Mappers.getMapper(TimeMapper.class).mapBack(source));
        return medium;
    }

    /**
     * Maps additional data.
     *
     * @param target movie
     */
    @AfterMapping
    void after(@MappingTarget final Movie target) {
        final List<Medium> media = target.getMedia();
        if (!CollectionUtils.isEmpty(media)) {
            for (int i = 0; i < media.size(); i++) {
                media.get(i).setNumber(i + 1);
            }
        }
    }

}
