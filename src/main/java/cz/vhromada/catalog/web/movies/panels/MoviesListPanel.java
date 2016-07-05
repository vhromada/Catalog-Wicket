package cz.vhromada.catalog.web.movies.panels;

import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.MediumTO;
import cz.vhromada.catalog.facade.to.MovieTO;
import cz.vhromada.catalog.web.commons.FormatUtils;
import cz.vhromada.catalog.web.components.CsfdLink;
import cz.vhromada.catalog.web.components.ImdbLink;
import cz.vhromada.catalog.web.components.WikipediaLink;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movies.mo.MoviesMO;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of movies.
 *
 * @author Vladimir Hromada
 */
@Component(MoviesListPanel.ID)
@Scope("prototype")
public class MoviesListPanel extends GenericPanel<MoviesMO> {

    /**
     * ID
     */
    public static final String ID = "moviesListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MoviesListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MoviesListPanel(final String id, final IModel<MoviesMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer moviesTable = new WebMarkupContainer("moviesTable");
        moviesTable.setVisible(!getModelObject().getMovies().isEmpty());

        final ListView<MovieTO> movies = new ListView<MovieTO>("movies", Model.ofList(getModelObject().getMovies())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<MovieTO> item) {
                final MovieTO movie = item.getModelObject();

                final Label czechName = new Label("czechName", movie.getCzechName());

                final Label originalName = new Label("originalName", movie.getOriginalName());

                final Label genres = new Label("genres", FormatUtils.getGenres(movie.getGenres()));

                final Label year = new Label("year", movie.getYear());

                final Label language = new Label("language", movie.getLanguage());

                final Label subtitles = new Label("subtitles", FormatUtils.getSubtitles(movie.getSubtitles()));

                final Label media = new Label("media", getMedia(movie));

                final Label totalLength = new Label("totalLength", getTotalLength(movie));

                final Label picture = new Label("picture", movie.getPicture());

                final Label note = new Label("note", movie.getNote());

                final CsfdLink csfd = new CsfdLink("csfd", movie.getCsfd());

                final ImdbLink imdb = new ImdbLink("imdb", movie.getImdbCode());

                final WikipediaLink wikiCz = new WikipediaLink("wikiCz", movie.getWikiCz(), WikipediaLink.Country.CZ);

                final WikipediaLink wikiEn = new WikipediaLink("wikiEn", movie.getWikiEn(), WikipediaLink.Country.EN);

                final AjaxFlowLink<MovieTO> moveUp = new AjaxFlowLink<>("moveUp", Model.of(movie), CatalogFlow.MOVIES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<MovieTO> moveDown = new AjaxFlowLink<>("moveDown", Model.of(movie), CatalogFlow.MOVIES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<MovieTO> duplicate = new AjaxFlowLink<>("duplicate", Model.of(movie), CatalogFlow.MOVIES_DUPLICATE);

                final AjaxFlowLink<MovieTO> edit = new AjaxFlowLink<>("edit", Model.of(movie), CatalogFlow.MOVIES_UPDATE);

                final AjaxFlowLink<MovieTO> remove = new AjaxFlowLink<>("remove", Model.of(movie), CatalogFlow.MOVIES_REMOVE);

                item.add(czechName, originalName, genres, year, language, subtitles, media, totalLength, picture, note, csfd, imdb, wikiCz, wikiEn, moveUp,
                        moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noMovies = new WebMarkupContainer("noMovies");
        noMovies.setVisible(getModelObject().getMovies().isEmpty());

        final Label count = new Label("count", getModelObject().getMovies().size());

        final Label totalLength = new Label("totalLength", getModelObject().getTotalLength());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        moviesTable.add(movies);
        add(moviesTable, noMovies, count, totalLength, mediaCount);
    }

    /**
     * Returns media.
     *
     * @param movie TO for movie
     * @return media
     */
    private static String getMedia(final MovieTO movie) {
        final List<MediumTO> media = movie.getMedia();

        if (media == null || media.isEmpty()) {
            return "";
        }

        final StringBuilder result = new StringBuilder();
        for (final MediumTO medium : media) {
            result.append(new Time(medium.getLength()));
            result.append(", ");
        }

        return result.substring(0, result.length() - 2);
    }

    /**
     * Returns total length.
     *
     * @param movie TO for movie
     * @return total length
     */
    private static Time getTotalLength(final MovieTO movie) {
        final List<MediumTO> media = movie.getMedia();

        if (media == null || media.isEmpty()) {
            return new Time(0);
        }

        int totalLength = 0;
        for (final MediumTO medium : media) {
            totalLength += medium.getLength();
        }

        return new Time(totalLength);
    }

}
