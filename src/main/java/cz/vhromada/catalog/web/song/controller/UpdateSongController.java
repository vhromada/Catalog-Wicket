package cz.vhromada.catalog.web.song.controller;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.song.mo.SongMO;
import cz.vhromada.catalog.web.song.panel.SongFormPanel;
import cz.vhromada.catalog.web.song.panel.SongsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing form for updating song.
 *
 * @author Vladimir Hromada
 */
@Component("updateSongController")
public class UpdateSongController extends Controller<IModel<Song>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateSongController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateSongController(final Converter converter) {
        Assert.notNull(converter, "Converter mustn't be null.");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Song> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SONGS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<SongMO> panelData = new PanelData<>(SongFormPanel.ID, new CompoundPropertyModel<>(converter.convert(data.getObject(), SongMO.class)));
        final PanelData<Void> menuData = new PanelData<>(SongsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit song", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SONGS_UPDATE;
    }

}