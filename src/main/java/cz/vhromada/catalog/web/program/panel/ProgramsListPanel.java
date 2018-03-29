package cz.vhromada.catalog.web.program.panel;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.program.mo.ProgramsMO;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of programs.
 *
 * @author Vladimir Hromada
 */
@Component(ProgramsListPanel.ID)
@Scope("prototype")
public class ProgramsListPanel extends GenericPanel<ProgramsMO> {

    /**
     * ID
     */
    public static final String ID = "programsListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ProgramsListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ProgramsListPanel(final String id, final IModel<ProgramsMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer programsTable = new WebMarkupContainer("programsTable");
        programsTable.setVisible(!getModelObject().getPrograms().isEmpty());

        final ListView<Program> programs = new ListView<>("programs", getModelObject().getPrograms()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Program> item) {
                final Program program = item.getModelObject();

                final AjaxFlowLink<Program> detail = new AjaxFlowLink<>("detail", item.getModel(), CatalogFlow.PROGRAMS_DETAIL);

                final Label detailText = new Label("detailText", program.getName());

                final AjaxFlowLink<Program> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.PROGRAMS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Program> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.PROGRAMS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Program> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.PROGRAMS_DUPLICATE);

                final AjaxFlowLink<Program> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.PROGRAMS_UPDATE);

                final AjaxFlowLink<Program> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.PROGRAMS_REMOVE);

                detail.add(detailText);
                item.add(detail, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noPrograms = new WebMarkupContainer("noPrograms");
        noPrograms.setVisible(getModelObject().getPrograms().isEmpty());

        final Label count = new Label("count", getModelObject().getPrograms().size());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        programsTable.add(programs);
        add(programsTable, noPrograms, count, mediaCount);
    }

}
