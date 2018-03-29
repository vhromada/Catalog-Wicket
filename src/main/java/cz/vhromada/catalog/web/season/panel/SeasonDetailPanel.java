package cz.vhromada.catalog.web.season.panel;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.common.FormatUtils;
import cz.vhromada.catalog.web.season.mo.SeasonDataMO;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with season's detail.
 *
 * @author Vladimir Hromada
 */
@Component(SeasonDetailPanel.ID)
@Scope("prototype")
public class SeasonDetailPanel extends GenericPanel<SeasonDataMO> {

    /**
     * ID
     */
    public static final String ID = "seasonDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SeasonDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SeasonDetailPanel(final String id, final IModel<SeasonDataMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final SeasonDataMO seasonData = getModelObject();
        final Season season = seasonData.getSeason();

        final Label number = new Label("number", season.getNumber());

        final Label year = new Label("year", getYear(season));

        final Label language = new Label("language", season.getLanguage());

        final WebMarkupContainer subtitlesContainer = new WebMarkupContainer("subtitlesContainer");
        subtitlesContainer.setVisible(!CollectionUtils.isEmpty(season.getSubtitles()));

        final Label subtitles = new Label("subtitles", FormatUtils.getSubtitles(season.getSubtitles()));

        final WebMarkupContainer episodesContainer = new WebMarkupContainer("episodesContainer");
        episodesContainer.setVisible(seasonData.getEpisodesCount() > 0);

        final Label episodesCount = new Label("episodesCount", seasonData.getEpisodesCount());

        final Label totalLength = new Label("totalLength", seasonData.getTotalLength());

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(season.getNote()));

        final Label note = new Label("note", season.getNote());

        subtitlesContainer.add(subtitles);
        episodesContainer.add(episodesCount, totalLength);
        noteContainer.add(note);
        add(number, year, language, subtitlesContainer, episodesContainer, noteContainer);
    }

    /**
     * Returns year.
     *
     * @param season season
     * @return year
     */
    private static String getYear(final Season season) {
        final int startYear = season.getStartYear();
        final int endYear = season.getEndYear();

        return startYear == endYear ? Integer.toString(startYear) : startYear + " - " + endYear;
    }

}
