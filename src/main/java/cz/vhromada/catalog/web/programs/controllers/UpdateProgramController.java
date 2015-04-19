package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.facade.to.ProgramTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.catalog.web.programs.panels.ProgramFormPanel;
import cz.vhromada.catalog.web.programs.panels.ProgramsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating program.
 *
 * @author Vladimir Hromada
 */
@Component("updateProgramController")
public class UpdateProgramController extends Controller<IModel<ProgramTO>> {

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
    public UpdateProgramController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<ProgramTO> data) {
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.PROGRAMS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData panelData = new PanelData(ProgramFormPanel.ID, new CompoundPropertyModel<>(converter.convert(data.getObject(), ProgramMO.class)));
        final PanelData menuData = new PanelData(ProgramsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit program", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_UPDATE;
    }

}
