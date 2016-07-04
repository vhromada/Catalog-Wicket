package cz.vhromada.catalog.web.seasons.controllers;

import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.catalog.web.shows.controllers.ShowSeasonsController;
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
 * A class represents controller for adding season.
 *
 * @author Vladimir Hromada
 */
@Component("addSeasonConfirmController")
public class AddSeasonConfirmController extends Controller<IModel<SeasonMO>> {

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
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(seasonFacade, "Facade for seasons");
        Validators.validateArgumentNotNull(converter, "converter");

        this.seasonFacade = seasonFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<SeasonMO> data) {
        final ShowTO show = CatalogApplication.getSessionAttribute(ShowSeasonsController.SHOW_ATTRIBUTE);
        final SeasonTO season = converter.convert(data.getObject(), SeasonTO.class);
        seasonFacade.add(show, season);

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_ADD_CONFIRM;
    }

}
