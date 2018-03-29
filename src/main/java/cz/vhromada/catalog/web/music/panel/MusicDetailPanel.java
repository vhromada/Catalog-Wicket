package cz.vhromada.catalog.web.music.panel;

import cz.vhromada.catalog.entity.Music;
import cz.vhromada.catalog.web.component.WikipediaLink;
import cz.vhromada.catalog.web.music.mo.MusicDataMO;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with music's detail.
 *
 * @author Vladimir Hromada
 */
@Component(MusicDetailPanel.ID)
@Scope("prototype")
public class MusicDetailPanel extends GenericPanel<MusicDataMO> {

    /**
     * ID
     */
    public static final String ID = "musicDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MusicDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MusicDetailPanel(final String id, final IModel<MusicDataMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final MusicDataMO musicData = getModelObject();
        final Music music = musicData.getMusic();

        final Label name = new Label("name", music.getName());

        final Label mediaCount = new Label("mediaCount", music.getMediaCount());

        final WebMarkupContainer songsContainer = new WebMarkupContainer("songsContainer");
        songsContainer.setVisible(musicData.getSongsCount() > 0);

        final Label songsCount = new Label("songsCount", musicData.getSongsCount());

        final Label totalLength = new Label("totalLength", musicData.getTotalLength());

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(music.getNote()));

        final Label note = new Label("note", music.getNote());

        final WebMarkupContainer wikiCzContainer = new WebMarkupContainer("wikiCzContainer");
        wikiCzContainer.setVisible(!StringUtils.isEmpty(music.getWikiCz()));

        final WikipediaLink wikiCz = new WikipediaLink("wikiCz", music.getWikiCz(), WikipediaLink.Country.CZ);

        final WebMarkupContainer wikiEnContainer = new WebMarkupContainer("wikiEnContainer");
        wikiEnContainer.setVisible(!StringUtils.isEmpty(music.getWikiEn()));

        final WikipediaLink wikiEn = new WikipediaLink("wikiEn", music.getWikiEn(), WikipediaLink.Country.EN);

        songsContainer.add(songsCount, totalLength);
        noteContainer.add(note);
        wikiCzContainer.add(wikiCz);
        wikiEnContainer.add(wikiEn);
        add(name, mediaCount, songsContainer, noteContainer, wikiCzContainer, wikiEnContainer);
    }


}
