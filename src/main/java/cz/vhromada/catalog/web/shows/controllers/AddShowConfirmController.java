package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.facade.ShowFacade;
import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for adding show.
 *
 * @author Vladimir Hromada
 */
@Component("addShowConfirmController")
public class AddShowConfirmController extends Controller<IModel<ShowMO>> {

    /**
     * Facade for shows
     */
    private ShowFacade showFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddShowConfirmController.
     *
     * @param showFacade facade for shows
     * @param converter  converter
     * @throws IllegalArgumentException if facade for shows is null
     *                                  or converter is null
     */
    @Autowired
    public AddShowConfirmController(final ShowFacade showFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(showFacade, "Facade for shows");
        Validators.validateArgumentNotNull(converter, "converter");

        this.showFacade = showFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ShowMO> data) {
        showFacade.add(converter.convert(data.getObject(), ShowTO.class));

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SHOWS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_ADD_CONFIRM;
    }

}
