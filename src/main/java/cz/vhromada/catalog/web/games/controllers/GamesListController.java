package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GamesMO;
import cz.vhromada.catalog.web.games.panels.GamesListPanel;
import cz.vhromada.catalog.web.games.panels.GamesMenuPanel;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of games.
 *
 * @author Vladimir Hromada
 */
@Component("gamesListController")
public class GamesListController extends Controller<Void> {

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
        Validators.validateArgumentNotNull(gameFacade, "Facade for games");

        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(final Void data) {
        final GamesMO games = new GamesMO();
        games.setGames(gameFacade.getGames());
        games.setMediaCount(gameFacade.getTotalMediaCount());
        final PanelData<GamesMO> panelData = new PanelData<>(GamesListPanel.ID, Model.of(games));
        final PanelData<Void> menuData = new PanelData<>(GamesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Games", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_LIST;
    }

}
