package cz.vhromada.catalog.web.season.controller;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.SeasonFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.SeasonMapper;
import cz.vhromada.catalog.web.season.mo.SeasonMO;
import cz.vhromada.catalog.web.show.controller.ShowSeasonsController;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
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
     * Mapper for seasons
     */
    private final SeasonMapper seasonMapper;

    /**
     * Creates a new instance of AddSeasonConfirmController.
     *
     * @param seasonFacade facade for seasons
     * @throws IllegalArgumentException if facade for seasons is null
     */
    @Autowired
    public AddSeasonConfirmController(final SeasonFacade seasonFacade) {
        Assert.notNull(seasonFacade, "Facade for seasons mustn't be null.");

        this.seasonFacade = seasonFacade;
        this.seasonMapper = Mappers.getMapper(SeasonMapper.class);
    }

    @Override
    public void handle(final IModel<SeasonMO> data) {
        final Show show = CatalogApplication.getSessionAttribute(ShowSeasonsController.SHOW_ATTRIBUTE);
        final Season season = seasonMapper.mapBack(data.getObject());

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
