package cz.vhromada.catalog.web.picture.controller;

import cz.vhromada.catalog.web.event.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.picture.mo.PictureMO;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.IModel;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for cancel changing picture.
 *
 * @author Vladimir Hromada
 */
@Component("pictureCancelController")
public class PictureCancelController extends Controller<IModel<PictureMO>> {

    @Override
    public void handle(final IModel<PictureMO> data) {
        getUi().fireEvent(new ControllerEvent(CatalogFlow.PICTURES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PICTURES_CANCEL;
    }

}
