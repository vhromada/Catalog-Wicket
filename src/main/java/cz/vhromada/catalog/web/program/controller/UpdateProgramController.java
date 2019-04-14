package cz.vhromada.catalog.web.program.controller;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.mapper.ProgramMapper;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.program.mo.ProgramMO;
import cz.vhromada.catalog.web.program.panel.ProgramFormPanel;
import cz.vhromada.catalog.web.program.panel.ProgramsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating program.
 *
 * @author Vladimir Hromada
 */
@Component("updateProgramController")
public class UpdateProgramController extends Controller<IModel<Program>> {

    /**
     * Mapper for programs
     */
    private final ProgramMapper programMapper;

    /**
     * Creates a new instance of UpdateProgramController.
     */
    @Autowired
    public UpdateProgramController() {
        this.programMapper = Mappers.getMapper(ProgramMapper.class);
    }

    @Override
    public void handle(final IModel<Program> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.PROGRAMS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<ProgramMO> panelData = new PanelData<>(ProgramFormPanel.ID, new CompoundPropertyModel<>(programMapper.map(data.getObject())));
        final PanelData<Void> menuData = new PanelData<>(ProgramsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit program", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_UPDATE;
    }

}
