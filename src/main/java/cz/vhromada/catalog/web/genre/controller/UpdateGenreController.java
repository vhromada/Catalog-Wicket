package cz.vhromada.catalog.web.genre.controller;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.catalog.web.genre.panel.GenreFormPanel;
import cz.vhromada.catalog.web.genre.panel.GenresMenuPanel;
import cz.vhromada.catalog.web.mapper.GenreMapper;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating genre.
 *
 * @author Vladimir Hromada
 */
@Component("updateGenreController")
public class UpdateGenreController extends Controller<IModel<Genre>> {

    /**
     * Mapper for genres
     */
    private final GenreMapper genreMapper;

    /**
     * Creates a new instance of UpdateGenreController.
     */
    @Autowired
    public UpdateGenreController() {
        this.genreMapper = Mappers.getMapper(GenreMapper.class);
    }

    @Override
    public void handle(final IModel<Genre> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GENRES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<GenreMO> panelData = new PanelData<>(GenreFormPanel.ID, new CompoundPropertyModel<>(genreMapper.map(data.getObject())));
        final PanelData<Void> menuData = new PanelData<>(GenresMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit genre", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_UPDATE;
    }

}
