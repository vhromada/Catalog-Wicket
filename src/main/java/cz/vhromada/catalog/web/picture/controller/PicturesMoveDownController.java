package cz.vhromada.catalog.web.picture.controller;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for moving down picture.
 *
 * @author Vladimir Hromada
 */
@Component("picturesMoveDownController")
public class PicturesMoveDownController extends AbstractResultController<IModel<Picture>> {

    /**
     * Facade for pictures
     */
    private PictureFacade pictureFacade;

    /**
     * Creates a new instance of PicturesMoveDownController.
     *
     * @param pictureFacade facade for pictures
     * @throws IllegalArgumentException if facade for pictures is null
     */
    @Autowired
    public PicturesMoveDownController(final PictureFacade pictureFacade) {
        Assert.notNull(pictureFacade, "Facade for pictures mustn't be null.");

        this.pictureFacade = pictureFacade;
    }

    @Override
    public void handle(final IModel<Picture> data) {
        addResults(pictureFacade.moveDown(data.getObject()));

        if (processResult()) {
            getUi().fireEvent(new ControllerEvent(CatalogFlow.PICTURES_LIST, null));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PICTURES_MOVE_DOWN;
    }

}
