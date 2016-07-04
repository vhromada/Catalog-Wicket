package cz.vhromada.catalog.web.programs.panels;

import cz.vhromada.catalog.facade.to.ProgramTO;
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
import org.apache.wicket.model.Model;
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

        final ListView<ProgramTO> programs = new ListView<ProgramTO>("programs", Model.ofList(getModelObject().getPrograms())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<ProgramTO> item) {
                final ProgramTO program = item.getModelObject();

                final Label name = new Label("name", program.getName());

                final Label mediaCount = new Label("mediaCount", program.getMediaCount());

                final Label additionalData = new Label("additionalData", getAdditionalData(program));

                final Label note = new Label("note", program.getNote());

                final WikipediaLink wikiCz = new WikipediaLink("wikiCz", program.getWikiCz(), WikipediaLink.Country.CZ);

                final WikipediaLink wikiEn = new WikipediaLink("wikiEn", program.getWikiEn(), WikipediaLink.Country.EN);

                final AjaxFlowLink<ProgramTO> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.PROGRAMS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<ProgramTO> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.PROGRAMS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<ProgramTO> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.PROGRAMS_DUPLICATE);

                final AjaxFlowLink<ProgramTO> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.PROGRAMS_UPDATE);

                final AjaxFlowLink<ProgramTO> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.PROGRAMS_REMOVE);

                item.add(name, mediaCount, additionalData, note, wikiCz, wikiEn, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noPrograms = new WebMarkupContainer("noPrograms");
        noPrograms.setVisible(getModelObject().getPrograms().isEmpty());

        final Label count = new Label("count", getModelObject().getPrograms().size());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        programsTable.add(programs);
        add(programsTable, noPrograms, count, mediaCount);
    }

    /**
     * Returns additional data.
     *
     * @param program TO for program
     * @return additional data
     */
    private static String getAdditionalData(final ProgramTO program) {
        final StringBuilder result = new StringBuilder();
        if (program.getCrack()) {
            result.append("Crack");
        }
        addToResult(result, program.getSerialKey(), "serial key");
        if (program.getOtherData() != null && !program.getOtherData().isEmpty()) {
            if (result.length() != 0) {
                result.append(", ");
            }
            result.append(program.getOtherData());
        }

        return result.toString();
    }

    /**
     * Adds data to result.
     *
     * @param result result
     * @param value  value
     * @param data   data
     */
    private static void addToResult(final StringBuilder result, final boolean value, final String data) {
        if (value) {
            if (result.length() == 0) {
                result.append(data.substring(0, 1).toUpperCase());
                result.append(data.substring(1));
            } else {
                result.append(", ");
                result.append(data);
            }
        }
    }

}
