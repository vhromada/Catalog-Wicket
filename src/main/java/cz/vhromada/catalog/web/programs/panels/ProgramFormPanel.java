package cz.vhromada.catalog.web.programs.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for program.
 *
 * @author Vladimir Hromada
 */
@Component(ProgramFormPanel.ID)
@Scope("prototype")
public class ProgramFormPanel extends AbstractFormPanel<ProgramMO> {

    /**
     * ID
     */
    public static final String ID = "programPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ProgramPanel.
     *
     * @param id    ID
     * @param model model
     * @throws WicketRuntimeException if ID is null
     */
    public ProgramFormPanel(final String id, final CompoundPropertyModel<ProgramMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> name = new RequiredTextField<>("name");
        name.setLabel(Model.of("Name"));

        final TextField<String> wikiCz = new TextField<>("wikiCz");

        final TextField<String> wikiEn = new TextField<>("wikiEn");

        final NumberTextField<Integer> mediaCount = new NumberTextField<>("mediaCount");
        mediaCount.setMinimum(1)
                .setMaximum(100)
                .setLabel(Model.of("Count of media"))
                .setRequired(true)
                .add(RangeValidator.range(1, 100));

        final CheckBox crack = new CheckBox("crack");
        crack.setLabel(Model.of("Crack"));

        final CheckBox serialKey = new CheckBox("serialKey");
        serialKey.setLabel(Model.of("Serial key"));

        final TextField<String> otherData = new TextField<>("otherData");

        final TextField<String> note = new TextField<>("note");

        getForm().add(name, wikiEn, wikiCz, mediaCount, crack, serialKey, otherData, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.PROGRAMS_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<ProgramMO> form) {
        final ProgramMO program = form.getModelObject();
        if (program.getWikiCz() == null) {
            program.setWikiCz("");
        }
        if (program.getWikiEn() == null) {
            program.setWikiEn("");
        }
        if (program.getNote() == null) {
            program.setNote("");
        }
        if (program.getOtherData() == null) {
            program.setOtherData("");
        }
    }

}
