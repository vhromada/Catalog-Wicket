package cz.vhromada.catalog.web.episode.panel;

import cz.vhromada.catalog.entity.Episode;
import cz.vhromada.common.Time;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with episode's detail.
 *
 * @author Vladimir Hromada
 */
@Component(EpisodeDetailPanel.ID)
@Scope("prototype")
public class EpisodeDetailPanel extends GenericPanel<Episode> {

    /**
     * ID
     */
    public static final String ID = "episodeDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of EpisodeDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public EpisodeDetailPanel(final String id, final IModel<Episode> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Episode episode = getModelObject();

        final Label number = new Label("number", episode.getNumber());

        final Label name = new Label("name", episode.getName());

        final Label length = new Label("length", new Time(episode.getLength()));

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(episode.getNote()));

        final Label note = new Label("note", episode.getNote());

        noteContainer.add(note);
        add(number, name, length, noteContainer);
    }

}
