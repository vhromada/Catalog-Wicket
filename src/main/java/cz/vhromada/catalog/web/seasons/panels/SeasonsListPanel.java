package cz.vhromada.catalog.web.seasons.panels;

import java.util.List;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.common.FormatUtils;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.mo.SeasonDataMO;
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
 * A class represents panel for list of seasons.
 *
 * @author Vladimir Hromada
 */
@Component(SeasonsListPanel.ID)
@Scope("prototype")
public class SeasonsListPanel extends GenericPanel<List<SeasonDataMO>> {

    /**
     * ID
     */
    public static final String ID = "seasonsListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SeasonsListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SeasonsListPanel(final String id, final IModel<List<SeasonDataMO>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer seasonsTable = new WebMarkupContainer("seasonsTable");
        seasonsTable.setVisible(!getModelObject().isEmpty());

        final ListView<SeasonDataMO> seasons = new ListView<SeasonDataMO>("seasons", getModel()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<SeasonDataMO> item) {
                final SeasonDataMO seasonData = item.getModelObject();
                final Season season = seasonData.getSeason();

                final Label number = new Label("number", season.getNumber());

                final Label year = new Label("year", getYear(season));

                final Label language = new Label("language", season.getLanguage());

                final Label subtitles = new Label("subtitles", FormatUtils.getSubtitles(season.getSubtitles()));

                final Label episodesCount = new Label("episodesCount", seasonData.getEpisodesCount());

                final Label totalLength = new Label("totalLength", seasonData.getTotalLength());

                final Label note = new Label("note", season.getNote());

                final AjaxFlowLink<Season> episodes = new AjaxFlowLink<>("episodes", Model.of(season), CatalogFlow.SEASONS_EPISODES);

                final AjaxFlowLink<Season> moveUp = new AjaxFlowLink<>("moveUp", Model.of(season), CatalogFlow.SEASONS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Season> moveDown = new AjaxFlowLink<>("moveDown", Model.of(season), CatalogFlow.SEASONS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Season> duplicate = new AjaxFlowLink<>("duplicate", Model.of(season), CatalogFlow.SEASONS_DUPLICATE);

                final AjaxFlowLink<Season> edit = new AjaxFlowLink<>("edit", Model.of(season), CatalogFlow.SEASONS_UPDATE);

                final AjaxFlowLink<Season> remove = new AjaxFlowLink<>("remove", Model.of(season), CatalogFlow.SEASONS_REMOVE);

                item.add(number, year, language, subtitles, episodesCount, totalLength, note, episodes, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noSeasons = new WebMarkupContainer("noSeasons");
        noSeasons.setVisible(getModelObject().isEmpty());

        seasonsTable.add(seasons);
        add(seasonsTable, noSeasons);
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
