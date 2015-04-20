package cz.vhromada.catalog.web.music.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.music.mo.MusicMO;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for music.
 *
 * @author Vladimir Hromada
 */
@Component(MusicFormPanel.ID)
@Scope("prototype")
public class MusicFormPanel extends AbstractFormPanel<MusicMO> {

    /**
     * ID
     */
    public static final String ID = "musicPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Maximum count of media
     */
    private static final int MAX_MEDIA_COUNT = 100;

    /**
     * Creates a new instance of musicPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MusicFormPanel(final String id, final CompoundPropertyModel<MusicMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> name = new RequiredTextField<>("name");
        name.setLabel(Model.of("Name"));

        final TextField<String> wikiCz = new TextField<>("wikiCz");

        final TextField<String> wikiEn = new TextField<>("wikiEn");

        final NumberTextField<Integer> mediaCount = new NumberTextField<>("mediaCount");
        mediaCount.setMinimum(1)
                .setMaximum(MAX_MEDIA_COUNT)
                .setLabel(Model.of("Count of media"))
                .setRequired(true)
                .add(RangeValidator.range(1, MAX_MEDIA_COUNT));

        final TextField<String> note = new TextField<>("note");

        getForm().add(name, wikiEn, wikiCz, mediaCount, note);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.MUSIC_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<MusicMO> panelForm) {
        final MusicMO music = panelForm.getModelObject();
        if (music.getWikiCz() == null) {
            music.setWikiCz("");
        }
        if (music.getWikiEn() == null) {
            music.setWikiEn("");
        }
        if (music.getNote() == null) {
            music.setNote("");
        }
    }

}
