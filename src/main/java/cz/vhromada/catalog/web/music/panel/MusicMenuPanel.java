package cz.vhromada.catalog.web.music.panel;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for music.
 *
 * @author Vladimir Hromada
 */
@Component(MusicMenuPanel.ID)
@Scope("prototype")
public class MusicMenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "musicMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MusicMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MusicMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allMusic = new AjaxFlowLink<>("allMusic", CatalogFlow.MUSIC_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.MUSIC_NEW_DATA);

        final AjaxFlowLink<Void> addMusic = new AjaxFlowLink<>("addMusic", CatalogFlow.MUSIC_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", CatalogFlow.MUSIC_UPDATE_POSITION);

        add(allMusic, newData, addMusic, updatePositions);
    }

}
