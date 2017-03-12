package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for removing program.
 *
 * @author Vladimir Hromada
 */
@Component("programsRemoveController")
public class ProgramsRemoveController extends ResultController<IModel<Program>> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Creates a new instance of ProgramsRemoveController.
     *
     * @param programFacade facade for programs
     * @throws IllegalArgumentException if facade for programs is null
     */
    @Autowired
    public ProgramsRemoveController(final ProgramFacade programFacade) {
        Assert.notNull(programFacade, "Facade for programs mustn't be null.");

        this.programFacade = programFacade;
    }

    @Override
    public void handle(final IModel<Program> data) {
        addResults(programFacade.remove(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_REMOVE;
    }

}
