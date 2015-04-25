package cz.vhromada.catalog.web.episodes.panels;

import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
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
 * A class represents panel with form for episode.
 *
 * @author Vladimir Hromada
 */
@Component(EpisodeFormPanel.ID)
@Scope("prototype")
public class EpisodeFormPanel extends AbstractFormPanel<EpisodeMO> {

    /**
     * ID
     */
    public static final String ID = "episodeFormPanel";

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
     * Maximum number
     */
    private static final int MAX_NUMBER = 500;

    /**
     * Creates a new instance of EpisodeFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public EpisodeFormPanel(final String id, final CompoundPropertyModel<EpisodeMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final NumberTextField<Integer> number = new NumberTextField<>("number");
        number.setMinimum(1)
                .setMaximum(MAX_NUMBER)
                .setLabel(Model.of("Number"))
                .setRequired(true)
                .add(RangeValidator.range(1, MAX_NUMBER));

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

        getForm().add(number, name, hours, minutes, seconds, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.EPISODES_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<EpisodeMO> panelForm) {
        final EpisodeMO episode = panelForm.getModelObject();
        if (episode.getNote() == null) {
            episode.setNote("");
        }
    }

}
