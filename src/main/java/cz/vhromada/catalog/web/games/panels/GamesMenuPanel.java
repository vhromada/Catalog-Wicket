package cz.vhromada.catalog.web.games.panels;

import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.flow.AjaxFlowLink;
import cz.vhromada.catalog.web.panels.BasePanel;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for games.
 *
 * @author Vladimir Hromada
 */
@Component(GamesMenuPanel.ID)
@Scope("prototype")
public class GamesMenuPanel extends BasePanel<Void> {

    /**
     * ID
     */
    public static final String ID = "gamesMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of GamesMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws WicketRuntimeException if ID is null
     */
    public GamesMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allGames = new AjaxFlowLink<>("allGames", Flow.GAMES_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", Flow.GAMES_NEW_DATA);

        final AjaxFlowLink<Void> addGame = new AjaxFlowLink<>("addGame", Flow.GAMES_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", Flow.GAMES_UPDATE_POSITION);

        add(allGames, newData, addGame, updatePositions);
    }

}
