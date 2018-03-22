package cz.vhromada.catalog.web.genre.controller;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.catalog.web.genre.panel.GenreFormPanel;
import cz.vhromada.catalog.web.genre.panel.GenresMenuPanel;
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
 * A class represents controller for showing form for updating genre.
 *
 * @author Vladimir Hromada
 */
@Component("updateGenreController")
public class UpdateGenreController extends Controller<IModel<Genre>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateGenreController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateGenreController(final Converter converter) {
        Assert.notNull(converter, "Converter mustn't be null.");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Genre> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GENRES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<GenreMO> panelData = new PanelData<>(GenreFormPanel.ID,
            new CompoundPropertyModel<>(converter.convert(data.getObject(), GenreMO.class)));
        final PanelData<Void> menuData = new PanelData<>(GenresMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit genre", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_UPDATE;
    }

}
