package cz.vhromada.catalog.web.commons;

import java.util.List;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.facade.to.GenreTO;

/**
 * A class represents utility class for formatting.
 *
 * @author Vladimir Hromada
 */
public final class FormatUtils {

    /**
     * Creates a new instance of FormatUtils.
     */
    private FormatUtils() {
    }

    /**
     * Adds data to result.
     *
     * @param result result
     * @param value  value
     * @param data   data
     */
    public static void addToResult(final StringBuilder result, final boolean value, final String data) {
        if (value) {
            if (result.length() == 0) {
                result.append(data.substring(0, 1).toUpperCase());
                result.append(data.substring(1));
            } else {
                result.append(", ");
                result.append(data);
            }
        }
    }

    /**
     * Returns subtitles.
     *
     * @param subtitles subtitles
     * @return subtitles
     */
    public static String getSubtitles(final List<Language> subtitles) {
        if (subtitles == null || subtitles.isEmpty()) {
            return "";
        }

        final StringBuilder result = new StringBuilder();
        for (final Language subtitlesItem : subtitles) {
            result.append(subtitlesItem);
            result.append(" / ");
        }

        return result.substring(0, result.length() - 3);
    }

    /**
     * Returns genres.
     *
     * @param genres list of TO for genre
     * @return genres
     */
    public static String getGenres(final List<GenreTO> genres) {
        if (genres == null || genres.isEmpty()) {
            return "";
        }

        final StringBuilder result = new StringBuilder();
        for (final GenreTO genre : genres) {
            result.append(genre.getName());
            result.append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

}
