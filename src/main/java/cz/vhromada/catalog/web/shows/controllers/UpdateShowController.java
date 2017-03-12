package cz.vhromada.catalog.web.shows.controllers;

import java.util.List;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.commons.ResultController;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.catalog.web.shows.panels.ShowFormPanel;
import cz.vhromada.catalog.web.shows.panels.ShowsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing form for updating show.
 *
 * @author Vladimir Hromada
 */
@Component("updateShowController")
public class UpdateShowController extends ResultController<IModel<Show>> {

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
            final Converter converter) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Show> data) {
        final Result<List<Genre>> result = genreFacade.getAll();

        addResults(result);

        if (processResult()) {
            final WebSession session = CatalogApplication.getSession();
            session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SHOWS_UPDATE_CONFIRM);
            session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
            final ShowMO show = converter.convert(data.getObject(), ShowMO.class);
            show.setAllGenres(converter.convertCollection(result.getData(), GenreMO.class));
            final PanelData<ShowMO> panelData = new PanelData<>(ShowFormPanel.ID, new CompoundPropertyModel<>(show));
            final PanelData<Void> menuData = new PanelData<>(ShowsMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Edit show", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_UPDATE;
    }

}
