package cz.vhromada.catalog.web.seasons.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.shows.panels.ShowsMenuPanel;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for seasons.
 *
 * @author Vladimir Hromada
 */
@Component(SeasonsMenuPanel.ID)
@Scope("prototype")
public class SeasonsMenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "seasonsMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SeasonsMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SeasonsMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Panel showsMenu = new ShowsMenuPanel("showsMenu", null);

        final AjaxFlowLink<Void> allSeasons = new AjaxFlowLink<>("allSeasons", CatalogFlow.SEASONS_LIST);

        final AjaxFlowLink<Void> addSeason = new AjaxFlowLink<>("addSeason", CatalogFlow.SEASONS_ADD);

        add(showsMenu, allSeasons, addSeason);
    }

}
