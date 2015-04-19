package cz.vhromada.catalog.web.games.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

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
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GamesMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allGames = new AjaxFlowLink<>("allGames", CatalogFlow.GAMES_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.GAMES_NEW_DATA);

        final AjaxFlowLink<Void> addGame = new AjaxFlowLink<>("addGame", CatalogFlow.GAMES_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", CatalogFlow.GAMES_UPDATE_POSITION);

        add(allGames, newData, addGame, updatePositions);
    }

}
