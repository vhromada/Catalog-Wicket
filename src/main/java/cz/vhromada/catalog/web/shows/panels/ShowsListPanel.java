package cz.vhromada.catalog.web.shows.panels;

import java.util.List;

import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.components.CsfdLink;
import cz.vhromada.catalog.web.components.ImdbLink;
import cz.vhromada.catalog.web.components.WikipediaLink;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.shows.mo.ShowDataMO;
import cz.vhromada.catalog.web.shows.mo.ShowsMO;
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
 * A class represents panel for list of shows.
 *
 * @author Vladimir Hromada
 */
@Component(ShowsListPanel.ID)
@Scope("prototype")
public class ShowsListPanel extends GenericPanel<ShowsMO> {

    /**
     * ID
     */
    public static final String ID = "showsListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ShowsListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ShowsListPanel(final String id, final IModel<ShowsMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer showsTable = new WebMarkupContainer("showsTable");
        showsTable.setVisible(!getModelObject().getShows().isEmpty());

        final ListView<ShowDataMO> shows = new ListView<ShowDataMO>("shows", Model.ofList(getModelObject().getShows())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<ShowDataMO> item) {
                final ShowDataMO showData = item.getModelObject();
                final ShowTO show = showData.getShow();

                final Label czechName = new Label("czechName", show.getCzechName());

                final Label originalName = new Label("originalName", show.getOriginalName());

                final Label genres = new Label("genres", getGenres(show));

                final Label seasonsCount = new Label("seasonsCount", showData.getSeasonsCount());

                final Label episodesCount = new Label("episodesCount", showData.getEpisodesCount());

                final Label totalLength = new Label("totalLength", showData.getTotalLength());

                final Label picture = new Label("picture", show.getPicture());

                final Label note = new Label("note", show.getNote());

                final CsfdLink csfd = new CsfdLink("csfd", show.getCsfd());

                final ImdbLink imdb = new ImdbLink("imdb", show.getImdbCode());

                final WikipediaLink wikiCz = new WikipediaLink("wikiCz", show.getWikiCz(), WikipediaLink.Country.CZ);

                final WikipediaLink wikiEn = new WikipediaLink("wikiEn", show.getWikiEn(), WikipediaLink.Country.EN);

                final AjaxFlowLink<ShowTO> seasons = new AjaxFlowLink<>("seasons", Model.of(show), CatalogFlow.SHOWS_SEASONS);

                final AjaxFlowLink<ShowTO> moveUp = new AjaxFlowLink<>("moveUp", Model.of(show), CatalogFlow.SHOWS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<ShowTO> moveDown = new AjaxFlowLink<>("moveDown", Model.of(show), CatalogFlow.SHOWS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<ShowTO> duplicate = new AjaxFlowLink<>("duplicate", Model.of(show), CatalogFlow.SHOWS_DUPLICATE);

                final AjaxFlowLink<ShowTO> edit = new AjaxFlowLink<>("edit", Model.of(show), CatalogFlow.SHOWS_UPDATE);

                final AjaxFlowLink<ShowTO> remove = new AjaxFlowLink<>("remove", Model.of(show), CatalogFlow.SHOWS_REMOVE);

                item.add(czechName, originalName, genres, seasonsCount, episodesCount, totalLength, picture, note, csfd, imdb, wikiCz, wikiEn, seasons, moveUp,
                        moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noShows = new WebMarkupContainer("noShows");
        noShows.setVisible(getModelObject().getShows().isEmpty());

        final Label count = new Label("count", getModelObject().getShows().size());

        final Label seasonsCount = new Label("seasonsCount", getModelObject().getSeasonsCount());

        final Label episodesCount = new Label("episodesCount", getModelObject().getEpisodesCount());

        final Label totalLength = new Label("totalLength", getModelObject().getTotalLength());

        showsTable.add(shows);
        add(showsTable, noShows, count, seasonsCount, episodesCount, totalLength);
    }

    /**
     * Returns genres.
     *
     * @param show TO for show
     * @return genres
     */
    private static String getGenres(final ShowTO show) {
        final List<GenreTO> genres = show.getGenres();

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
