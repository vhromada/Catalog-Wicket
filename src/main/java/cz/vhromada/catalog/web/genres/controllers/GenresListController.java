package cz.vhromada.catalog.web.genres.controllers;

import java.util.List;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.panels.GenresListPanel;
import cz.vhromada.catalog.web.genres.panels.GenresMenuPanel;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of genres.
 *
 * @author Vladimir Hromada
 */
@Component("genresListController")
public class GenresListController extends Controller<Void> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Creates a new instance of GenresListController.
     *
     * @param genreFacade facade for genres
     * @throws IllegalArgumentException if facade for genres is null
     */
    @Autowired
    public GenresListController(final GenreFacade genreFacade) {
        Validators.validateArgumentNotNull(genreFacade, "Facade for genres");

        this.genreFacade = genreFacade;
    }

    @Override
    public void handle(final Void data) {
        final PanelData<List<GenreTO>> panelData = new PanelData<>(GenresListPanel.ID, Model.ofList(genreFacade.getGenres()));
        final PanelData<Void> menuData = new PanelData<>(GenresMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Genres", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_LIST;
    }

}
