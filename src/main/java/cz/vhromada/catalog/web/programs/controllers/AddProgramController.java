package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.web.controllers.Controller;
import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.events.PageEvent;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.catalog.web.programs.panels.ProgramFormPanel;
import cz.vhromada.catalog.web.programs.panels.ProgramsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;

import org.apache.wicket.model.CompoundPropertyModel;
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
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, Flow.PROGRAMS_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData panelData = new PanelData(ProgramFormPanel.ID, new CompoundPropertyModel<>(new ProgramMO()));
        final PanelData menuData = new PanelData(ProgramsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Add program", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return Flow.PROGRAMS_ADD;
    }

}