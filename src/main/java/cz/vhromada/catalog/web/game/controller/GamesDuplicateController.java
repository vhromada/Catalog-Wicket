package cz.vhromada.catalog.web.game.controller;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for duplicating game.
 *
 * @author Vladimir Hromada
 */
@Component("gamesDuplicateController")
public class GamesDuplicateController extends AbstractResultController<IModel<Game>> {

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
        Assert.notNull(gameFacade, "Facade for games mustn't be null.");

        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(final IModel<Game> data) {
        addResults(gameFacade.duplicate(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_DUPLICATE;
    }

}
