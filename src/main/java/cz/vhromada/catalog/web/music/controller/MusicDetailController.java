package cz.vhromada.catalog.web.music.controller;

import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicDataMO;
import cz.vhromada.catalog.web.music.panel.MusicDetailPanel;
import cz.vhromada.catalog.web.music.panel.MusicMenuPanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of music.
 *
 * @author Vladimir Hromada
 */
@Component("musicDetailController")
public class MusicDetailController extends Controller<IModel<MusicDataMO>> {

    @Override
    public void handle(final IModel<MusicDataMO> data) {
        final PanelData<MusicDataMO> panelData = new PanelData<>(MusicDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(MusicMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Music detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_DETAIL;
    }

}
