package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.validators.Validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for creating new data.
 *
 * @author Vladimir Hromada
 */
@Component("programsNewDataController")
public class ProgramsNewDataController extends Controller<Void> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Creates a new instance of ProgramsNewDataController.
     *
     * @param programFacade facade for programs
     * @throws IllegalArgumentException if facade for programs is null
     */
    @Autowired
    public ProgramsNewDataController(final ProgramFacade programFacade) {
        Validators.validateArgumentNotNull(programFacade, "Facade for programs");

        this.programFacade = programFacade;
    }

    @Override
    public void handle(final Void data) {
        programFacade.newData();

        getUi().fireEvent(new ControllerEvent(Flow.PROGRAMS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return Flow.PROGRAMS_NEW_DATA;
    }

}
