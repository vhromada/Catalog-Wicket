package cz.vhromada.catalog.web.show.panel;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.show.mo.ShowDataMO;
import cz.vhromada.catalog.web.show.mo.ShowsMO;
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

        final ListView<ShowDataMO> shows = new ListView<ShowDataMO>("shows", getModelObject().getShows()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<ShowDataMO> item) {
                final Show show = item.getModelObject().getShow();

                final AjaxFlowLink<ShowDataMO> detail = new AjaxFlowLink<>("detail", item.getModel(), CatalogFlow.SHOWS_DETAIL);

                final Label detailText = new Label("detailText", show.getCzechName());

                final AjaxFlowLink<Show> seasons = new AjaxFlowLink<>("seasons", Model.of(show), CatalogFlow.SHOWS_SEASONS);

                final AjaxFlowLink<Show> moveUp = new AjaxFlowLink<>("moveUp", Model.of(show), CatalogFlow.SHOWS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Show> moveDown = new AjaxFlowLink<>("moveDown", Model.of(show), CatalogFlow.SHOWS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Show> duplicate = new AjaxFlowLink<>("duplicate", Model.of(show), CatalogFlow.SHOWS_DUPLICATE);

                final AjaxFlowLink<Show> edit = new AjaxFlowLink<>("edit", Model.of(show), CatalogFlow.SHOWS_UPDATE);

                final AjaxFlowLink<Show> remove = new AjaxFlowLink<>("remove", Model.of(show), CatalogFlow.SHOWS_REMOVE);

                detail.add(detailText);
                item.add(detail, seasons, moveUp, moveDown, duplicate, edit, remove);
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

}
