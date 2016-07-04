package cz.vhromada.catalog.web.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;

import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.IModel;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents menu panel.
 *
 * @author Vladimir Hromada
 */
@Component(MenuPanel.ID)
@Scope("prototype")
public class MenuPanel extends GenericPanel<Void> {

    /**
     * ID
     */
    public static final String ID = "menuPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MenuPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MenuPanel(final String id, final IModel<Void> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        add(new AjaxFlowLink<Void>("home", CatalogFlow.HOME));
        add(new AjaxFlowLink<Void>("movies", CatalogFlow.MOVIES_LIST));
        add(new AjaxFlowLink<Void>("shows", CatalogFlow.SHOWS_LIST));
        add(new AjaxFlowLink<Void>("games", CatalogFlow.GAMES_LIST));
        add(new AjaxFlowLink<Void>("music", CatalogFlow.MUSIC_LIST));
        add(new AjaxFlowLink<Void>("programs", CatalogFlow.PROGRAMS_LIST));
        add(new AjaxFlowLink<Void>("books", CatalogFlow.BOOK_CATEGORIES_LIST));
        add(new AjaxFlowLink<Void>("genres", CatalogFlow.GENRES_LIST));
    }

}
