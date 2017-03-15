package cz.vhromada.catalog.web.episodes.controllers;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for duplicating episode.
 *
 * @author Vladimir Hromada
 */
@Component("episodesDuplicateController")
public class EpisodesDuplicateController extends AbstractResultController<IModel<Episode>> {

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Creates a new instance of EpisodesDuplicateController.
     *
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for episodes is null
     */
    @Autowired
    public EpisodesDuplicateController(final EpisodeFacade episodeFacade) {
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");

        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final IModel<Episode> data) {
        addResults(episodeFacade.duplicate(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_DUPLICATE;
    }

}
