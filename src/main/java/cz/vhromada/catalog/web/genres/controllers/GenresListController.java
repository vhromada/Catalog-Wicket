package cz.vhromada.catalog.web.genres.controllers;

import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.panels.GenresListPanel;
import cz.vhromada.catalog.web.genres.panels.GenresMenuPanel;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of genres.
 *
 * @author Vladimir Hromada
 */
@Component("genresListController")
public class GenresListController extends AbstractResultController<Void> {

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
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");

        this.genreFacade = genreFacade;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Genre>> result = genreFacade.getAll();

        addResults(result);

        if (processResult()) {
            final PanelData<List<Genre>> panelData = new PanelData<>(GenresListPanel.ID, Model.ofList(result.getData()));
            final PanelData<Void> menuData = new PanelData<>(GenresMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Genres", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_LIST;
    }

}
