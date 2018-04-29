package cz.vhromada.catalog.web.movie.panel;

import java.util.List;

import cz.vhromada.catalog.entity.Medium;
import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.common.FormatUtils;
import cz.vhromada.catalog.web.component.CsfdLink;
import cz.vhromada.catalog.web.component.Image;
import cz.vhromada.catalog.web.component.ImdbLink;
import cz.vhromada.catalog.web.component.WikipediaLink;
import cz.vhromada.common.Time;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with movie's detail.
 *
 * @author Vladimir Hromada
 */
@Component(MovieDetailPanel.ID)
@Scope("prototype")
public class MovieDetailPanel extends GenericPanel<Movie> {

    /**
     * ID
     */
    public static final String ID = "movieDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MovieDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MovieDetailPanel(final String id, final IModel<Movie> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Movie movie = getModelObject();

        final Image picture = new Image("picture", movie.getPicture());

        final Label czechName = new Label("czechName", movie.getCzechName());

        final Label originalName = new Label("originalName", movie.getOriginalName());

        final Label genres = new Label("genres", FormatUtils.getGenres(movie.getGenres()));

        final Label year = new Label("year", movie.getYear());

        final Label language = new Label("language", movie.getLanguage());

        final WebMarkupContainer subtitlesContainer = new WebMarkupContainer("subtitlesContainer");
        subtitlesContainer.setVisible(!CollectionUtils.isEmpty(movie.getSubtitles()));

        final Label subtitles = new Label("subtitles", FormatUtils.getSubtitles(movie.getSubtitles()));

        final Label media = new Label("media", getMedia(movie));

        final Label totalLength = new Label("totalLength", getTotalLength(movie));

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(movie.getNote()));

        final Label note = new Label("note", movie.getNote());

        final WebMarkupContainer csfdContainer = new WebMarkupContainer("csfdContainer");
        csfdContainer.setVisible(!StringUtils.isEmpty(movie.getCsfd()));

        final CsfdLink csfd = new CsfdLink("csfd", movie.getCsfd());

        final WebMarkupContainer imdbContainer = new WebMarkupContainer("imdbContainer");
        imdbContainer.setVisible(movie.getImdbCode() > 0);

        final ImdbLink imdb = new ImdbLink("imdb", movie.getImdbCode());

        final WebMarkupContainer wikiCzContainer = new WebMarkupContainer("wikiCzContainer");
        wikiCzContainer.setVisible(!StringUtils.isEmpty(movie.getWikiCz()));

        final WikipediaLink wikiCz = new WikipediaLink("wikiCz", movie.getWikiCz(), WikipediaLink.Country.CZ);

        final WebMarkupContainer wikiEnContainer = new WebMarkupContainer("wikiEnContainer");
        wikiEnContainer.setVisible(!StringUtils.isEmpty(movie.getWikiEn()));

        final WikipediaLink wikiEn = new WikipediaLink("wikiEn", movie.getWikiEn(), WikipediaLink.Country.EN);

        subtitlesContainer.add(subtitles);
        noteContainer.add(note);
        csfdContainer.add(csfd);
        imdbContainer.add(imdb);
        wikiCzContainer.add(wikiCz);
        wikiEnContainer.add(wikiEn);
        add(picture, czechName, originalName, genres, year, language, subtitlesContainer, media, totalLength, noteContainer, csfdContainer, imdbContainer,
            wikiCzContainer, wikiEnContainer);
    }


    /**
     * Returns media.
     *
     * @param movie movie
     * @return media
     */
    private static String getMedia(final Movie movie) {
        final List<Medium> media = movie.getMedia();

        if (CollectionUtils.isEmpty(media)) {
            return "";
        }

        final StringBuilder result = new StringBuilder();
        for (final Medium medium : media) {
            result.append(new Time(medium.getLength()));
            result.append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    /**
     * Returns total length.
     *
     * @param movie movie
     * @return total length
     */
    private static Time getTotalLength(final Movie movie) {
        final List<Medium> media = movie.getMedia();

        if (CollectionUtils.isEmpty(media)) {
            return new Time(0);
        }

        int totalLength = 0;
        for (final Medium medium : media) {
            totalLength += medium.getLength();
        }

        return new Time(totalLength);
    }

}
