package cz.vhromada.catalog.web.programs.controllers;

import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.programs.mo.ProgramsMO;
import cz.vhromada.catalog.web.programs.panels.ProgramsListPanel;
import cz.vhromada.catalog.web.programs.panels.ProgramsMenuPanel;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing list of programs.
 *
 * @author Vladimir Hromada
 */
@Component("programsListController")
public class ProgramsListController extends Controller<Void> {

    /**
     * Facade for programs
     */
    private ProgramFacade programFacade;

    /**
     * Creates a new instance of ProgramsListController.
     *
     * @param programFacade facade for programs
     * @throws IllegalArgumentException if facade for programs is null
     */
    @Autowired
    public ProgramsListController(final ProgramFacade programFacade) {
        Validators.validateArgumentNotNull(programFacade, "Facade for programs");

        this.programFacade = programFacade;
    }

    @Override
    public void handle(final Void data) {
        final ProgramsMO programs = new ProgramsMO();
        programs.setPrograms(programFacade.getPrograms());
        programs.setMediaCount(programFacade.getTotalMediaCount());
        final PanelData<ProgramsMO> panelData = new PanelData<>(ProgramsListPanel.ID, Model.of(programs));
        final PanelData<Void> menuData = new PanelData<>(ProgramsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Programs", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_LIST;
    }

}
