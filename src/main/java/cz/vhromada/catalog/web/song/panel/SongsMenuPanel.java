package cz.vhromada.catalog.web.song.panel;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.panel.MusicMenuPanel;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for songs.
 *
 * @author Vladimir Hromada
 */
@Component(SongsMenuPanel.ID)
@Scope("prototype")
public class SongsMenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "songsMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SongsMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SongsMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Panel musicMenu = new MusicMenuPanel("musicMenu", null);

        final AjaxFlowLink<Void> allSongs = new AjaxFlowLink<>("allSongs", CatalogFlow.SONGS_LIST);

        final AjaxFlowLink<Void> addSong = new AjaxFlowLink<>("addSong", CatalogFlow.SONGS_ADD);

        add(musicMenu, allSongs, addSong);
    }

}
