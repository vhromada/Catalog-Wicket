package cz.vhromada.catalog.web.common;

import java.util.List;

import cz.vhromada.catalog.common.Language;
import cz.vhromada.catalog.entity.Genre;

import org.springframework.util.CollectionUtils;

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
        if (CollectionUtils.isEmpty(subtitles)) {
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
     * @param genres list of genres
     * @return genres
     */
    public static String getGenres(final List<Genre> genres) {
        if (CollectionUtils.isEmpty(genres)) {
            return "";
        }

        final StringBuilder result = new StringBuilder();
        for (final Genre genre : genres) {
            result.append(genre.getName());
            result.append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

}
