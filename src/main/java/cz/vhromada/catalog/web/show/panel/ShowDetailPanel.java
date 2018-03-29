package cz.vhromada.catalog.web.show.panel;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.common.FormatUtils;
import cz.vhromada.catalog.web.component.CsfdLink;
import cz.vhromada.catalog.web.component.Image;
import cz.vhromada.catalog.web.component.ImdbLink;
import cz.vhromada.catalog.web.component.WikipediaLink;
import cz.vhromada.catalog.web.show.mo.ShowDataMO;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with show's detail.
 *
 * @author Vladimir Hromada
 */
@Component(ShowDetailPanel.ID)
@Scope("prototype")
public class ShowDetailPanel extends GenericPanel<ShowDataMO> {

    /**
     * ID
     */
    public static final String ID = "showDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ShowDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ShowDetailPanel(final String id, final IModel<ShowDataMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final ShowDataMO showData = getModelObject();
        final Show show = showData.getShow();

        final Image picture = new Image("picture", show.getPicture());

        final Label czechName = new Label("czechName", show.getCzechName());

        final Label originalName = new Label("originalName", show.getOriginalName());

        final Label genres = new Label("genres", FormatUtils.getGenres(show.getGenres()));

        final WebMarkupContainer seasonsContainer = new WebMarkupContainer("seasonsContainer");
        seasonsContainer.setVisible(showData.getSeasonsCount() > 0);

        final Label seasonsCount = new Label("seasonsCount", showData.getSeasonsCount());

        final WebMarkupContainer episodesContainer = new WebMarkupContainer("episodesContainer");
        episodesContainer.setVisible(showData.getEpisodesCount() > 0);

        final Label episodesCount = new Label("episodesCount", showData.getEpisodesCount());

        final Label totalLength = new Label("totalLength", showData.getTotalLength());

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(show.getNote()));

        final Label note = new Label("note", show.getNote());

        final WebMarkupContainer csfdContainer = new WebMarkupContainer("csfdContainer");
        csfdContainer.setVisible(!StringUtils.isEmpty(show.getCsfd()));

        final CsfdLink csfd = new CsfdLink("csfd", show.getCsfd());

        final WebMarkupContainer imdbContainer = new WebMarkupContainer("imdbContainer");
        imdbContainer.setVisible(show.getImdbCode() > 0);

        final ImdbLink imdb = new ImdbLink("imdb", show.getImdbCode());

        final WebMarkupContainer wikiCzContainer = new WebMarkupContainer("wikiCzContainer");
        wikiCzContainer.setVisible(!StringUtils.isEmpty(show.getWikiCz()));

        final WikipediaLink wikiCz = new WikipediaLink("wikiCz", show.getWikiCz(), WikipediaLink.Country.CZ);

        final WebMarkupContainer wikiEnContainer = new WebMarkupContainer("wikiEnContainer");
        wikiEnContainer.setVisible(!StringUtils.isEmpty(show.getWikiEn()));

        final WikipediaLink wikiEn = new WikipediaLink("wikiEn", show.getWikiEn(), WikipediaLink.Country.EN);

        seasonsContainer.add(seasonsCount);
        episodesContainer.add(episodesCount, totalLength);
        noteContainer.add(note);
        csfdContainer.add(csfd);
        imdbContainer.add(imdb);
        wikiCzContainer.add(wikiCz);
        wikiEnContainer.add(wikiEn);
        add(picture, czechName, originalName, genres, seasonsContainer, episodesContainer, noteContainer, csfdContainer, imdbContainer, wikiCzContainer,
            wikiEnContainer);
    }

}
