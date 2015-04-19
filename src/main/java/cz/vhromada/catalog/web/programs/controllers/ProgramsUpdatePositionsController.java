package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for updating positions.
 *
 * @author Vladimir Hromada
 */
@Component("programsUpdatePositionsController")
public class ProgramsUpdatePositionsController extends Controller<Void> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Creates a new instance of ProgramsUpdatePositionsController.
     *
     * @param programFacade facade for programs
     * @throws IllegalArgumentException if facade for programs is null
     */
    @Autowired
    public ProgramsUpdatePositionsController(final ProgramFacade programFacade) {
        Validators.validateArgumentNotNull(programFacade, "Facade for programs");

        this.programFacade = programFacade;
    }

    @Override
    public void handle(final Void data) {
        programFacade.updatePositions();

        getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_UPDATE_POSITION;
    }

}
