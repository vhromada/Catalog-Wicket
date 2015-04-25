package cz.vhromada.catalog.web.episodes.controllers;

import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
import cz.vhromada.catalog.web.episodes.panels.EpisodeFormPanel;
import cz.vhromada.catalog.web.episodes.panels.EpisodesMenuPanel;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding episode.
 *
 * @author Vladimir Hromada
 */
@Component("addEpisodeController")
public class AddEpisodeController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.EPISODES_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData panelData = new PanelData(EpisodeFormPanel.ID, new CompoundPropertyModel<>(new EpisodeMO()));
        final PanelData menuData = new PanelData(EpisodesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Add episode", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_ADD;
    }

}
