package cz.vhromada.catalog.web.converters;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.facade.to.MediumTO;
import cz.vhromada.catalog.facade.to.MovieTO;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.movies.mo.MovieMO;

import org.dozer.CustomConverter;
import org.dozer.Mapper;
import org.dozer.MapperAware;
import org.dozer.MappingException;

/**
 * A class represents converter between MO for movie and TO for movie.
 *
 * @author Vladimir Hromada
 */
public class MovieConverter implements MapperAware, CustomConverter {

    /**
     * Mapper
     */
    private Mapper mapper;

    @Override
    public Object convert(final Object existingDestinationFieldValue, final Object sourceFieldValue, final Class<?> destinationClass,
            final Class<?> sourceClass) {
        if (sourceFieldValue == null) {
            return null;
        }
        if (sourceFieldValue instanceof MovieMO && sourceClass == MovieMO.class && destinationClass == MovieTO.class) {
            return convertMovieMO((MovieMO) sourceFieldValue);
        } else if (sourceFieldValue instanceof MovieTO && sourceClass == MovieTO.class && destinationClass == MovieMO.class) {
            return convertMovieTO((MovieTO) sourceFieldValue);
        } else {
            throw new MappingException("Converter MovieConverter used incorrectly. Arguments passed in were:" + existingDestinationFieldValue + " and "
                    + sourceFieldValue);
        }
    }

    @Override
    public void setMapper(final Mapper mapper) {
        this.mapper = mapper;
    }

    /**
     * Returns converted MO for movie to TO for movie.
     *
     * @param source MO for movie
     * @return converted MO for movie to TO for movie
     */
    private MovieTO convertMovieMO(final MovieMO source) {
        final MovieTO movie = new MovieTO();
        movie.setId(source.getId());
        movie.setCzechName(source.getCzechName());
        movie.setOriginalName(source.getOriginalName());
        movie.setYear(source.getYear());
        movie.setLanguage(source.getLanguage());
        movie.setSubtitles(source.getSubtitles());
        movie.setMedia(convertMedia(source.getMedia()));
        movie.setCsfd(source.getCsfd());
        movie.setImdbCode(source.getImdbCode());
        movie.setWikiCz(source.getWikiCz());
        movie.setWikiEn(source.getWikiEn());
        movie.setPicture(source.getPicture());
        movie.setNote(source.getNote());
        movie.setPosition(source.getPosition());
        movie.setGenres(convertGenres(source.getGenres()));

        return movie;
    }

    /**
     * Returns media.
     *
     * @param source source
     * @return media
     */
    private List<MediumTO> convertMedia(final List<TimeMO> source) {
        final List<MediumTO> media = new ArrayList<>();
        for (final TimeMO medium : source) {
            final MediumTO mediumTO = new MediumTO();
            mediumTO.setNumber(source.indexOf(medium) + 1);
            mediumTO.setLength(mapper.map(medium, Integer.class));

            media.add(mediumTO);
        }

        return media;
    }

    /**
     * Returns genres.
     *
     * @param source source
     * @return genres
     */
    private List<GenreTO> convertGenres(final List<GenreMO> source) {
        final List<GenreTO> genres = new ArrayList<>();
        for (final GenreMO genre : source) {
            genres.add(mapper.map(genre, GenreTO.class));
        }

        return genres;
    }

    /**
     * Returns converted TO for movie to MO for movie.
     *
     * @param source TO for movie
     * @return converted TO for movie to MO for movie
     */
    private MovieMO convertMovieTO(final MovieTO source) {
        final MovieMO movie = new MovieMO();
        movie.setId(source.getId());
        movie.setCzechName(source.getCzechName());
        movie.setOriginalName(source.getOriginalName());
        movie.setYear(source.getYear());
        movie.setLanguage(source.getLanguage());
        movie.setSubtitles(source.getSubtitles());
        movie.setMedia(convertMediaList(source.getMedia()));
        movie.setCsfd(source.getCsfd());
        movie.setImdbCode(source.getImdbCode());
        movie.setWikiCz(source.getWikiCz());
        movie.setWikiEn(source.getWikiEn());
        movie.setPicture(source.getPicture());
        movie.setNote(source.getNote());
        movie.setPosition(source.getPosition());
        movie.setGenres(convertGenreList(source.getGenres()));

        return movie;
    }

    /**
     * Returns media.
     *
     * @param source source
     * @return media
     */
    private List<TimeMO> convertMediaList(final List<MediumTO> source) {
        final List<TimeMO> media = new ArrayList<>();
        for (final MediumTO medium : source) {
            media.add(mapper.map(medium.getLength(), TimeMO.class));
        }

        return media;
    }

    /**
     * Returns genres.
     *
     * @param source source
     * @return genres
     */
    private List<GenreMO> convertGenreList(final List<GenreTO> source) {
        final List<GenreMO> genres = new ArrayList<>();
        for (final GenreTO genre : source) {
            genres.add(mapper.map(genre, GenreMO.class));
        }

        return genres;
    }

}
