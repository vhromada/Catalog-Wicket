package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.facade.to.GameTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving up game.
 *
 * @author Vladimir Hromada
 */
@Component("gamesMoveUpController")
public class GamesMoveUpController extends Controller<IModel<GameTO>> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Creates a new instance of GamesMoveUpController.
     *
     * @param gameFacade facade for games
     * @throws IllegalArgumentException if facade for games is null
     */
    @Autowired
    public GamesMoveUpController(final GameFacade gameFacade) {
        Validators.validateArgumentNotNull(gameFacade, "Facade for games");

        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(final IModel<GameTO> data) {
        gameFacade.moveUp(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_MOVE_UP;
    }

}
