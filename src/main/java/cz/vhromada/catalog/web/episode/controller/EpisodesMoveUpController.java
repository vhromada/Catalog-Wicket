package cz.vhromada.catalog.web.episode.controller;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for moving up episode.
 *
 * @author Vladimir Hromada
 */
@Component("episodesMoveUpController")
public class EpisodesMoveUpController extends AbstractResultController<IModel<Episode>> {

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Creates a new instance of EpisodesMoveUpController.
     *
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for episodes is null
     */
    @Autowired
    public EpisodesMoveUpController(final EpisodeFacade episodeFacade) {
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");

        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final IModel<Episode> data) {
        addResults(episodeFacade.moveUp(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_MOVE_UP;
    }

}
