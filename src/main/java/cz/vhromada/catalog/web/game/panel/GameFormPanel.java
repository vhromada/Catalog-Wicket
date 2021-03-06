package cz.vhromada.catalog.web.game.panel;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.game.mo.GameMO;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.markup.html.form.CheckBox;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for game.
 *
 * @author Vladimir Hromada
 */
@Component(GameFormPanel.ID)
@Scope("prototype")
public class GameFormPanel extends AbstractFormPanel<GameMO> {

    /**
     * ID
     */
    public static final String ID = "gameFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Maximum count of media
     */
    private static final int MAX_MEDIA_COUNT = 100;

    /**
     * Creates a new instance of GameFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public GameFormPanel(final String id, final CompoundPropertyModel<GameMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> name = new RequiredTextField<>("name");
        name.setLabel(Model.of("Name"))
            .add(getValidationBehavior());

        final TextField<String> wikiCz = new TextField<>("wikiCz");

        final TextField<String> wikiEn = new TextField<>("wikiEn");

        final NumberTextField<Integer> mediaCount = new NumberTextField<>("mediaCount");
        mediaCount.setMinimum(1)
            .setMaximum(MAX_MEDIA_COUNT)
            .setLabel(Model.of("Count of media"))
            .setRequired(true)
            .add(RangeValidator.range(1, MAX_MEDIA_COUNT))
            .add(getValidationBehavior());

        final CheckBox crack = new CheckBox("crack");
        crack.setLabel(Model.of("Crack"));

        final CheckBox serialKey = new CheckBox("serialKey");
        serialKey.setLabel(Model.of("Serial key"));

        final CheckBox patch = new CheckBox("patch");
        patch.setLabel(Model.of("Patch"));

        final CheckBox trainer = new CheckBox("trainer");
        trainer.setLabel(Model.of("Trainer"));

        final CheckBox trainerData = new CheckBox("trainerData");
        trainerData.setLabel(Model.of("Data for trainer"));

        final CheckBox editor = new CheckBox("editor");
        editor.setLabel(Model.of("Editor"));

        final CheckBox saves = new CheckBox("saves");
        saves.setLabel(Model.of("Saves"));

        final TextField<String> otherData = new TextField<>("otherData");

        final TextField<String> note = new TextField<>("note");

        getForm().add(name, wikiEn, wikiCz, mediaCount, crack, serialKey, patch, trainer, trainerData, editor, saves, otherData, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.GAMES_CANCEL;
    }

    @Override
    protected void onFormSubmit() {
        final GameMO game = getForm().getModelObject();
        if (game.getWikiCz() == null) {
            game.setWikiCz("");
        }
        if (game.getWikiEn() == null) {
            game.setWikiEn("");
        }
        if (game.getNote() == null) {
            game.setNote("");
        }
        if (game.getOtherData() == null) {
            game.setOtherData("");
        }
    }

}
