package cz.vhromada.catalog.web.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.GenreMapper;
import cz.vhromada.catalog.web.movie.mo.MovieMO;
import cz.vhromada.catalog.web.movie.panel.MovieFormPanel;
import cz.vhromada.catalog.web.movie.panel.MoviesMenuPanel;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
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
 * A class represents controller for showing form for adding movie.
 *
 * @author Vladimir Hromada
 */
@Component("addMovieController")
public class AddMovieController extends AbstractResultController<Void> {

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
     * Creates a new instance of AddMovieController.
     *
     * @param pictureFacade facade for pictures
     * @param genreFacade   facade for genres
     * @throws IllegalArgumentException if facade for pictures is null
     *                                  or facade for genres is null
     *                                  or converter is null
     */
    @Autowired
    public AddMovieController(final PictureFacade pictureFacade, final GenreFacade genreFacade) {
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
            session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MOVIES_ADD_CONFIRM);
            session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
            final TimeMO time = new TimeMO();
            final List<TimeMO> media = new ArrayList<>();
            media.add(time);
            final MovieMO movie = new MovieMO();
            movie.setSubtitles(new ArrayList<>());
            movie.setMedia(media);
            movie.setAllGenres(genres.getData().stream().map(genreMapper::map).collect(Collectors.toList()));
            movie.setAllPictures(pictures.getData().stream().map(Picture::getId).collect(Collectors.toList()));
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
