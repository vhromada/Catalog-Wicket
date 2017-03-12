package cz.vhromada.catalog.web.converter;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
import cz.vhromada.converters.orika.MapperConfig;

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
                .fieldMap("media{}", "media{length}").converter("timeConverter").add()
                .byDefault()
                .customize(new MovieCustomMapper())
                .register();
    }

}
