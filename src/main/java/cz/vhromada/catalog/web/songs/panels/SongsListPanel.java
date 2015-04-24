package cz.vhromada.catalog.web.songs.panels;

import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.SongTO;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of songs.
 *
 * @author Vladimir Hromada
 */
@Component(SongsListPanel.ID)
@Scope("prototype")
public class SongsListPanel extends BasePanel<List<SongTO>> {

    /**
     * ID
     */
    public static final String ID = "songsListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SongsListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SongsListPanel(final String id, final IModel<List<SongTO>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer songsTable = new WebMarkupContainer("songsTable");
        songsTable.setVisible(!getModelObject().isEmpty());

        final ListView<SongTO> songs = new ListView<SongTO>("songs", getModel()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<SongTO> item) {
                final SongTO song = item.getModelObject();

                final Label name = new Label("name", song.getName());

                final Label length = new Label("length", new Time(song.getLength()));

                final Label note = new Label("note", song.getNote());

                final AjaxFlowLink<SongTO> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.SONGS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<SongTO> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.SONGS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<SongTO> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.SONGS_DUPLICATE);

                final AjaxFlowLink<SongTO> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.SONGS_UPDATE);

                final AjaxFlowLink<SongTO> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.SONGS_REMOVE);

                item.add(name, length, note, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noSongs = new WebMarkupContainer("noSongs");
        noSongs.setVisible(getModelObject().isEmpty());

        songsTable.add(songs);
        add(songsTable, noSongs);
    }

}
