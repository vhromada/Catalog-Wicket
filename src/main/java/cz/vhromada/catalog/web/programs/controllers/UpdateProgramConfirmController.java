package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.facade.to.ProgramTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating program.
 *
 * @author Vladimir Hromada
 */
@Component("updateProgramConfirmController")
public class UpdateProgramConfirmController extends Controller<IModel<ProgramMO>> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateProgramConfirmController.
     *
     * @param programFacade facade for programs
     * @param converter     converter
     * @throws IllegalArgumentException if facade for programs is null
     *                                  or converter is null
     */
    @Autowired
    public UpdateProgramConfirmController(final ProgramFacade programFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(programFacade, "Facade for programs");
        Validators.validateArgumentNotNull(converter, "converter");

        this.programFacade = programFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ProgramMO> data) {
        programFacade.update(converter.convert(data.getObject(), ProgramTO.class));

        getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_UPDATE_CONFIRM;
    }

}
