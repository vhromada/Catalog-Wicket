package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.facade.to.ProgramTO;
import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for adding program.
 *
 * @author Vladimir Hromada
 */
@Component("addProgramConfirmController")
public class AddProgramConfirmController extends Controller<IModel<ProgramMO>> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddProgramConfirmController.
     *
     * @param programFacade facade for programs
     * @param converter     converter
     * @throws IllegalArgumentException if facade for programs is null
     *                                  or converter is null
     */
    @Autowired
    public AddProgramConfirmController(final ProgramFacade programFacade,
            @Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(programFacade, "Facade for programs");
        Validators.validateArgumentNotNull(converter, "converter");

        this.programFacade = programFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ProgramMO> data) {
        programFacade.add(converter.convert(data.getObject(), ProgramTO.class));

        getUi().fireEvent(new ControllerEvent(Flow.PROGRAMS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return Flow.PROGRAMS_ADD_CONFIRM;
    }

}
