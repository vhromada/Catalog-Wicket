package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing game.
 *
 * @author Vladimir Hromada
 */
@Component("gameCancelController")
public class GameCancelController extends Controller<IModel<GameMO>> {

    @Override
    public void handle(final IModel<GameMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.GAMES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_CANCEL;
    }

}
