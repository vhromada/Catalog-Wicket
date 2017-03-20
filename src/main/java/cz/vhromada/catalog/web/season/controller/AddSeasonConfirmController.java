package cz.vhromada.catalog.web.season.controller;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.catalog.web.show.controller.ShowSeasonsController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding season.
 *
 * @author Vladimir Hromada
 */
@Component("addSeasonConfirmController")
public class AddSeasonConfirmController extends AbstractResultController<IModel<SeasonMO>> {

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddSeasonConfirmController.
     *
     * @param seasonFacade facade for seasons
     * @param converter    converter
     * @throws IllegalArgumentException if facade for seasons is null
     *                                  or converter is null
     */
    @Autowired
    public AddSeasonConfirmController(final SeasonFacade seasonFacade,
            final Converter converter) {
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.seasonFacade = seasonFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<SeasonMO> data) {
        final Show show = CatalogApplication.getSessionAttribute(ShowSeasonsController.SHOW_ATTRIBUTE);
        final Season season = converter.convert(data.getObject(), Season.class);

        addResults(seasonFacade.add(show, season));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_ADD_CONFIRM;
    }

}
