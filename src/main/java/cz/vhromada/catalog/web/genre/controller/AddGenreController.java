package cz.vhromada.catalog.web.genre.controller;

import cz.vhromada.catalog.web.event.PanelData;
import cz.vhromada.catalog.web.event.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genre.mo.GenreMO;
import cz.vhromada.catalog.web.genre.panel.GenreFormPanel;
import cz.vhromada.catalog.web.genre.panel.GenresMenuPanel;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for adding genre.
 *
 * @author Vladimir Hromada
 */
@Component("addGenreController")
public class AddGenreController extends Controller<Void> {

    @Override
    public void handle(final Void data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GENRES_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData<GenreMO> panelData = new PanelData<>(GenreFormPanel.ID, new CompoundPropertyModel<>(new GenreMO()));
        final PanelData<Void> menuData = new PanelData<>(GenresMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Add genre", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_ADD;
    }

}
