package cz.vhromada.catalog.web.genres.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for genres.
 *
 * @author Vladimir Hromada
 */
@Component(GenresMenuPanel.ID)
@Scope("prototype")
public class GenresMenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "genresMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of GenresMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GenresMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allGenres = new AjaxFlowLink<>("allGenres", CatalogFlow.GENRES_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.GENRES_NEW_DATA);

        final AjaxFlowLink<Void> addGenre = new AjaxFlowLink<>("addGenre", CatalogFlow.GENRES_ADD);

        add(allGenres, newData, addGenre);
    }

}
