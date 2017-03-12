package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding game.
 *
 * @author Vladimir Hromada
 */
@Component("addGameConfirmController")
public class AddGameConfirmController extends ResultController<IModel<GameMO>> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddGameConfirmController.
     *
     * @param gameFacade facade for games
     * @param converter  converter
     * @throws IllegalArgumentException if facade for games is null
     *                                  or converter is null
     */
    @Autowired
    public AddGameConfirmController(final GameFacade gameFacade,
            final Converter converter) {
        Assert.notNull(gameFacade, "Facade for games mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.gameFacade = gameFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GameMO> data) {
        addResults(gameFacade.add(converter.convert(data.getObject(), Game.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_ADD_CONFIRM;
    }

}
