package cz.vhromada.catalog.web.program.controller;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.program.mo.ProgramMO;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding program.
 *
 * @author Vladimir Hromada
 */
@Component("addProgramConfirmController")
public class AddProgramConfirmController extends AbstractResultController<IModel<ProgramMO>> {

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
            final Converter converter) {
        Assert.notNull(programFacade, "Facade for programs mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.programFacade = programFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ProgramMO> data) {
        addResults(programFacade.add(converter.convert(data.getObject(), Program.class)));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_ADD_CONFIRM;
    }

}