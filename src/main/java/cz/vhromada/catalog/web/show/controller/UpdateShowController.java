package cz.vhromada.catalog.web.show.controller;

import java.util.List;
import java.util.stream.Collectors;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.show.mo.ShowMO;
import cz.vhromada.catalog.web.show.panel.ShowFormPanel;
import cz.vhromada.catalog.web.show.panel.ShowsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converter.Converter;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controller.Flow;

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
public class UpdateShowController extends AbstractResultController<IModel<Show>> {

    /**
     * Facade for pictures
     */
    private PictureFacade pictureFacade;

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
     * @param pictureFacade facade for pictures
     * @param genreFacade   facade for genres
     * @param converter     converter
     * @throws IllegalArgumentException if facade for pictures is null
     *                                  or facade for genres is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateShowController(final PictureFacade pictureFacade, final GenreFacade genreFacade, final Converter converter) {
        Assert.notNull(pictureFacade, "Facade for pictures mustn't be null.");
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.pictureFacade = pictureFacade;
        this.genreFacade = genreFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Show> data) {
        final Result<List<Genre>> genres = genreFacade.getAll();
        final Result<List<Picture>> pictures = pictureFacade.getAll();

        addResults(pictures, genres);

        if (processResult()) {
            final WebSession session = CatalogApplication.getSession();
            session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SHOWS_UPDATE_CONFIRM);
            session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
            final ShowMO show = converter.convert(data.getObject(), ShowMO.class);
            show.setAllGenres(converter.convertCollection(genres.getData(), GenreMO.class));
            show.setAllPictures(pictures.getData().stream().map(Picture::getId).collect(Collectors.toList()));
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
