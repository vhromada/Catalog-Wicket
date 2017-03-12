package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.commons.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for creating new data.
 *
 * @author Vladimir Hromada
 */
@Component("gamesNewDataController")
public class GamesNewDataController extends ResultController<Void> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Creates a new instance of GamesNewDataController.
     *
     * @param gameFacade facade for games
     * @throws IllegalArgumentException if facade for games is null
     */
    @Autowired
    public GamesNewDataController(final GameFacade gameFacade) {
        Assert.notNull(gameFacade, "Facade for games mustn't be null.");

        this.gameFacade = gameFacade;
    }

    @Override
    public void handle(final Void data) {
        addResults(gameFacade.newData());

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_NEW_DATA;
    }

}
