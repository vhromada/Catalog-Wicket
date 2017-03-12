package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.common.ResultController;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating program.
 *
 * @author Vladimir Hromada
 */
@Component("updateProgramConfirmController")
public class UpdateProgramConfirmController extends ResultController<IModel<ProgramMO>> {

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
            final Converter converter) {
        Assert.notNull(programFacade, "Facade for programs mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.programFacade = programFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ProgramMO> data) {
        addResults(programFacade.update(converter.convert(data.getObject(), Program.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_UPDATE_CONFIRM;
    }

}
