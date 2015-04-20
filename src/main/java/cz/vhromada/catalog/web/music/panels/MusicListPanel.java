package cz.vhromada.catalog.web.music.panels;

import cz.vhromada.catalog.facade.to.MusicTO;
import cz.vhromada.catalog.web.components.WikipediaLink;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicDataMO;
import cz.vhromada.catalog.web.music.mo.MusicInfoMO;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
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
public class MusicListPanel extends BasePanel<MusicInfoMO> {

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

        final ListView<MusicDataMO> music = new ListView<MusicDataMO>("music", Model.ofList(getModelObject().getMusicData())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<MusicDataMO> item) {
                final MusicDataMO musicData = item.getModelObject();
                final MusicTO musicTO = musicData.getMusic();

                final Label name = new Label("name", musicTO.getName());

                final Label mediaCount = new Label("mediaCount", musicTO.getMediaCount());

                final Label songsCount = new Label("songsCount", musicData.getSongsCount());

                final Label totalLength = new Label("totalLength", musicData.getTotalLength());

                final Label note = new Label("note", musicTO.getNote());

                final WikipediaLink wikiCz = new WikipediaLink("wikiCz", musicTO.getWikiCz(), WikipediaLink.Country.CZ);

                final WikipediaLink wikiEn = new WikipediaLink("wikiEn", musicTO.getWikiEn(), WikipediaLink.Country.EN);

                //TODO vhromada 20.04.2015: songs
                final AjaxLink songs = new AjaxLink("songs") {
                    private static final long serialVersionUID = 7558089009117211382L;

                    @Override
                    public void onClick(final AjaxRequestTarget target) {
                        target.appendJavaScript("alert('Not implemented');");
                    }
                };

                final AjaxFlowLink<MusicTO> moveUp = new AjaxFlowLink<>("moveUp", Model.of(musicTO), CatalogFlow.MUSIC_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<MusicTO> moveDown = new AjaxFlowLink<>("moveDown", Model.of(musicTO), CatalogFlow.MUSIC_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<MusicTO> duplicate = new AjaxFlowLink<>("duplicate", Model.of(musicTO), CatalogFlow.MUSIC_DUPLICATE);

                final AjaxFlowLink<MusicTO> edit = new AjaxFlowLink<>("edit", Model.of(musicTO), CatalogFlow.MUSIC_UPDATE);

                final AjaxFlowLink<MusicTO> remove = new AjaxFlowLink<>("remove", Model.of(musicTO), CatalogFlow.MUSIC_REMOVE);

                item.add(name, mediaCount, songsCount, totalLength, note, wikiCz, wikiEn, songs, moveUp, moveDown, duplicate, edit, remove);
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
