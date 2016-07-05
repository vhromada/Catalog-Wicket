package cz.vhromada.catalog.web.genres.panels;

import java.util.List;

import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
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
 * A class represents panel for list of genres.
 *
 * @author Vladimir Hromada
 */
@Component(GenresListPanel.ID)
@Scope("prototype")
public class GenresListPanel extends GenericPanel<List<GenreTO>> {

    /**
     * ID
     */
    public static final String ID = "genresListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of GenresListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GenresListPanel(final String id, final IModel<List<GenreTO>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer genresTable = new WebMarkupContainer("genresTable");
        genresTable.setVisible(!getModelObject().isEmpty());

        final ListView<GenreTO> genres = new ListView<GenreTO>("genres", Model.ofList(getModelObject())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<GenreTO> item) {
                final GenreTO genre = item.getModelObject();

                final Label name = new Label("name", genre.getName());

                final AjaxFlowLink<GenreTO> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.GENRES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<GenreTO> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.GENRES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<GenreTO> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.GENRES_DUPLICATE);

                final AjaxFlowLink<GenreTO> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.GENRES_UPDATE);

                final AjaxFlowLink<GenreTO> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.GENRES_REMOVE);

                item.add(name, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noGenres = new WebMarkupContainer("noGenres");
        noGenres.setVisible(getModelObject().isEmpty());

        final Label count = new Label("count", getModelObject().size());

        genresTable.add(genres);
        add(genresTable, noGenres, count);
    }

}
