package cz.vhromada.catalog.web.games.panels;

import cz.vhromada.catalog.facade.to.GameTO;
import cz.vhromada.catalog.web.components.WikipediaLink;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GamesMO;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of games.
 *
 * @author Vladimir Hromada
 */
@Component(GamesListPanel.ID)
@Scope("prototype")
public class GamesListPanel extends BasePanel<GamesMO> {

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
     * @throws WicketRuntimeException if ID is null
     */
    public GamesListPanel(final String id, final IModel<GamesMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer gamesTable = new WebMarkupContainer("gamesTable");
        gamesTable.setVisible(!getModelObject().getGames().isEmpty());

        final ListView<GameTO> games = new ListView<GameTO>("games", Model.ofList(getModelObject().getGames())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<GameTO> item) {
                final GameTO game = item.getModelObject();

                final Label name = new Label("name", game.getName());

                final Label mediaCount = new Label("mediaCount", game.getMediaCount());

                final Label additionalData = new Label("additionalData", getAdditionalData(game));

                final Label note = new Label("note", game.getNote());

                final WikipediaLink wikiCz = new WikipediaLink("wikiCz", game.getWikiCz(), WikipediaLink.Country.CZ);

                final WikipediaLink wikiEn = new WikipediaLink("wikiEn", game.getWikiEn(), WikipediaLink.Country.EN);

                final AjaxFlowLink<GameTO> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.GAMES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<GameTO> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.GAMES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<GameTO> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.GAMES_DUPLICATE);

                final AjaxFlowLink<GameTO> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.GAMES_UPDATE);

                final AjaxFlowLink<GameTO> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.GAMES_REMOVE);

                item.add(name, mediaCount, additionalData, note, wikiCz, wikiEn, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noGames = new WebMarkupContainer("noGames");
        noGames.setVisible(getModelObject().getGames().isEmpty());

        final Label count = new Label("count", getModelObject().getGames().size());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        gamesTable.add(games);
        add(gamesTable, noGames, count, mediaCount);
    }

    /**
     * Returns additional data.
     *
     * @param game TO for game
     * @return additional data
     */
    private static String getAdditionalData(final GameTO game) {
        final StringBuilder result = new StringBuilder();
        if (game.getCrack()) {
            result.append("Crack");
        }
        addToResult(result, game.getSerialKey(), "serial key");
        addToResult(result, game.getPatch(), "patch");
        addToResult(result, game.getTrainer(), "trainer");
        addToResult(result, game.getTrainerData(), "data for trainer");
        addToResult(result, game.getEditor(), "editor");
        addToResult(result, game.getSaves(), "saves");
        if (game.getOtherData() != null && !game.getOtherData().isEmpty()) {
            if (result.length() != 0) {
                result.append(", ");
            }
            result.append(game.getOtherData());
        }

        return result.toString();
    }

    /**
     * Adds data to result.
     *
     * @param result result
     * @param value  value
     * @param data   data
     */
    private static void addToResult(final StringBuilder result, final boolean value, final String data) {
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

}
