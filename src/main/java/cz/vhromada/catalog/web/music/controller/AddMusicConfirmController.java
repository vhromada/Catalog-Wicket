package cz.vhromada.catalog.web.music.controller;

import cz.vhromada.catalog.facade.MusicFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.MusicMapper;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding music.
 *
 * @author Vladimir Hromada
 */
@Component("addMusicConfirmController")
public class AddMusicConfirmController extends AbstractResultController<IModel<MusicMO>> {

    /**
     * Facade for music
     */
    private MusicFacade musicFacade;

    /**
     * Mapper for music
     */
    private final MusicMapper musicMapper;

    /**
     * Creates a new instance of AddMusicConfirmController.
     *
     * @param musicFacade facade for music
     * @throws IllegalArgumentException if facade for music is null
     */
    @Autowired
    public AddMusicConfirmController(final MusicFacade musicFacade) {
        Assert.notNull(musicFacade, "Facade for music mustn't be null.");

        this.musicFacade = musicFacade;
        this.musicMapper = Mappers.getMapper(MusicMapper.class);
    }

    @Override
    public void handle(final IModel<MusicMO> data) {
        addResults(musicFacade.add(musicMapper.mapBack(data.getObject())));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.MUSIC_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.MUSIC_ADD_CONFIRM;
    }

}
