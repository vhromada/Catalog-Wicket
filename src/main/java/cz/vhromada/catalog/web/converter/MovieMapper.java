package cz.vhromada.catalog.web.converter;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.movie.mo.MovieMO;
import cz.vhromada.converter.orika.MapperConfig;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * A class represents mapper configuration for movie.
 *
 * @author Vladimir Hromada
 */
@Component("movieMapper")
public class MovieMapper implements MapperConfig {

    @Override
    public void config(final MapperFactory mapperFactory) {
        mapperFactory.classMap(MovieMO.class, Movie.class)
                .field("media{}", "media{length}")
                .byDefault()
                .customize(new MovieCustomMapper())
                .register();
    }

}
