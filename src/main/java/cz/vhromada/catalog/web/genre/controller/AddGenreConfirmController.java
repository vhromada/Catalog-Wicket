package cz.vhromada.catalog.web.genre.controller;

import cz.vhromada.catalog.facade.GenreFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.catalog.web.mapper.GenreMapper;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding genre.
 *
 * @author Vladimir Hromada
 */
@Component("addGenreConfirmController")
public class AddGenreConfirmController extends AbstractResultController<IModel<GenreMO>> {

    /**
     * Facade for genres
     */
    private GenreFacade genreFacade;

    /**
     * Mapper for genres
     */
    private final GenreMapper genreMapper;

    /**
     * Creates a new instance of AddGenreConfirmController.
     *
     * @param genreFacade facade for genres
     * @throws IllegalArgumentException if facade for genres is null
     */
    @Autowired
    public AddGenreConfirmController(final GenreFacade genreFacade) {
        Assert.notNull(genreFacade, "Facade for genres mustn't be null.");

        this.genreFacade = genreFacade;
        this.genreMapper = Mappers.getMapper(GenreMapper.class);
    }

    @Override
    public void handle(final IModel<GenreMO> data) {
        addResults(genreFacade.add(genreMapper.mapBack(data.getObject())));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.GENRES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_ADD_CONFIRM;
    }

}
