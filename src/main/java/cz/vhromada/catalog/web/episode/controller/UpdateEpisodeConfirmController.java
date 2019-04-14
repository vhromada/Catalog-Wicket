package cz.vhromada.catalog.web.episode.controller;

import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.episode.mo.EpisodeMO;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.EpisodeMapper;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating episode.
 *
 * @author Vladimir Hromada
 */
@Component("updateEpisodeConfirmController")
public class UpdateEpisodeConfirmController extends AbstractResultController<IModel<EpisodeMO>> {

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Mapper for episodes
     */
    private final EpisodeMapper episodeMapper;

    /**
     * Creates a new instance of UpdateEpisodeConfirmController.
     *
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for episodes is null
     */
    @Autowired
    public UpdateEpisodeConfirmController(final EpisodeFacade episodeFacade) {
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");

        this.episodeFacade = episodeFacade;
        this.episodeMapper = Mappers.getMapper(EpisodeMapper.class);
    }

    @Override
    public void handle(final IModel<EpisodeMO> data) {
        addResults(episodeFacade.update(episodeMapper.mapBack(data.getObject())));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_UPDATE_CONFIRM;
    }

}
