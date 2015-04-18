package cz.vhromada.catalog.web.panels;

import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.flow.AjaxFlowLink;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.model.IModel;

/**
 * A class represents menu panel.
 *
 * @author Vladimir Hromada
 */
public class MenuPanel extends BasePanel<Void> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of BasePanel.
     *
     * @param id    ID
     * @param model model
     * @throws WicketRuntimeException if ID is null
     */
    public MenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }


    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AjaxFlowLink<Void>("home", Flow.HOME));
        add(new AjaxFlowLink<Void>("games", Flow.GAMES_LIST));
    }

}
