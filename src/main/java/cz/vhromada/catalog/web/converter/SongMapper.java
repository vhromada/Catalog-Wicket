package cz.vhromada.catalog.web.converter;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.converters.orika.MapperConfig;

import ma.glasnost.orika.MapperFactory;
import org.springframework.stereotype.Component;

/**
 * A class represents mapper configuration for song.
 *
 * @author Vladimir Hromada
 */
@Component("songMapper")
public class SongMapper implements MapperConfig {

    @Override
    public void config(final MapperFactory mapperFactory) {
        mapperFactory.classMap(SongMO.class, Song.class)
                .fieldMap("length", "length").converter("timeConverter").add()
                .byDefault()
                .register();
    }

}
