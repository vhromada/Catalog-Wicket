package cz.vhromada.catalog.web.converter;

import java.util.List;

import cz.vhromada.catalog.entity.Medium;
import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.movie.mo.MovieMO;

import ma.glasnost.orika.CustomMapper;
import ma.glasnost.orika.MappingContext;
import org.springframework.util.CollectionUtils;

/**
 * A class represents custom mapper for movie.
 *
 * @author Vladimir Hromada
 */
public class MovieCustomMapper extends CustomMapper<MovieMO, Movie> {

    @Override
    public void mapAtoB(final MovieMO movieMO, final Movie movie, final MappingContext context) {
        super.mapAtoB(movieMO, movie, context);

        final List<Medium> media = movie.getMedia();
        if (!CollectionUtils.isEmpty(media)) {
            for (int i = 0; i < media.size(); i++) {
                media.get(i).setNumber(i + 1);
            }
        }
    }

}
