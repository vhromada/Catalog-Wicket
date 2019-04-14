package cz.vhromada.catalog.web.show.controller;

import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.ShowMapper;
import cz.vhromada.catalog.web.show.mo.ShowMO;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding show.
 *
 * @author Vladimir Hromada
 */
@Component("addShowConfirmController")
public class AddShowConfirmController extends AbstractResultController<IModel<ShowMO>> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Mapper for shows
     */
    private final ShowMapper showMapper;

    /**
     * Creates a new instance of AddShowConfirmController.
     *
     * @param showFacade facade for shows
     * @throws IllegalArgumentException if facade for shows is null
     */
    @Autowired
    public AddShowConfirmController(final ShowFacade showFacade) {
        Assert.notNull(showFacade, "Facade for shows mustn't be null.");

        this.showFacade = showFacade;
        this.showMapper = Mappers.getMapper(ShowMapper.class);
    }

    @Override
    public void handle(final IModel<ShowMO> data) {
        addResults(showFacade.add(showMapper.mapBack(data.getObject())));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_ADD_CONFIRM;
    }

}
