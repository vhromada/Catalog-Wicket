package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.facade.to.GameTO;
import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.validators.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for duplicating game.
 *
 * @author Vladimir Hromada
 */
@Component("gamesDuplicateController")
public class GamesDuplicateController extends Controller<GameTO> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Creates a new instance of GamesDuplicateController.
     *
     * @param gameFacade facade for games
     * @throws IllegalArgumentException if facade for games is null
     */
    @Autowired
    public GamesDuplicateController(final GameFacade gameFacade) {
        Validators.validateArgumentNotNull(gameFacade, "Facade for games");

        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(final GameTO data) {
        gameFacade.remove(data);

        getUi().fireEvent(new ControllerEvent(Flow.GAMES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return Flow.GAMES_DUPLICATE;
    }

}
