package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating positions.
 *
 * @author Vladimir Hromada
 */
@Component("gamesUpdatePositionsController")
public class GamesUpdatePositionsController extends Controller<Void> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Creates a new instance of GamesUpdatePositionsController.
     *
     * @param gameFacade facade for games
     * @throws IllegalArgumentException if facade for games is null
     */
    @Autowired
    public GamesUpdatePositionsController(final GameFacade gameFacade) {
        Validators.validateArgumentNotNull(gameFacade, "Facade for games");

        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(final Void data) {
        gameFacade.updatePositions();

        getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_UPDATE_POSITION;
    }

}
