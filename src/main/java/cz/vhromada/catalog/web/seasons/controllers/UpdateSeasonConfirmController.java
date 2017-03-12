package cz.vhromada.catalog.web.seasons.controllers;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating season.
 *
 * @author Vladimir Hromada
 */
@Component("updateSeasonConfirmController")
public class UpdateSeasonConfirmController extends ResultController<IModel<SeasonMO>> {

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateSeasonConfirmController.
     *
     * @param seasonFacade facade for seasons
     * @param converter    converter
     * @throws IllegalArgumentException if facade for seasons is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateSeasonConfirmController(final SeasonFacade seasonFacade,
            final Converter converter) {
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.seasonFacade = seasonFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<SeasonMO> data) {
        addResults(seasonFacade.update(converter.convert(data.getObject(), Season.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_UPDATE_CONFIRM;
    }

}
