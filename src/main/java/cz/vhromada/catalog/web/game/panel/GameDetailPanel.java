package cz.vhromada.catalog.web.game.panel;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.common.FormatUtils;
import cz.vhromada.catalog.web.component.WikipediaLink;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * A class represents panel with game's detail.
 *
 * @author Vladimir Hromada
 */
@Component(GameDetailPanel.ID)
@Scope("prototype")
public class GameDetailPanel extends GenericPanel<Game> {

    /**
     * ID
     */
    public static final String ID = "gameDetailPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of GameDetailPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GameDetailPanel(final String id, final IModel<Game> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final Game game = getModelObject();
        final String additionalDataValue = getAdditionalData(game);

        final Label name = new Label("name", game.getName());

        final Label mediaCount = new Label("mediaCount", game.getMediaCount());

        final WebMarkupContainer additionalDataContainer = new WebMarkupContainer("additionalDataContainer");
        additionalDataContainer.setVisible(!StringUtils.isEmpty(additionalDataValue));

        final Label additionalData = new Label("additionalData", additionalDataValue);

        final WebMarkupContainer noteContainer = new WebMarkupContainer("noteContainer");
        noteContainer.setVisible(!StringUtils.isEmpty(game.getNote()));

        final Label note = new Label("note", game.getNote());

        final WebMarkupContainer wikiCzContainer = new WebMarkupContainer("wikiCzContainer");
        wikiCzContainer.setVisible(!StringUtils.isEmpty(game.getWikiCz()));

        final WikipediaLink wikiCz = new WikipediaLink("wikiCz", game.getWikiCz(), WikipediaLink.Country.CZ);

        final WebMarkupContainer wikiEnContainer = new WebMarkupContainer("wikiEnContainer");
        wikiEnContainer.setVisible(!StringUtils.isEmpty(game.getWikiEn()));

        final WikipediaLink wikiEn = new WikipediaLink("wikiEn", game.getWikiEn(), WikipediaLink.Country.EN);

        additionalDataContainer.add(additionalData);
        noteContainer.add(note);
        wikiCzContainer.add(wikiCz);
        wikiEnContainer.add(wikiEn);
        add(name, mediaCount, additionalDataContainer, noteContainer, wikiCzContainer, wikiEnContainer);
    }

    /**
     * Returns additional data.
     *
     * @param game game
     * @return additional data
     */
    private static String getAdditionalData(final Game game) {
        final StringBuilder result = new StringBuilder();
        if (game.getCrack()) {
            result.append("Crack");
        }
        FormatUtils.addToResult(result, game.getSerialKey(), "serial key");
        FormatUtils.addToResult(result, game.getPatch(), "patch");
        FormatUtils.addToResult(result, game.getTrainer(), "trainer");
        FormatUtils.addToResult(result, game.getTrainerData(), "data for trainer");
        FormatUtils.addToResult(result, game.getEditor(), "editor");
        FormatUtils.addToResult(result, game.getSaves(), "saves");
        if (game.getOtherData() != null && !game.getOtherData().isEmpty()) {
            if (result.length() != 0) {
                result.append(", ");
            }
            result.append(game.getOtherData());
        }

        return result.toString();
    }

}
