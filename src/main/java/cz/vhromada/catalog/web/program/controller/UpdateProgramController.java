package cz.vhromada.catalog.web.program.controller;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.program.mo.ProgramMO;
import cz.vhromada.catalog.web.program.panel.ProgramFormPanel;
import cz.vhromada.catalog.web.program.panel.ProgramsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing form for updating program.
 *
 * @author Vladimir Hromada
 */
@Component("updateProgramController")
public class UpdateProgramController extends Controller<IModel<Program>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateProgramController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateProgramController(final Converter converter) {
        Assert.notNull(converter, "Converter mustn't be null.");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Program> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.PROGRAMS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<ProgramMO> panelData = new PanelData<>(ProgramFormPanel.ID,
            new CompoundPropertyModel<>(converter.convert(data.getObject(), ProgramMO.class)));
        final PanelData<Void> menuData = new PanelData<>(ProgramsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit program", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_UPDATE;
    }

}
