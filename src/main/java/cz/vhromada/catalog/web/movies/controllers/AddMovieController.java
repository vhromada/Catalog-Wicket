package cz.vhromada.catalog.web.movies.controllers;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
import cz.vhromada.catalog.web.movies.panels.MovieFormPanel;
import cz.vhromada.catalog.web.movies.panels.MoviesMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing form for adding movie.
 *
 * @author Vladimir Hromada
 */
@Component("addMovieController")
public class AddMovieController extends ResultController<Void> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddMovieController.
     *
     * @param genreFacade facade for genres
     * @param converter   converter
     * @throws IllegalArgumentException if facade for genres is null
     *                                  or converter is null
     */
    @Autowired
    public AddMovieController(final GenreFacade genreFacade,
            final Converter converter) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Genre>> result = genreFacade.getAll();

        addResults(result);

        if (processResult()) {
            final WebSession session = CatalogApplication.getSession();
            session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MOVIES_ADD_CONFIRM);
            session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
            final TimeMO time = new TimeMO();
            final List<TimeMO> media = new ArrayList<>();
            media.add(time);
            final MovieMO movie = new MovieMO();
            movie.setSubtitles(new ArrayList<>());
            movie.setMedia(media);
            movie.setAllGenres(converter.convertCollection(result.getData(), GenreMO.class));
            final PanelData<MovieMO> panelData = new PanelData<>(MovieFormPanel.ID, new CompoundPropertyModel<>(movie));
            final PanelData<Void> menuData = new PanelData<>(MoviesMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Add movie", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_ADD;
    }

}
