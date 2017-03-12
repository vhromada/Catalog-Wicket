package cz.vhromada.catalog.web.programs.panels;

import java.util.List;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.common.FormatUtils;
import cz.vhromada.catalog.web.components.WikipediaLink;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.programs.mo.ProgramsMO;
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

        final ListView<Program> programs = new ProgramsListView("programs", getModelObject().getPrograms());

        final WebMarkupContainer noPrograms = new WebMarkupContainer("noPrograms");
        noPrograms.setVisible(getModelObject().getPrograms().isEmpty());

        final Label count = new Label("count", getModelObject().getPrograms().size());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        programsTable.add(programs);
        add(programsTable, noPrograms, count, mediaCount);
    }

    /**
     * A class represents list view with programs.
     */
    private static final class ProgramsListView extends ListView<Program> {

        /**
         * SerialVersionUID
         */
        private static final long serialVersionUID = 1L;

        /**
         * Creates a new instance of ProgramsListView.
         *
         * @param id   ID
         * @param list list of programs
         * @throws org.apache.wicket.WicketRuntimeException if ID is null
         */
        ProgramsListView(final String id, final List<Program> list) {
            super(id, list);
        }

        @Override
        protected void populateItem(final ListItem<Program> item) {
            final Program program = item.getModelObject();

            final Label name = new Label("name", program.getName());

            final Label mediaCount = new Label("mediaCount", program.getMediaCount());

            final Label additionalData = new Label("additionalData", getAdditionalData(program));

            final Label note = new Label("note", program.getNote());

            final WikipediaLink wikiCz = new WikipediaLink("wikiCz", program.getWikiCz(), WikipediaLink.Country.CZ);

            final WikipediaLink wikiEn = new WikipediaLink("wikiEn", program.getWikiEn(), WikipediaLink.Country.EN);

            final AjaxFlowLink<Program> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.PROGRAMS_MOVE_UP);
            moveUp.setVisible(item.getIndex() > 0);

            final AjaxFlowLink<Program> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.PROGRAMS_MOVE_DOWN);
            moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

            final AjaxFlowLink<Program> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.PROGRAMS_DUPLICATE);

            final AjaxFlowLink<Program> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.PROGRAMS_UPDATE);

            final AjaxFlowLink<Program> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.PROGRAMS_REMOVE);

            item.add(name, mediaCount, additionalData, note, wikiCz, wikiEn, moveUp, moveDown, duplicate, edit, remove);
        }

        /**
         * Returns additional data.
         *
         * @param program program
         * @return additional data
         */
        private static String getAdditionalData(final Program program) {
            final StringBuilder result = new StringBuilder();
            if (program.getCrack()) {
                result.append("Crack");
            }
            FormatUtils.addToResult(result, program.getSerialKey(), "serial key");
            if (program.getOtherData() != null && !program.getOtherData().isEmpty()) {
                if (result.length() != 0) {
                    result.append(", ");
                }
                result.append(program.getOtherData());
            }

            return result.toString();
        }

    }

}
