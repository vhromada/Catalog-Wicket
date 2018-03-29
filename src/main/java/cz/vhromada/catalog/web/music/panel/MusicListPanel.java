package cz.vhromada.catalog.web.music.panel;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicDataMO;
import cz.vhromada.catalog.web.music.mo.MusicInfoMO;
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
 * A class represents panel for list of music.
 *
 * @author Vladimir Hromada
 */
@Component(MusicListPanel.ID)
@Scope("prototype")
public class MusicListPanel extends GenericPanel<MusicInfoMO> {

    /**
     * ID
     */
    public static final String ID = "musicListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MusicListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MusicListPanel(final String id, final IModel<MusicInfoMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer musicTable = new WebMarkupContainer("musicTable");
        musicTable.setVisible(!getModelObject().getMusicData().isEmpty());

        final ListView<MusicDataMO> music = new ListView<>("music", getModelObject().getMusicData()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<MusicDataMO> item) {
                final Music musicItem = item.getModelObject().getMusic();

                final AjaxFlowLink<MusicDataMO> detail = new AjaxFlowLink<>("detail", item.getModel(), CatalogFlow.MUSIC_DETAIL);

                final Label detailText = new Label("detailText", musicItem.getName());

                final AjaxFlowLink<Music> songs = new AjaxFlowLink<>("songs", Model.of(musicItem), CatalogFlow.MUSIC_SONGS);

                final AjaxFlowLink<Music> moveUp = new AjaxFlowLink<>("moveUp", Model.of(musicItem), CatalogFlow.MUSIC_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Music> moveDown = new AjaxFlowLink<>("moveDown", Model.of(musicItem), CatalogFlow.MUSIC_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Music> duplicate = new AjaxFlowLink<>("duplicate", Model.of(musicItem), CatalogFlow.MUSIC_DUPLICATE);

                final AjaxFlowLink<Music> edit = new AjaxFlowLink<>("edit", Model.of(musicItem), CatalogFlow.MUSIC_UPDATE);

                final AjaxFlowLink<Music> remove = new AjaxFlowLink<>("remove", Model.of(musicItem), CatalogFlow.MUSIC_REMOVE);

                detail.add(detailText);
                item.add(detail, songs, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noMusic = new WebMarkupContainer("noMusic");
        noMusic.setVisible(getModelObject().getMusicData().isEmpty());

        final Label count = new Label("count", getModelObject().getMusicData().size());

        final Label mediaCount = new Label("mediaCount", getModelObject().getMediaCount());

        final Label songsCount = new Label("songsCount", getModelObject().getSongsCount());

        final Label totalLength = new Label("totalLength", getModelObject().getTotalLength());

        musicTable.add(music);
        add(musicTable, noMusic, count, mediaCount, songsCount, totalLength);
    }

}
