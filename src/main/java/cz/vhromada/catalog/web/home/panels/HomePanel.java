package cz.vhromada.catalog.web.home.panels;

import cz.vhromada.web.wicket.panels.BasePanel;

import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents home panel.
 *
 * @author Vladimir Hromada
 */
@Component(HomePanel.ID)
@Scope("prototype")
public class HomePanel extends BasePanel<Void> {

    /**
     * ID
     */
    public static final String ID = "homePanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of HomePanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public HomePanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

}
