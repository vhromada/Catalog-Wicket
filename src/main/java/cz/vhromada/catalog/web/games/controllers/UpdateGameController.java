package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.catalog.web.games.panels.GameFormPanel;
import cz.vhromada.catalog.web.games.panels.GamesMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing form for updating game.
 *
 * @author Vladimir Hromada
 */
@Component("updateGameController")
public class UpdateGameController extends Controller<IModel<Game>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateGameController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateGameController(final Converter converter) {
        Assert.notNull(converter, "Converter mustn't be null.");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Game> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GAMES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<GameMO> panelData = new PanelData<>(GameFormPanel.ID, new CompoundPropertyModel<>(converter.convert(data.getObject(), GameMO.class)));
        final PanelData<Void> menuData = new PanelData<>(GamesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit game", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GAMES_UPDATE;
    }

}
