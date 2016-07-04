package cz.vhromada.catalog.web.episodes.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.panels.SeasonsMenuPanel;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for episodes.
 *
 * @author Vladimir Hromada
 */
@Component(EpisodesMenuPanel.ID)
@Scope("prototype")
public class EpisodesMenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "episodesMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of EpisodesMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public EpisodesMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Panel seasonsMenu = new SeasonsMenuPanel("seasonsMenu", null);

        final AjaxFlowLink<Void> allEpisodes = new AjaxFlowLink<>("allEpisodes", CatalogFlow.EPISODES_LIST);

        final AjaxFlowLink<Void> addEpisode = new AjaxFlowLink<>("addEpisode", CatalogFlow.EPISODES_ADD);

        add(seasonsMenu, allEpisodes, addEpisode);
    }

}
