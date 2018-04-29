package cz.vhromada.catalog.web.song.panel;

import cz.vhromada.catalog.entity.Song;
import cz.vhromada.common.Time;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with song's detail.
 *
 * @author Vladimir Hromada
 */
@Component(SongDetailPanel.ID)
@Scope("prototype")
public class SongDetailPanel extends GenericPanel<Song> {

    /**
     * ID
     */
    public static final String ID = "songDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of SongDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public SongDetailPanel(final String id, final IModel<Song> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Song song = getModelObject();

        final Label name = new Label("name", song.getName());

        final Label length = new Label("length", new Time(song.getLength()));

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(song.getNote()));

        final Label note = new Label("note", song.getNote());

        noteContainer.add(note);
        add(name, length, noteContainer);
    }

}
