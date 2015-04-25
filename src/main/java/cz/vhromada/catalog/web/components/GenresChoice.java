package cz.vhromada.catalog.web.components;

import java.util.List;

import cz.vhromada.catalog.web.genres.mo.GenreMO;

import org.apache.wicket.markup.html.form.ChoiceRenderer;
import org.apache.wicket.markup.html.form.ListMultipleChoice;
import org.apache.wicket.model.Model;

/**
 * A class represents choice for genres.
 *
 * @author Vladimir Hromada
 */
public class GenresChoice extends ListMultipleChoice<GenreMO> {

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
        super(id, choices, new ChoiceRenderer<GenreMO>("name", "id"));

        setRequired(true);
        setLabel(Model.of("Genres"));
    }

}
