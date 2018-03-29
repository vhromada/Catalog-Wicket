package cz.vhromada.catalog.web.picture.controller;

import java.util.List;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.facade.PictureFacade;
import cz.vhromada.catalog.web.common.AbstractResultController;
import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.picture.panel.PicturesListPanel;
import cz.vhromada.catalog.web.picture.panel.PicturesMenuPanel;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for showing list of pictures.
 *
 * @author Vladimir Hromada
 */
@Component("picturesListController")
public class PicturesListController extends AbstractResultController<Void> {

    /**
     * Facade for pictures
     */
    private PictureFacade pictureFacade;

    /**
     * Creates a new instance of PicturesListController.
     *
     * @param pictureFacade facade for pictures
     * @throws IllegalArgumentException if facade for pictures is null
     */
    @Autowired
    public PicturesListController(final PictureFacade pictureFacade) {
        Assert.notNull(pictureFacade, "Facade for pictures mustn't be null.");

        this.pictureFacade = pictureFacade;
    }

    @Override
    public void handle(final Void data) {
        final Result<List<Picture>> picturesResult = pictureFacade.getAll();

        addResults(picturesResult);

        if (processResult()) {
            final PanelData<List<Picture>> panelData = new PanelData<>(PicturesListPanel.ID, Model.ofList(picturesResult.getData()));
            final PanelData<Void> menuData = new PanelData<>(PicturesMenuPanel.ID, null);

            getUi().fireEvent(new PanelEvent(panelData, "Pictures", menuData));
        }
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.PICTURES_LIST;
    }

}
