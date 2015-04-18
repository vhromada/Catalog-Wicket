package cz.vhromada.catalog.web.games.controllers;

import cz.vhromada.catalog.facade.to.GameTO;
import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.PageEvent;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.games.mo.GameMO;
import cz.vhromada.catalog.web.games.panels.GameFormPanel;
import cz.vhromada.catalog.web.games.panels.GamesMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating game.
 *
 * @author Vladimir Hromada
 */
@Component("updateGameController")
public class UpdateGameController extends Controller<IModel<GameTO>> {

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
    public UpdateGameController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GameTO> data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, Flow.GAMES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData panelData = new PanelData(GameFormPanel.ID, new CompoundPropertyModel<>(converter.convert(data.getObject(), GameMO.class)));
        final PanelData menuData = new PanelData(GamesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit game", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return Flow.GAMES_UPDATE;
    }

}