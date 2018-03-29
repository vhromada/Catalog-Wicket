package cz.vhromada.catalog.web.program.panel;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.common.FormatUtils;
import cz.vhromada.catalog.web.component.WikipediaLink;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with program's detail.
 *
 * @author Vladimir Hromada
 */
@Component(ProgramDetailPanel.ID)
@Scope("prototype")
public class ProgramDetailPanel extends GenericPanel<Program> {

    /**
     * ID
     */
    public static final String ID = "programDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ProgramDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ProgramDetailPanel(final String id, final IModel<Program> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Program program = getModelObject();
        final String additionalDataValue = getAdditionalData(program);

        final Label name = new Label("name", program.getName());

        final Label mediaCount = new Label("mediaCount", program.getMediaCount());

        final WebMarkupContainer additionalDataContainer = new WebMarkupContainer("additionalDataContainer");
        additionalDataContainer.setVisible(!StringUtils.isEmpty(additionalDataValue));

        final Label additionalData = new Label("additionalData", additionalDataValue);

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(program.getNote()));

        final Label note = new Label("note", program.getNote());

        final WebMarkupContainer wikiCzContainer = new WebMarkupContainer("wikiCzContainer");
        wikiCzContainer.setVisible(!StringUtils.isEmpty(program.getWikiCz()));

        final WikipediaLink wikiCz = new WikipediaLink("wikiCz", program.getWikiCz(), WikipediaLink.Country.CZ);

        final WebMarkupContainer wikiEnContainer = new WebMarkupContainer("wikiEnContainer");
        wikiEnContainer.setVisible(!StringUtils.isEmpty(program.getWikiEn()));

        final WikipediaLink wikiEn = new WikipediaLink("wikiEn", program.getWikiEn(), WikipediaLink.Country.EN);

        additionalDataContainer.add(additionalData);
        noteContainer.add(note);
        wikiCzContainer.add(wikiCz);
        wikiEnContainer.add(wikiEn);
        add(name, mediaCount, additionalDataContainer, noteContainer, wikiCzContainer, wikiEnContainer);
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
