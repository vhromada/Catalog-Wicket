package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.facade.to.GameTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating game.
 *
 * @author Vladimir Hromada
 */
@Component("updateGameConfirmController")
public class UpdateGameConfirmController extends Controller<IModel<GameMO>> {

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
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(gameFacade, "Facade for games");
        Validators.validateArgumentNotNull(converter, "converter");

        this.gameFacade = gameFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GameMO> data) {
        gameFacade.update(converter.convert(data.getObject(), GameTO.class));

        getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_UPDATE_CONFIRM;
    }

}
