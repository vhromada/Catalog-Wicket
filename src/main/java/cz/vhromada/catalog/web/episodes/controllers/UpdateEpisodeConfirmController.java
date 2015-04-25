package cz.vhromada.catalog.web.episodes.controllers;

import cz.vhromada.catalog.facade.EpisodeFacade;
import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.controllers.SeasonEpisodesController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating episode.
 *
 * @author Vladimir Hromada
 */
@Component("updateEpisodeConfirmController")
public class UpdateEpisodeConfirmController extends Controller<IModel<EpisodeMO>> {

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
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(episodeFacade, "Facade for episodes");
        Validators.validateArgumentNotNull(converter, "converter");

        this.episodeFacade = episodeFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<EpisodeMO> data) {
        final SeasonTO season = CatalogApplication.getSessionAttribute(SeasonEpisodesController.SEASON_ATTRIBUTE);
        final EpisodeTO episode = converter.convert(data.getObject(), EpisodeTO.class);
        episode.setSeason(season);
        episodeFacade.update(episode);

        getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_UPDATE_CONFIRM;
    }

}
