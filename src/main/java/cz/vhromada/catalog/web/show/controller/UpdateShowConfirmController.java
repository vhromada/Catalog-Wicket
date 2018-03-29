package cz.vhromada.catalog.web.show.controller;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.show.mo.ShowMO;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating show.
 *
 * @author Vladimir Hromada
 */
@Component("updateShowConfirmController")
public class UpdateShowConfirmController extends AbstractResultController<IModel<ShowMO>> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateShowConfirmController.
     *
     * @param showFacade facade for shows
     * @param converter  converter
     * @throws IllegalArgumentException if facade for shows is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateShowConfirmController(final ShowFacade showFacade, final Converter converter) {
        Assert.notNull(showFacade, "Facade for shows mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.showFacade = showFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ShowMO> data) {
        addResults(showFacade.update(converter.convert(data.getObject(), Show.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_UPDATE_CONFIRM;
    }

}
