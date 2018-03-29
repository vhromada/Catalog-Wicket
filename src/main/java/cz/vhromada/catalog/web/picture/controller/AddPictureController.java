package cz.vhromada.catalog.web.picture.controller;

import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.picture.mo.PictureMO;
import cz.vhromada.catalog.web.picture.panel.PictureFormPanel;
import cz.vhromada.catalog.web.picture.panel.PicturesMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.Model;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding picture.
 *
 * @author Vladimir Hromada
 */
@Component("addPictureController")
public class AddPictureController extends AbstractResultController<Void> {

    @Override
    public void handle(final Void data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.PICTURES_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData<PictureMO> panelData = new PanelData<>(PictureFormPanel.ID, Model.of(new PictureMO()));
        final PanelData<Void> menuData = new PanelData<>(PicturesMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Add picture", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PICTURES_ADD;
    }

}
