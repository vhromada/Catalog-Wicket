package cz.vhromada.catalog.web.song.panel;

import java.util.List;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
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
public class SongsListPanel extends GenericPanel<List<Song>> {

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
    public SongsListPanel(final String id, final IModel<List<Song>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer songsTable = new WebMarkupContainer("songsTable");
        songsTable.setVisible(!getModelObject().isEmpty());

        final ListView<Song> songs = new ListView<>("songs", getModel()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Song> item) {
                final Song song = item.getModelObject();

                final AjaxFlowLink<Song> detail = new AjaxFlowLink<>("detail", item.getModel(), CatalogFlow.SONGS_DETAIL);

                final Label detailText = new Label("detailText", song.getName());

                final AjaxFlowLink<Song> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.SONGS_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Song> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.SONGS_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Song> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.SONGS_DUPLICATE);

                final AjaxFlowLink<Song> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.SONGS_UPDATE);

                final AjaxFlowLink<Song> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.SONGS_REMOVE);

                detail.add(detailText);
                item.add(detail, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noSongs = new WebMarkupContainer("noSongs");
        noSongs.setVisible(getModelObject().isEmpty());

        songsTable.add(songs);
        add(songsTable, noSongs);
    }

}
