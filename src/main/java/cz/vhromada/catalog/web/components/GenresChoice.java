package cz.vhromada.catalog.web.components;

import java.util.List;

import cz.vhromada.catalog.web.genres.mo.GenreMO;

import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.Model;

/**
 * An abstract class represents choice for genres.
 *
 * @author Vladimir Hromada
 */
public abstract class GenresChoice extends ListMultipleChoice<GenreMO> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of GenresChoice.
     *
     * @param id      ID
     * @param choices choices
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GenresChoice(final String id, final List<? extends GenreMO> choices) {
        super(id, choices, new ChoiceRenderer<>("name", "id"));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        setRequired(true);
        setLabel(Model.of("Genres"));
        add(getValidationBehavior());
    }

    /**
     * Returns validation behavior.
     *
     * @return validation behavior
     */
    protected abstract AjaxFormComponentUpdatingBehavior getValidationBehavior();

}
