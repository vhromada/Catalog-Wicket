package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.facade.to.ProgramTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for duplicating program.
 *
 * @author Vladimir Hromada
 */
@Component("programsDuplicateController")
public class ProgramsDuplicateController extends Controller<IModel<ProgramTO>> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Creates a new instance of ProgramsDuplicateController.
     *
     * @param programFacade facade for programs
     * @throws IllegalArgumentException if facade for programs is null
     */
    @Autowired
    public ProgramsDuplicateController(final ProgramFacade programFacade) {
        Validators.validateArgumentNotNull(programFacade, "Facade for programs");

        this.programFacade = programFacade;
    }

    @Override
    public void handle(final IModel<ProgramTO> data) {
        programFacade.duplicate(data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_DUPLICATE;
    }

}
