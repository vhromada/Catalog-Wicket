package cz.vhromada.catalog.web.movie.controller;

import java.util.ArrayList;
import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.catalog.web.movie.mo.MovieMO;
import cz.vhromada.catalog.web.movie.panel.MovieFormPanel;
import cz.vhromada.catalog.web.movie.panel.MoviesMenuPanel;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing form for updating movie.
 *
 * @author Vladimir Hromada
 */
@Component("updateMovieController")
public class UpdateMovieController extends AbstractResultController<IModel<Movie>> {

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
            final Converter converter) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Movie> data) {
        final Result<List<Genre>> result = genreFacade.getAll();

        addResults(result);

        if (processResult()) {
            final WebSession session = CatalogApplication.getSession();
            session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MOVIES_UPDATE_CONFIRM);
            session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
            final MovieMO movie = converter.convert(data.getObject(), MovieMO.class);
            if (movie.getSubtitles() == null) {
                movie.setSubtitles(new ArrayList<>());
            }
            if (movie.getMedia() == null) {
                final TimeMO time = new TimeMO();
                final List<TimeMO> media = new ArrayList<>();
                media.add(time);
                movie.setMedia(media);
            }
            movie.setAllGenres(converter.convertCollection(result.getData(), GenreMO.class));
            final PanelData<MovieMO> panelData = new PanelData<>(MovieFormPanel.ID, new CompoundPropertyModel<>(movie));
            final PanelData<Void> menuData = new PanelData<>(MoviesMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Edit movie", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_UPDATE;
    }

}
