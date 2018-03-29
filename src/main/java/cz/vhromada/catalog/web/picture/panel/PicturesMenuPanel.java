package cz.vhromada.catalog.web.picture.panel;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for pictures.
 *
 * @author Vladimir Hromada
 */
@Component(PicturesMenuPanel.ID)
@Scope("prototype")
public class PicturesMenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "picturesMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of PicturesMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public PicturesMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allPictures = new AjaxFlowLink<>("allPictures", CatalogFlow.PICTURES_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.PICTURES_NEW_DATA);

        final AjaxFlowLink<Void> addPicture = new AjaxFlowLink<>("addPicture", CatalogFlow.PICTURES_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", CatalogFlow.PICTURES_UPDATE_POSITION);

        add(allPictures, newData, addPicture, updatePositions);
    }

}
