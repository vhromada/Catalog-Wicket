package cz.vhromada.catalog.web.movie.panel;

import cz.vhromada.catalog.entity.Movie;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movie.mo.MoviesMO;
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
 * A class represents panel for list of movies.
 *
 * @author Vladimir Hromada
 */
@Component(MoviesListPanel.ID)
@Scope("prototype")
public class MoviesListPanel extends GenericPanel<MoviesMO> {

    /**
     * ID
     */
    public static final String ID = "moviesListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MoviesListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MoviesListPanel(final String id, final IModel<MoviesMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer moviesTable = new WebMarkupContainer("moviesTable");
        moviesTable.setVisible(!getModelObject().getMovies().isEmpty());

        final ListView<Movie> movies = new ListView<>("movies", getModelObject().getMovies()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Movie> item) {
                final Movie movie = item.getModelObject();

                final AjaxFlowLink<Movie> detail = new AjaxFlowLink<>("detail", item.getModel(), CatalogFlow.MOVIES_DETAIL);

                final Label detailText = new Label("detailText", movie.getCzechName());

                final AjaxFlowLink<Movie> moveUp = new AjaxFlowLink<>("moveUp", Model.of(movie), CatalogFlow.MOVIES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Movie> moveDown = new AjaxFlowLink<>("moveDown", Model.of(movie), CatalogFlow.MOVIES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Movie> duplicate = new AjaxFlowLink<>("duplicate", Model.of(movie), CatalogFlow.MOVIES_DUPLICATE);

                final AjaxFlowLink<Movie> edit = new AjaxFlowLink<>("edit", Model.of(movie), CatalogFlow.MOVIES_UPDATE);

                final AjaxFlowLink<Movie> remove = new AjaxFlowLink<>("remove", Model.of(movie), CatalogFlow.MOVIES_REMOVE);

                detail.add(detailText);
                item.add(detail, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noMovies = new WebMarkupContainer("noMovies");
        noMovies.setVisible(getModelObject().getMovies().isEmpty());

        final Label count = new Label("count", getModelObject().getMovies().size());

        final Label totalLength = new Label("totalLength", getModelObject().getTotalLength());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        moviesTable.add(movies);
        add(moviesTable, noMovies, count, totalLength, mediaCount);
    }

}
