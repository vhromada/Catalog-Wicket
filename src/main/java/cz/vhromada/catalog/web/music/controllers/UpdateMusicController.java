package cz.vhromada.catalog.web.music.controllers;

import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.catalog.web.music.panels.MusicFormPanel;
import cz.vhromada.catalog.web.music.panels.MusicMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating music.
 *
 * @author Vladimir Hromada
 */
@Component("updateMusicController")
public class UpdateMusicController extends Controller<IModel<MusicTO>> {

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
    public UpdateMusicController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<MusicTO> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MUSIC_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData panelData = new PanelData(MusicFormPanel.ID, new CompoundPropertyModel<>(converter.convert(data.getObject(), MusicMO.class)));
        final PanelData menuData = new PanelData(MusicMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit music", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_UPDATE;
    }

}
