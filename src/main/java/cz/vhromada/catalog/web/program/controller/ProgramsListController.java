package cz.vhromada.catalog.web.program.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.facade.ProgramFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.program.mo.ProgramsMO;
import cz.vhromada.catalog.web.program.panel.ProgramsListPanel;
import cz.vhromada.catalog.web.program.panel.ProgramsMenuPanel;
import cz.vhromada.validation.result.Result;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of programs.
 *
 * @author Vladimir Hromada
 */
@Component("programsListController")
public class ProgramsListController extends AbstractResultController<Void> {

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
        Assert.notNull(programFacade, "Facade for programs mustn't be null.");

        this.programFacade = programFacade;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Program>> programsResult = programFacade.getAll();
        final Result<Integer> mediaCountResult = programFacade.getTotalMediaCount();

        addResults(programsResult, mediaCountResult);

        if (processResult()) {
            final ProgramsMO programs = new ProgramsMO();
            programs.setPrograms(programsResult.getData());
            programs.setMediaCount(mediaCountResult.getData());
            final PanelData<ProgramsMO> panelData = new PanelData<>(ProgramsListPanel.ID, Model.of(programs));
            final PanelData<Void> menuData = new PanelData<>(ProgramsMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Programs", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PROGRAMS_LIST;
    }

}
