package cz.vhromada.catalog.web.movies.controllers;

import cz.vhromada.catalog.facade.MovieFacade;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movies.mo.MoviesMO;
import cz.vhromada.catalog.web.movies.panels.MoviesListPanel;
import cz.vhromada.catalog.web.movies.panels.MoviesMenuPanel;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of movies.
 *
 * @author Vladimir Hromada
 */
@Component("moviesListController")
public class MoviesListController extends Controller<Void> {

    /**
     * Facade for movies
     */
    private MovieFacade movieFacade;

    /**
     * Creates a new instance of MoviesListController.
     *
     * @param movieFacade facade for movies
     * @throws IllegalArgumentException if facade for movies is null
     */
    @Autowired
    public MoviesListController(final MovieFacade movieFacade) {
        Validators.validateArgumentNotNull(movieFacade, "Facade for movies");

        this.movieFacade = movieFacade;
    }

    @Override
    public void handle(final Void data) {
        final MoviesMO movies = new MoviesMO();
        movies.setMovies(movieFacade.getMovies());
        movies.setMediaCount(movieFacade.getTotalMediaCount());
        movies.setTotalLength(movieFacade.getTotalLength());
        final PanelData panelData = new PanelData(MoviesListPanel.ID, Model.of(movies));
        final PanelData menuData = new PanelData(MoviesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Movies", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_LIST;
    }

}
