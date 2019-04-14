package cz.vhromada.catalog.web.show.controller;

import java.util.List;
import java.util.stream.Collectors;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.GenreMapper;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.show.mo.ShowMO;
import cz.vhromada.catalog.web.show.panel.ShowFormPanel;
import cz.vhromada.catalog.web.show.panel.ShowsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.validation.result.Result;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing form for adding show.
 *
 * @author Vladimir Hromada
 */
@Component("addShowController")
public class AddShowController extends AbstractResultController<Void> {

    /**
     * Facade for pictures
     */
    private PictureFacade pictureFacade;

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Mapper for genres
     */
    private final GenreMapper genreMapper;

    /**
     * Creates a new instance of AddShowController.
     *
     * @param pictureFacade facade for pictures
     * @param genreFacade   facade for genres
     * @throws IllegalArgumentException if facade for pictures is null
     *                                  or facade for genres is null
     */
    @Autowired
    public AddShowController(final PictureFacade pictureFacade, final GenreFacade genreFacade) {
        Assert.notNull(pictureFacade, "Facade for pictures mustn't be null.");
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");

        this.pictureFacade = pictureFacade;
        this.genreFacade = genreFacade;
        this.genreMapper = Mappers.getMapper(GenreMapper.class);
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Genre>> genres = genreFacade.getAll();
        final Result<List<Picture>> pictures = pictureFacade.getAll();

        addResults(pictures, genres);

        if (processResult()) {
            final WebSession session = CatalogApplication.getSession();
            session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SHOWS_ADD_CONFIRM);
            session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
            final ShowMO show = new ShowMO();
            show.setAllGenres(genres.getData().stream().map(genreMapper::map).collect(Collectors.toList()));
            show.setAllPictures(pictures.getData().stream().map(Picture::getId).collect(Collectors.toList()));
            final PanelData<ShowMO> panelData = new PanelData<>(ShowFormPanel.ID, new CompoundPropertyModel<>(show));
            final PanelData<Void> menuData = new PanelData<>(ShowsMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Add show", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_ADD;
    }

}
