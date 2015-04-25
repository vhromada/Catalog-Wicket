package cz.vhromada.catalog.web.movies.controllers;

import java.util.ArrayList;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.to.MovieTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
import cz.vhromada.catalog.web.movies.panels.MovieFormPanel;
import cz.vhromada.catalog.web.movies.panels.MoviesMenuPanel;
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
 * A class represents controller for showing form for updating movie.
 *
 * @author Vladimir Hromada
 */
@Component("updateMovieController")
public class UpdateMovieController extends Controller<IModel<MovieTO>> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateMovieController.
     *
     * @param genreFacade facade for genres
     * @param converter   converter
     * @throws IllegalArgumentException if facade for genres is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateMovieController(final GenreFacade genreFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(genreFacade, "Facade for genres");
        Validators.validateArgumentNotNull(converter, "converter");

        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<MovieTO> data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MOVIES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final MovieMO movie = converter.convert(data.getObject(), MovieMO.class);
        if (movie.getSubtitles() == null) {
            movie.setSubtitles(new ArrayList<Language>());
        }
        movie.setAllGenres(converter.convertCollection(genreFacade.getGenres(), GenreMO.class));
        final PanelData panelData = new PanelData(MovieFormPanel.ID, new CompoundPropertyModel<>(movie));
        final PanelData menuData = new PanelData(MoviesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit movie", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_UPDATE;
    }

}
