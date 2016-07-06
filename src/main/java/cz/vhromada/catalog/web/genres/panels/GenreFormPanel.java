package cz.vhromada.catalog.web.genres.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for genre.
 *
 * @author Vladimir Hromada
 */
@Component(GenreFormPanel.ID)
@Scope("prototype")
public class GenreFormPanel extends AbstractFormPanel<GenreMO> {

    /**
     * ID
     */
    public static final String ID = "genreFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of GenreFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GenreFormPanel(final String id, final CompoundPropertyModel<GenreMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> name = new RequiredTextField<>("name");
        name.setLabel(Model.of("Name"))
                .add(getValidationBehavior());

        getForm().add(name);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.GENRES_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<GenreMO> panelForm) {
    }

}
