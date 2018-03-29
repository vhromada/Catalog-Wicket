package cz.vhromada.catalog.web.game.panel;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.game.mo.GamesMO;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of games.
 *
 * @author Vladimir Hromada
 */
@Component(GamesListPanel.ID)
@Scope("prototype")
public class GamesListPanel extends GenericPanel<GamesMO> {

    /**
     * ID
     */
    public static final String ID = "gamesListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of GamesListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GamesListPanel(final String id, final IModel<GamesMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer gamesTable = new WebMarkupContainer("gamesTable");
        gamesTable.setVisible(!getModelObject().getGames().isEmpty());

        final ListView<Game> games = new ListView<>("games", getModelObject().getGames()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Game> item) {
                final Game game = item.getModelObject();

                final AjaxFlowLink<Game> detail = new AjaxFlowLink<>("detail", item.getModel(), CatalogFlow.GAMES_DETAIL);

                final Label detailText = new Label("detailText", game.getName());

                final AjaxFlowLink<Game> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.GAMES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Game> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.GAMES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Game> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.GAMES_DUPLICATE);

                final AjaxFlowLink<Game> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.GAMES_UPDATE);

                final AjaxFlowLink<Game> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.GAMES_REMOVE);

                detail.add(detailText);
                item.add(detail, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noGames = new WebMarkupContainer("noGames");
        noGames.setVisible(getModelObject().getGames().isEmpty());

        final Label count = new Label("count", getModelObject().getGames().size());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        gamesTable.add(games);
        add(gamesTable, noGames, count, mediaCount);
    }

}
