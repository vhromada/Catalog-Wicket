package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.GameFacade;
import cz.vhromada.catalog.facade.to.GameTO;
import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for adding game.
 *
 * @author Vladimir Hromada
 */
@Component("addGameConfirmController")
public class AddGameConfirmController extends Controller<IModel<GameMO>> {

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
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(gameFacade, "Facade for games");
        Validators.validateArgumentNotNull(converter, "converter");

        this.gameFacade = gameFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GameMO> data) {
        gameFacade.add(converter.convert(data.getObject(), GameTO.class));

        getUi().fireEvent(new ControllerEvent(Flow.GAMES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return Flow.GAMES_ADD_CONFIRM;
    }

}
