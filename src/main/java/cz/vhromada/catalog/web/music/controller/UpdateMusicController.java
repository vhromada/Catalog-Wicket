package cz.vhromada.catalog.web.music.controller;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.MusicMapper;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.catalog.web.music.panel.MusicFormPanel;
import cz.vhromada.catalog.web.music.panel.MusicMenuPanel;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating music.
 *
 * @author Vladimir Hromada
 */
@Component("updateMusicController")
public class UpdateMusicController extends Controller<IModel<Music>> {

    /**
     * Mapper for music
     */
    private final MusicMapper musicMapper;

    /**
     * Creates a new instance of UpdateMusicController.
     */
    @Autowired
    public UpdateMusicController() {
        this.musicMapper = Mappers.getMapper(MusicMapper.class);
    }

    @Override
    public void handle(final IModel<Music> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MUSIC_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<MusicMO> panelData = new PanelData<>(MusicFormPanel.ID, new CompoundPropertyModel<>(musicMapper.map(data.getObject())));
        final PanelData<Void> menuData = new PanelData<>(MusicMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit music", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_UPDATE;
    }

}
