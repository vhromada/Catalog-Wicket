package cz.vhromada.catalog.web.seasons.controllers;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for moving down season.
 *
 * @author Vladimir Hromada
 */
@Component("seasonsMoveDownController")
public class SeasonsMoveDownController extends AbstractResultController<IModel<Season>> {

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Creates a new instance of SeasonsMoveDownController.
     *
     * @param seasonFacade facade for seasons
     * @throws IllegalArgumentException if facade for seasons is null
     */
    @Autowired
    public SeasonsMoveDownController(final SeasonFacade seasonFacade) {
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");

        this.seasonFacade = seasonFacade;
    }

    @Override
    public void handle(final IModel<Season> data) {
        addResults(seasonFacade.moveDown(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_MOVE_DOWN;
    }

}
