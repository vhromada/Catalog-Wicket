package cz.vhromada.catalog.web.program.controller;

import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.program.mo.ProgramMO;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing program.
 *
 * @author Vladimir Hromada
 */
@Component("programCancelController")
public class ProgramCancelController extends Controller<IModel<ProgramMO>> {

    @Override
    public void handle(final IModel<ProgramMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.PROGRAMS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_CANCEL;
    }

}
