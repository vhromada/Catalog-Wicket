package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.genres.panels.GenreFormPanel;
import cz.vhromada.catalog.web.genres.panels.GenresMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating genre.
 *
 * @author Vladimir Hromada
 */
@Component("updateGenreController")
public class UpdateGenreController extends Controller<IModel<GenreTO>> {

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
    public UpdateGenreController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<GenreTO> data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GENRES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData panelData = new PanelData(GenreFormPanel.ID, new CompoundPropertyModel<>(converter.convert(data.getObject(), GenreMO.class)));
        final PanelData menuData = new PanelData(GenresMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit genre", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_UPDATE;
    }

}
