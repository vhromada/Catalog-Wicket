package cz.vhromada.catalog.web.songs.controllers;

import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.catalog.web.songs.panels.SongFormPanel;
import cz.vhromada.catalog.web.songs.panels.SongsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding song.
 *
 * @author Vladimir Hromada
 */
@Component("addSongController")
public class AddSongController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SONGS_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData<SongMO> panelData = new PanelData<>(SongFormPanel.ID, new CompoundPropertyModel<>(new SongMO()));
        final PanelData<Void> menuData = new PanelData<>(SongsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Add song", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_ADD;
    }

}
