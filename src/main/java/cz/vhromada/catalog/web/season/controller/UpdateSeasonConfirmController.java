package cz.vhromada.catalog.web.season.controller;

import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.SeasonMapper;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating season.
 *
 * @author Vladimir Hromada
 */
@Component("updateSeasonConfirmController")
public class UpdateSeasonConfirmController extends AbstractResultController<IModel<SeasonMO>> {

    /**
     * Facade for seasons
     */
    private SeasonFacade seasonFacade;

    /**
     * Mapper for seasons
     */
    private final SeasonMapper seasonMapper;

    /**
     * Creates a new instance of UpdateSeasonConfirmController.
     *
     * @param seasonFacade facade for seasons
     * @throws IllegalArgumentException if facade for seasons is null
     */
    @Autowired
    public UpdateSeasonConfirmController(final SeasonFacade seasonFacade) {
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");

        this.seasonFacade = seasonFacade;
        this.seasonMapper = Mappers.getMapper(SeasonMapper.class);
    }

    @Override
    public void handle(final IModel<SeasonMO> data) {
        addResults(seasonFacade.update(seasonMapper.mapBack(data.getObject())));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_UPDATE_CONFIRM;
    }

}
