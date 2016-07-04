package cz.vhromada.catalog.web.movies.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for movies.
 *
 * @author Vladimir Hromada
 */
@Component(MoviesMenuPanel.ID)
@Scope("prototype")
public class MoviesMenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "moviesMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MoviesMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MoviesMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allMovies = new AjaxFlowLink<>("allMovies", CatalogFlow.MOVIES_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.MOVIES_NEW_DATA);

        final AjaxFlowLink<Void> addMovie = new AjaxFlowLink<>("addMovie", CatalogFlow.MOVIES_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", CatalogFlow.MOVIES_UPDATE_POSITION);

        add(allMovies, newData, addMovie, updatePositions);
    }

}
