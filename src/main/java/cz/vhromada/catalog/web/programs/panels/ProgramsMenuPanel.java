package cz.vhromada.catalog.web.programs.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for programs.
 *
 * @author Vladimir Hromada
 */
@Component(ProgramsMenuPanel.ID)
@Scope("prototype")
public class ProgramsMenuPanel extends BasePanel<Void> {

    /**
     * ID
     */
    public static final String ID = "programsMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ProgramsMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ProgramsMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allPrograms = new AjaxFlowLink<>("allPrograms", CatalogFlow.PROGRAMS_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.PROGRAMS_NEW_DATA);

        final AjaxFlowLink<Void> addProgram = new AjaxFlowLink<>("addProgram", CatalogFlow.PROGRAMS_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", CatalogFlow.PROGRAMS_UPDATE_POSITION);

        add(allPrograms, newData, addProgram, updatePositions);
    }

}
