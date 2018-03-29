package cz.vhromada.catalog.web.program.controller;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.program.panel.ProgramDetailPanel;
import cz.vhromada.catalog.web.program.panel.ProgramsMenuPanel;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing detail of program.
 *
 * @author Vladimir Hromada
 */
@Component("programDetailController")
public class ProgramDetailController extends Controller<IModel<Program>> {

    @Override
    public void handle(final IModel<Program> data) {
        final PanelData<Program> panelData = new PanelData<>(ProgramDetailPanel.ID, data);
        final PanelData<Void> menuData = new PanelData<>(ProgramsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Program detail", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_DETAIL;
    }

}
