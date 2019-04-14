package cz.vhromada.catalog.web.program.controller;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.ProgramMapper;
import cz.vhromada.catalog.web.program.mo.ProgramMO;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for updating program.
 *
 * @author Vladimir Hromada
 */
@Component("updateProgramConfirmController")
public class UpdateProgramConfirmController extends AbstractResultController<IModel<ProgramMO>> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Mapper for programs
     */
    private final ProgramMapper programMapper;

    /**
     * Creates a new instance of UpdateProgramConfirmController.
     *
     * @param programFacade facade for programs
     * @throws IllegalArgumentException if facade for programs is null
     */
    @Autowired
    public UpdateProgramConfirmController(final ProgramFacade programFacade) {
        Assert.notNull(programFacade, "Facade for programs mustn't be null.");

        this.programFacade = programFacade;
        this.programMapper = Mappers.getMapper(ProgramMapper.class);
    }

    @Override
    public void handle(final IModel<ProgramMO> data) {
        addResults(programFacade.update(programMapper.mapBack(data.getObject())));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_UPDATE_CONFIRM;
    }

}
