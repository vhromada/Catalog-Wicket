package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.catalog.web.programs.panels.ProgramFormPanel;
import cz.vhromada.catalog.web.programs.panels.ProgramsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding program.
 *
 * @author Vladimir Hromada
 */
@Component("addProgramController")
public class AddProgramController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.PROGRAMS_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData<ProgramMO> panelData = new PanelData<>(ProgramFormPanel.ID, new CompoundPropertyModel<>(new ProgramMO()));
        final PanelData<Void> menuData = new PanelData<>(ProgramsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Add program", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_ADD;
    }

}
