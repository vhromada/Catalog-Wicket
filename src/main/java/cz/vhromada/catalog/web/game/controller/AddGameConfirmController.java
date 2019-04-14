package cz.vhromada.catalog.web.game.controller;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.game.mo.GameMO;
import cz.vhromada.catalog.web.mapper.GameMapper;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding game.
 *
 * @author Vladimir Hromada
 */
@Component("addGameConfirmController")
public class AddGameConfirmController extends AbstractResultController<IModel<GameMO>> {

    /**
     * Facade for games
     */
    private GameFacade gameFacade;

    /**
     * Mapper for games
     */
    private final GameMapper gameMapper;

    /**
     * Creates a new instance of AddGameConfirmController.
     *
     * @param gameFacade facade for games
     * @throws IllegalArgumentException if facade for games is null
     */
    @Autowired
    public AddGameConfirmController(final GameFacade gameFacade) {
        Assert.notNull(gameFacade, "Facade for games mustn't be null.");

        this.gameFacade = gameFacade;
        this.gameMapper = Mappers.getMapper(GameMapper.class);
    }

    @Override
    public void handle(final IModel<GameMO> data) {
        addResults(gameFacade.add(gameMapper.mapBack(data.getObject())));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_ADD_CONFIRM;
    }

}
