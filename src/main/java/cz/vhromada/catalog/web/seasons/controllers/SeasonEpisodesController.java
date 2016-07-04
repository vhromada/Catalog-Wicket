package cz.vhromada.catalog.web.seasons.controllers;

import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing season episodes.
 *
 * @author Vladimir Hromada
 */
@Component("seasonEpisodesController")
public class SeasonEpisodesController extends Controller<IModel<SeasonTO>> {

    /**
     * Name of seasons attribute in session
     */
    public static final String SEASON_ATTRIBUTE = "season";

    @Override
    public void handle(final IModel<SeasonTO> data) {
        final WebSession session = WebSession.get();
        session.setAttribute(SEASON_ATTRIBUTE, data.getObject());

        getUi().fireEvent(new ControllerEvent(CatalogFlow.EPISODES_LIST, null));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_EPISODES;
    }

}
