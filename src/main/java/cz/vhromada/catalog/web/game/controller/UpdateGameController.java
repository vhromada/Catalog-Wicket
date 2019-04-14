package cz.vhromada.catalog.web.game.controller;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.game.mo.GameMO;
import cz.vhromada.catalog.web.game.panel.GameFormPanel;
import cz.vhromada.catalog.web.game.panel.GamesMenuPanel;
import cz.vhromada.catalog.web.mapper.GameMapper;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating game.
 *
 * @author Vladimir Hromada
 */
@Component("updateGameController")
public class UpdateGameController extends Controller<IModel<Game>> {

    /**
     * Mapper for games
     */
    private final GameMapper gameMapper;

    /**
     * Creates a new instance of UpdateGameController.
     */
    @Autowired
    public UpdateGameController() {
        this.gameMapper = Mappers.getMapper(GameMapper.class);
    }

    @Override
    public void handle(final IModel<Game> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GAMES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<GameMO> panelData = new PanelData<>(GameFormPanel.ID, new CompoundPropertyModel<>(gameMapper.map(data.getObject())));
        final PanelData<Void> menuData = new PanelData<>(GamesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit game", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_UPDATE;
    }

}
