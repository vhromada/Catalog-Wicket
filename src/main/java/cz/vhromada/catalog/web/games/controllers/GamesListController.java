package cz.vhromada.catalog.web.games.controllers;

import java.util.List;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GamesMO;
import cz.vhromada.catalog.web.games.panels.GamesListPanel;
import cz.vhromada.catalog.web.games.panels.GamesMenuPanel;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of games.
 *
 * @author Vladimir Hromada
 */
@Component("gamesListController")
public class GamesListController extends AbstractResultController<Void> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Creates a new instance of GamesListController.
     *
     * @param gameFacade facade for games
     * @throws IllegalArgumentException if facade for games is null
     */
    @Autowired
    public GamesListController(final GameFacade gameFacade) {
        Assert.notNull(gameFacade, "Facade for games mustn't be null.");

        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Game>> gamesResult = gameFacade.getAll();
        final Result<Integer> mediaCountResult = gameFacade.getTotalMediaCount();

        addResults(gamesResult, mediaCountResult);

        if (processResult()) {
            final GamesMO games = new GamesMO();
            games.setGames(gamesResult.getData());
            games.setMediaCount(mediaCountResult.getData());
            final PanelData<GamesMO> panelData = new PanelData<>(GamesListPanel.ID, Model.of(games));
            final PanelData<Void> menuData = new PanelData<>(GamesMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Games", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_LIST;
    }

}
