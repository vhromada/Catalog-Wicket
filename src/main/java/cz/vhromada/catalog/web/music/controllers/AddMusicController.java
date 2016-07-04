package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.catalog.web.music.panels.MusicFormPanel;
import cz.vhromada.catalog.web.music.panels.MusicMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding music.
 *
 * @author Vladimir Hromada
 */
@Component("addMusicController")
public class AddMusicController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MUSIC_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData panelData = new PanelData(MusicFormPanel.ID, new CompoundPropertyModel<>(new MusicMO()));
        final PanelData menuData = new PanelData(MusicMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Add music", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_ADD;
    }

}
