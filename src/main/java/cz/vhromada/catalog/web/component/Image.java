package cz.vhromada.catalog.web.component;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.Model;

/**
 * A class represents image.
 *
 * @author Vladimir Hromada
 */
public class Image extends GenericPanel<Integer> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Image
     */
    private org.apache.wicket.markup.html.image.Image image;

    /**
     * Creates a new instance of Image.
     *
     * @param id    ID
     * @param value value
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public Image(final String id, final Integer value) {
        super(id, Model.of(value));
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        image = new org.apache.wicket.markup.html.image.Image("image", new ImageResource(getModelObject()));
        add(image);
    }

    /**
     * Update image.
     *
     * @param value value
     */
    public void update(final Integer value) {
        setModel(Model.of(value));
        image.setImageResource(new ImageResource(getModelObject()));
    }

}
