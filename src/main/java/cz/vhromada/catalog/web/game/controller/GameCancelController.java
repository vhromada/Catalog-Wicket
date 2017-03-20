package cz.vhromada.catalog.web.game.controller;

import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.game.mo.GameMO;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

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
