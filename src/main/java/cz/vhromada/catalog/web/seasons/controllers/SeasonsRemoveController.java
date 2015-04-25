package cz.vhromada.catalog.web.seasons.controllers;

import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for removing season.
 *
 * @author Vladimir Hromada
 */
@Component("seasonsRemoveController")
public class SeasonsRemoveController extends Controller<IModel<SeasonTO>> {

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Creates a new instance of SeasonsRemoveController.
     *
     * @param seasonFacade facade for seasons
     * @throws IllegalArgumentException if facade for seasons is null
     */
    @Autowired
    public SeasonsRemoveController(final SeasonFacade seasonFacade) {
        Validators.validateArgumentNotNull(seasonFacade, "Facade for seasons");

        this.seasonFacade = seasonFacade;
    }

    @Override
    public void handle(final IModel<SeasonTO> data) {
        seasonFacade.remove(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_REMOVE;
    }

}