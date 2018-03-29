package cz.vhromada.catalog.web.game.controller;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.game.panel.GameDetailPanel;
import cz.vhromada.catalog.web.game.panel.GamesMenuPanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of game.
 *
 * @author Vladimir Hromada
 */
@Component("gameDetailController")
public class GameDetailController extends Controller<IModel<Game>> {

    @Override
    public void handle(final IModel<Game> data) {
        final PanelData<Game> panelData = new PanelData<>(GameDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(GamesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Game detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_DETAIL;
    }

}
