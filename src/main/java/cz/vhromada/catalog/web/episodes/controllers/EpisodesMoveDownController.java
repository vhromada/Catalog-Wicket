package cz.vhromada.catalog.web.episodes.controllers;

import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for moving down episode.
 *
 * @author Vladimir Hromada
 */
@Component("episodesMoveDownController")
public class EpisodesMoveDownController extends Controller<IModel<EpisodeTO>> {

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Creates a new instance of EpisodesMoveDownController.
     *
     * @param episodeFacade facade for episodes
     * @throws IllegalArgumentException if facade for episodes is null
     */
    @Autowired
    public EpisodesMoveDownController(final EpisodeFacade episodeFacade) {
        Validators.validateArgumentNotNull(episodeFacade, "Facade for episodes");

        this.episodeFacade = episodeFacade;
    }

    @Override
    public void handle(final IModel<EpisodeTO> data) {
        episodeFacade.moveDown(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_MOVE_DOWN;
    }

}
