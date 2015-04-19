package cz.vhromada.catalog.web.genres.controllers;

import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.catalog.web.genres.panels.GenreFormPanel;
import cz.vhromada.catalog.web.genres.panels.GenresMenuPanel;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.catalog.web.system.CatalogSession;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
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
        final CatalogSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.GENRES_ADD_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Create");
        final PanelData panelData = new PanelData(GenreFormPanel.ID, new CompoundPropertyModel<>(new GenreMO()));
        final PanelData menuData = new PanelData(GenresMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Add genre", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.GENRES_ADD;
    }

}
