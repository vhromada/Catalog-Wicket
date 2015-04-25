package cz.vhromada.catalog.web.shows.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel for shows.
 *
 * @author Vladimir Hromada
 */
@Component(ShowsMenuPanel.ID)
@Scope("prototype")
public class ShowsMenuPanel extends BasePanel<Void> {

    /**
     * ID
     */
    public static final String ID = "showsMenuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ShowsMenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ShowsMenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final AjaxFlowLink<Void> allShows = new AjaxFlowLink<>("allShows", CatalogFlow.SHOWS_LIST);

        final AjaxFlowLink<Void> newData = new AjaxFlowLink<>("newData", CatalogFlow.SHOWS_NEW_DATA);

        final AjaxFlowLink<Void> addShow = new AjaxFlowLink<>("addShow", CatalogFlow.SHOWS_ADD);

        final AjaxFlowLink<Void> updatePositions = new AjaxFlowLink<>("updatePositions", CatalogFlow.SHOWS_UPDATE_POSITION);

        add(allShows, newData, addShow, updatePositions);
    }

}
