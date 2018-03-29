package cz.vhromada.catalog.web.picture.controller;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.picture.mo.PictureMO;
import cz.vhromada.converter.Converter;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for adding picture.
 *
 * @author Vladimir Hromada
 */
@Component("addPictureConfirmController")
public class AddPictureConfirmController extends AbstractResultController<IModel<PictureMO>> {

    /**
     * Facade for pictures
     */
    private PictureFacade pictureFacade;

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of AddPictureConfirmController.
     *
     * @param pictureFacade facade for pictures
     * @param converter     converter
     * @throws IllegalArgumentException if facade for pictures is null
     *                                  or converter is null
     */
    @Autowired
    public AddPictureConfirmController(final PictureFacade pictureFacade, final Converter converter) {
        Assert.notNull(pictureFacade, "Facade for pictures mustn't be null.");
        Assert.notNull(converter, "Converter mustn't be null.");

        this.pictureFacade = pictureFacade;
        this.converter = converter;
    }

    @Override
    public void handle(final IModel<PictureMO> data) {
        final boolean hasContent = data.getObject().getContent() != null;

        if (hasContent) {
            addResults(pictureFacade.add(converter.convert(data.getObject(), Picture.class)));
        }

        if (!hasContent || processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.PICTURES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PICTURES_ADD_CONFIRM;
    }

}
