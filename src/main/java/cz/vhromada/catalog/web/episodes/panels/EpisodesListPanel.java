package cz.vhromada.catalog.web.episodes.panels;

import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.EpisodeTO;
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
 * A class represents panel for list of episodes.
 *
 * @author Vladimir Hromada
 */
@Component(EpisodesListPanel.ID)
@Scope("prototype")
public class EpisodesListPanel extends GenericPanel<List<EpisodeTO>> {

    /**
     * ID
     */
    public static final String ID = "episodesListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of EpisodesListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public EpisodesListPanel(final String id, final IModel<List<EpisodeTO>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer episodesTable = new WebMarkupContainer("episodesTable");
        episodesTable.setVisible(!getModelObject().isEmpty());

        final ListView<EpisodeTO> episodes = new ListView<EpisodeTO>("episodes", getModel()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<EpisodeTO> item) {
                final EpisodeTO episode = item.getModelObject();

                final Label number = new Label("number", episode.getNumber());

                final Label name = new Label("name", episode.getName());

                final Label length = new Label("length", new Time(episode.getLength()));

                final Label note = new Label("note", episode.getNote());

                final AjaxFlowLink<EpisodeTO> moveUp = new AjaxFlowLink<>("moveUp", item.getModel(), CatalogFlow.EPISODES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<EpisodeTO> moveDown = new AjaxFlowLink<>("moveDown", item.getModel(), CatalogFlow.EPISODES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<EpisodeTO> duplicate = new AjaxFlowLink<>("duplicate", item.getModel(), CatalogFlow.EPISODES_DUPLICATE);

                final AjaxFlowLink<EpisodeTO> edit = new AjaxFlowLink<>("edit", item.getModel(), CatalogFlow.EPISODES_UPDATE);

                final AjaxFlowLink<EpisodeTO> remove = new AjaxFlowLink<>("remove", item.getModel(), CatalogFlow.EPISODES_REMOVE);

                item.add(number, name, length, note, moveUp, moveDown, duplicate, edit, remove);
            }

        };

        final WebMarkupContainer noEpisodes = new WebMarkupContainer("noEpisodes");
        noEpisodes.setVisible(getModelObject().isEmpty());

        episodesTable.add(episodes);
        add(episodesTable, noEpisodes);
    }

}
