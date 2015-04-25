package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.catalog.web.shows.panels.ShowFormPanel;
import cz.vhromada.catalog.web.shows.panels.ShowsMenuPanel;
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
 * A class represents controller for showing form for updating show.
 *
 * @author Vladimir Hromada
 */
@Component("updateShowController")
public class UpdateShowController extends Controller<IModel<ShowTO>> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateShowController.
     *
     * @param genreFacade facade for genres
     * @param converter   converter
     * @throws IllegalArgumentException if facade for genres is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateShowController(final GenreFacade genreFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(genreFacade, "Facade for genres");
        Validators.validateArgumentNotNull(converter, "converter");

        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ShowTO> data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SHOWS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final ShowMO show = converter.convert(data.getObject(), ShowMO.class);
        show.setAllGenres(converter.convertCollection(genreFacade.getGenres(), GenreMO.class));
        final PanelData panelData = new PanelData(ShowFormPanel.ID, new CompoundPropertyModel<>(show));
        final PanelData menuData = new PanelData(ShowsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit show", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_UPDATE;
    }

}
