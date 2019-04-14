package cz.vhromada.catalog.web.movie.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import cz.vhromada.catalog.entity.Genre;
import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.GenreMapper;
import cz.vhromada.catalog.web.mapper.MovieMapper;
import cz.vhromada.catalog.web.movie.mo.MovieMO;
import cz.vhromada.catalog.web.movie.panel.MovieFormPanel;
import cz.vhromada.catalog.web.movie.panel.MoviesMenuPanel;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.validation.result.Result;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.mapstruct.factory.Mappers;
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
     * Facade for pictures
     */
    private PictureFacade pictureFacade;

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Mapper for movies
     */
    private final MovieMapper movieMapper;

    /**
     * Mapper for genres
     */
    private final GenreMapper genreMapper;

    /**
     * Creates a new instance of UpdateMovieController.
     *
     * @param pictureFacade facade for pictures
     * @param genreFacade   facade for genres
     * @throws IllegalArgumentException if facade for pictures is null
     *                                  or facade for genres is null
     */
    @Autowired
    public UpdateMovieController(final PictureFacade pictureFacade, final GenreFacade genreFacade) {
        Assert.notNull(pictureFacade, "Facade for pictures mustn't be null.");
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");

        this.pictureFacade = pictureFacade;
        this.genreFacade = genreFacade;
        this.movieMapper = Mappers.getMapper(MovieMapper.class);
        this.genreMapper = Mappers.getMapper(GenreMapper.class);
    }

    @Override
    public void handle(final IModel<Movie> data) {
        final Result<List<Genre>> genres = genreFacade.getAll();
        final Result<List<Picture>> pictures = pictureFacade.getAll();

        addResults(pictures, genres);

        if (processResult()) {
            final WebSession session = CatalogApplication.getSession();
            session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.MOVIES_UPDATE_CONFIRM);
            session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
            final MovieMO movie = movieMapper.map(data.getObject());
            if (movie.getSubtitles() == null) {
                movie.setSubtitles(new ArrayList<>());
            }
            if (movie.getMedia() == null) {
                final TimeMO time = new TimeMO();
                final List<TimeMO> media = new ArrayList<>();
                media.add(time);
                movie.setMedia(media);
            }
            movie.setAllGenres(genres.getData().stream().map(genreMapper::map).collect(Collectors.toList()));
            movie.setAllPictures(pictures.getData().stream().map(Picture::getId).collect(Collectors.toList()));
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
