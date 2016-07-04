package cz.vhromada.catalog.web.seasons.controllers;

import java.util.ArrayList;

import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.facade.to.SeasonTO;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.catalog.web.seasons.panels.SeasonFormPanel;
import cz.vhromada.catalog.web.seasons.panels.SeasonsMenuPanel;
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
 * A class represents controller for seasoning form for updating season.
 *
 * @author Vladimir Hromada
 */
@Component("updateSeasonController")
public class UpdateSeasonController extends Controller<IModel<SeasonTO>> {

    /**
     * Converter
     */
    private Converter converter;

    /**
     * Creates a new instance of UpdateSeasonController.
     *
     * @param converter converter
     * @throws IllegalArgumentException if converter is null
     */
    @Autowired
    public UpdateSeasonController(@Qualifier("webDozerConverter") final Converter converter) {
        Validators.validateArgumentNotNull(converter, "converter");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<SeasonTO> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SEASONS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final SeasonMO season = converter.convert(data.getObject(), SeasonMO.class);
        if (season.getSubtitles() == null) {
            season.setSubtitles(new ArrayList<Language>());
        }
        final PanelData panelData = new PanelData(SeasonFormPanel.ID, new CompoundPropertyModel<>(season));
        final PanelData menuData = new PanelData(SeasonsMenuPanel.ID, null);

        final PageEvent event = new PanelEvent(panelData, "Edit season", menuData);

        getUi().fireEvent(event);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_UPDATE;
    }

}
