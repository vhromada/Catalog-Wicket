package cz.vhromada.catalog.web.song.controller;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.song.panel.SongDetailPanel;
import cz.vhromada.catalog.web.song.panel.SongsMenuPanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of song.
 *
 * @author Vladimir Hromada
 */
@Component("songDetailController")
public class SongDetailController extends Controller<IModel<Song>> {

    @Override
    public void handle(final IModel<Song> data) {
        final PanelData<Song> panelData = new PanelData<>(SongDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(SongsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Song detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_DETAIL;
    }

}
