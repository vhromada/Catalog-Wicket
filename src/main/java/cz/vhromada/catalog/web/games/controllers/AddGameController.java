package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.catalog.web.games.panels.GameFormPanel;
import cz.vhromada.catalog.web.games.panels.GamesMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding game.
 *
 * @author Vladimir Hromada
 */
@Component("addGameController")
public class AddGameController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GAMES_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData<GameMO> panelData = new PanelData<>(GameFormPanel.ID, new CompoundPropertyModel<>(new GameMO()));
        final PanelData<Void> menuData = new PanelData<>(GamesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Add game", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_ADD;
    }

}
