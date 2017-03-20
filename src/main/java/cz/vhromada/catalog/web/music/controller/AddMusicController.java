package cz.vhromada.catalog.web.music.controller;

import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.catalog.web.music.panel.MusicFormPanel;
import cz.vhromada.catalog.web.music.panel.MusicMenuPanel;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

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
        final PanelData<MusicMO> panelData = new PanelData<>(MusicFormPanel.ID, new CompoundPropertyModel<>(new MusicMO()));
        final PanelData<Void> menuData = new PanelData<>(MusicMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Add music", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_ADD;
    }

}
