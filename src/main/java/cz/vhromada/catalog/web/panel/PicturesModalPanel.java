package cz.vhromada.catalog.web.panel;

import java.util.List;

import cz.vhromada.catalog.web.component.Image;

import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.Model;

/**
 * An abstract class represents modal panel for choosing picture.
 *
 * @author Vladimir Hromada
 */
public abstract class PicturesModalPanel extends GenericPanel<List<Integer>> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of PicturesModalPanel.
     *
     * @param id       ID
     * @param pictures pictures
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public PicturesModalPanel(final String id, final List<Integer> pictures) {
        super(id, Model.ofList(pictures));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RadioGroup<Integer> picture = new RadioGroup<>("picture");

        final ListView<Integer> pictures = new ListView<>("pictures", getModel()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Integer> item) {
                final Radio<Integer> pictureItem = new Radio<>("pictureItem", item.getModel());
                pictureItem.setMarkupId("picture" + (item.getIndex() + 1));

                final Image pictureItemContent = new Image("pictureItemContent", item.getModelObject());

                item.add(pictureItem, pictureItemContent);
            }

        };

        picture.add(pictures);
        add(picture);
    }

    /**
     * Returns validation behavior.
     *
     * @return validation behavior
     */
    protected abstract AjaxFormChoiceComponentUpdatingBehavior getValidationBehavior();

}
