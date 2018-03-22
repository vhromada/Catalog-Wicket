package cz.vhromada.catalog.web.episode.controller;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.episode.mo.EpisodeMO;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.season.controller.SeasonEpisodesController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding episode.
 *
 * @author Vladimir Hromada
 */
@Component("addEpisodeConfirmController")
public class AddEpisodeConfirmController extends AbstractResultController<IModel<EpisodeMO>> {

    /**
     * Facade for episodes
     */
    private EpisodeFacade episodeFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddEpisodeConfirmController.
     *
     * @param episodeFacade facade for episodes
     * @param converter     converter
     * @throws IllegalArgumentException if facade for episodes is null
     *                                  or converter is null
     */
    @Autowired
    public AddEpisodeConfirmController(final EpisodeFacade episodeFacade,
        final Converter converter) {
        Assert.notNull(episodeFacade, "Facade for episodes mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.episodeFacade = episodeFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<EpisodeMO> data) {
        final Season season = CatalogApplication.getSessionAttribute(SeasonEpisodesController.SEASON_ATTRIBUTE);
        final Episode episode = converter.convert(data.getObject(), Episode.class);

        addResults(episodeFacade.add(season, episode));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_ADD_CONFIRM;
    }

}
