package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;

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
        getUi().fireEvent(new ControllerEvent(Flow.PROGRAMS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return Flow.PROGRAMS_CANCEL;
    }

}
