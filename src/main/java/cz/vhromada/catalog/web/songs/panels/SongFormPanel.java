package cz.vhromada.catalog.web.songs.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.songs.mo.SongMO;
import cz.vhromada.web.wicket.controllers.Flow;

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
 * A class represents panel with form for song.
 *
 * @author Vladimir Hromada
 */
@Component(SongFormPanel.ID)
@Scope("prototype")
public class SongFormPanel extends AbstractFormPanel<SongMO> {

    /**
     * ID
     */
    public static final String ID = "songFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Maximum hours
     */
    private static final int MAX_HOURS = 23;

    /**
     * Maximum minutes
     */
    private static final int MAX_MINUTES = 59;

    /**
     * Maximum seconds
     */
    private static final int MAX_SECONDS = 59;

    /**
     * Creates a new instance of SongPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SongFormPanel(final String id, final CompoundPropertyModel<SongMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> name = new RequiredTextField<>("name");
        name.setLabel(Model.of("Name"));

        final NumberTextField<Integer> hours = new NumberTextField<>("length.hours");
        hours.setMinimum(0)
                .setMaximum(MAX_HOURS)
                .setLabel(Model.of("Hours"))
                .setRequired(true)
                .add(RangeValidator.range(0, MAX_HOURS));

        final NumberTextField<Integer> minutes = new NumberTextField<>("length.minutes");
        minutes.setMinimum(0)
                .setMaximum(MAX_MINUTES)
                .setLabel(Model.of("Minutes"))
                .setRequired(true)
                .add(RangeValidator.range(0, MAX_MINUTES));

        final NumberTextField<Integer> seconds = new NumberTextField<>("length.seconds");
        seconds.setMinimum(0)
                .setMaximum(MAX_SECONDS)
                .setLabel(Model.of("Seconds"))
                .setRequired(true)
                .add(RangeValidator.range(0, MAX_SECONDS));

        final TextField<String> note = new TextField<>("note");

        getForm().add(name, hours, minutes, seconds, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.SONGS_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<SongMO> panelForm) {
        final SongMO song = panelForm.getModelObject();
        if (song.getNote() == null) {
            song.setNote("");
        }
    }

}
