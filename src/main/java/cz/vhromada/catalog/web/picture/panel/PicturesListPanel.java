package cz.vhromada.catalog.web.picture.panel;

import java.util.List;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.web.component.Image;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel for list of pictures.
 *
 * @author Vladimir Hromada
 */
@Component(PicturesListPanel.ID)
@Scope("prototype")
public class PicturesListPanel extends GenericPanel<List<Picture>> {

    /**
     * ID
     */
    public static final String ID = "picturesListPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of PicturesListPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public PicturesListPanel(final String id, final IModel<List<Picture>> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final WebMarkupContainer picturesTable = new WebMarkupContainer("picturesTable");
        picturesTable.setVisible(!getModelObject().isEmpty());

        final ListView<Picture> pictures = new ListView<>("pictures", getModelObject()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Picture> item) {
                final Picture picture = item.getModelObject();

                final Image image = new Image("image", picture.getId());

                final AjaxFlowLink<Picture> moveUp = new AjaxFlowLink<>("moveUp", Model.of(picture), CatalogFlow.PICTURES_MOVE_UP);
                moveUp.setVisible(item.getIndex() > 0);

                final AjaxFlowLink<Picture> moveDown = new AjaxFlowLink<>("moveDown", Model.of(picture), CatalogFlow.PICTURES_MOVE_DOWN);
                moveDown.setVisible(item.getIndex() < getModelObject().size() - 1);

                final AjaxFlowLink<Picture> remove = new AjaxFlowLink<>("remove", Model.of(picture), CatalogFlow.PICTURES_REMOVE);

                item.add(image, moveUp, moveDown, remove);
            }

        };

        final WebMarkupContainer noPictures = new WebMarkupContainer("noPictures");
        noPictures.setVisible(getModelObject().isEmpty());

        picturesTable.add(pictures);
        add(picturesTable, noPictures);
    }

}
