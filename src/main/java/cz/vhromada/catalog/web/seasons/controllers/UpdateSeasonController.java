package cz.vhromada.catalog.web.seasons.controllers;

import java.util.ArrayList;

import cz.vhromada.catalog.entity.Season;
import cz.vhromada.catalog.web.events.PanelData;
import cz.vhromada.catalog.web.events.PanelEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.seasons.mo.SeasonMO;
import cz.vhromada.catalog.web.seasons.panels.SeasonFormPanel;
import cz.vhromada.catalog.web.seasons.panels.SeasonsMenuPanel;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.converters.Converter;
import cz.vhromada.web.wicket.controllers.Controller;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.protocol.http.WebSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

/**
 * A class represents controller for seasoning form for updating season.
 *
 * @author Vladimir Hromada
 */
@Component("updateSeasonController")
public class UpdateSeasonController extends Controller<IModel<Season>> {

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
    public UpdateSeasonController(final Converter converter) {
        Assert.notNull(converter, "Converter mustn't be null.");

        this.converter = converter;
    }

    @Override
    public void handle(final IModel<Season> data) {
        final WebSession session = CatalogApplication.getSession();
        session.setAttribute(AbstractFormPanel.SUBMIT_FLOW, CatalogFlow.SEASONS_UPDATE_CONFIRM);
        session.setAttribute(AbstractFormPanel.SUBMIT_MESSAGE, "Update");
        final SeasonMO season = converter.convert(data.getObject(), SeasonMO.class);
        if (season.getSubtitles() == null) {
            season.setSubtitles(new ArrayList<>());
        }
        final PanelData<SeasonMO> panelData = new PanelData<>(SeasonFormPanel.ID, new CompoundPropertyModel<>(season));
        final PanelData<Void> menuData = new PanelData<>(SeasonsMenuPanel.ID, null);

        getUi().fireEvent(new PanelEvent(panelData, "Edit season", menuData));
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.SEASONS_UPDATE;
    }

}
