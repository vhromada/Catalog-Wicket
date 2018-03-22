package cz.vhromada.catalog.web.music.controller;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.catalog.web.music.panel.MusicFormPanel;
import cz.vhromada.catalog.web.music.panel.MusicMenuPanel;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
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
 * A class represents controller for showing form for updating music.
 *
 * @author Vladimir Hromada
 */
@Component("updateMusicController")
public class UpdateMusicController extends Controller<IModel<Music>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateMusicController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateMusicController(final Converter converter) {
        Assert.notNull(converter, "Converter mustn't be null.");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Music> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MUSIC_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<MusicMO> panelData = new PanelData<>(MusicFormPanel.ID,
            new CompoundPropertyModel<>(converter.convert(data.getObject(), MusicMO.class)));
        final PanelData<Void> menuData = new PanelData<>(MusicMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit music", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_UPDATE;
    }

}
