package cz.vhromada.catalog.web.converter;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
import cz.vhromada.converters.orika.MapperConfig;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * A class represents mapper configuration for episode.
 *
 * @author Vladimir Hromada
 */
@Component("episodeMapper")
public class EpisodeMapper implements MapperConfig {

    @Override
    public void config(final MapperFactory mapperFactory) {
        mapperFactory.classMap(EpisodeMO.class, Episode.class)
                .fieldMap("length", "length").converter("timeConverter").add()
                .byDefault()
                .register();
    }

}
