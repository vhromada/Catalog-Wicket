package cz.vhromada.catalog.web.shows.controllers;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing show seasons.
 *
 * @author Vladimir Hromada
 */
@Component("showSeasonsController")
public class ShowSeasonsController extends Controller<IModel<Show>> {

    /**
     * Name of show attribute in session
     */
    public static final String SHOW_ATTRIBUTE = "show";

    @Override
    public void handle(final IModel<Show> data) {
        final WebSession session = WebSession.get();
        session.setAttribute(SHOW_ATTRIBUTE, data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.SEASONS_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SHOWS_SEASONS;
    }

}
