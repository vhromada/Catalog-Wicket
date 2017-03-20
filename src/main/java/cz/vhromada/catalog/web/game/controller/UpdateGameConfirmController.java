package cz.vhromada.catalog.web.game.controller;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.game.mo.GameMO;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating game.
 *
 * @author Vladimir Hromada
 */
@Component("updateGameConfirmController")
public class UpdateGameConfirmController extends AbstractResultController<IModel<GameMO>> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateGameConfirmController.
     *
     * @param gameFacade facade for games
     * @param converter  converter
     * @throws IllegalArgumentException if facade for games is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateGameConfirmController(final GameFacade gameFacade,
            final Converter converter) {
        Assert.notNull(gameFacade, "Facade for games mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.gameFacade = gameFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GameMO> data) {
        addResults(gameFacade.update(converter.convert(data.getObject(), Game.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_UPDATE_CONFIRM;
    }

}
