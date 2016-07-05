package cz.vhromada.catalog.web.episodes.controllers;

import cz.vhromada.catalog.facade.to.EpisodeTO;
import cz.vhromada.catalog.web.episodes.mo.EpisodeMO;
import cz.vhromada.catalog.web.episodes.panels.EpisodeFormPanel;
import cz.vhromada.catalog.web.episodes.panels.EpisodesMenuPanel;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.validators.Validators;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.events.PageEvent;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing form for updating episode.
 *
 * @author Vladimir Hromada
 */
@Component("updateEpisodeController")
public class UpdateEpisodeController extends Controller<IModel<EpisodeTO>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateEpisodeController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateEpisodeController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<EpisodeTO> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.EPISODES_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final PanelData<EpisodeMO> panelData = new PanelData<>(EpisodeFormPanel.ID,
                new CompoundPropertyModel<>(converter.convert(data.getObject(), EpisodeMO.class)));
        final PanelData<Void> menuData = new PanelData<>(EpisodesMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit episode", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.EPISODES_UPDATE;
    }

}
