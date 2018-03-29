package cz.vhromada.catalog.web.movie.controller;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movie.panel.MovieDetailPanel;
import cz.vhromada.catalog.web.movie.panel.MoviesMenuPanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of movie.
 *
 * @author Vladimir Hromada
 */
@Component("movieDetailController")
public class MovieDetailController extends Controller<IModel<Movie>> {

    @Override
    public void handle(final IModel<Movie> data) {
        final PanelData<Movie> panelData = new PanelData<>(MovieDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(MoviesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Movie detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MOVIES_DETAIL;
    }

}
