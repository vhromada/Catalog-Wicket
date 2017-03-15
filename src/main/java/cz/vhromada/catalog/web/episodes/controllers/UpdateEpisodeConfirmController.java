package cz.vhromada.catalog.web.episodes.controllers;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
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
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateEpisodeConfirmController.
     *
     * @param episodeFacade facade for episodes
     * @param converter     converter
     * @throws IllegalArgumentException if facade for episodes is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateEpisodeConfirmController(final EpisodeFacade episodeFacade,
            final Converter converter) {
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.episodeFacade = episodeFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<EpisodeMO> data) {
        addResults(episodeFacade.update(converter.convert(data.getObject(), Episode.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_UPDATE_CONFIRM;
    }

}
