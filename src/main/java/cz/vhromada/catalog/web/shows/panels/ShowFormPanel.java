package cz.vhromada.catalog.web.shows.panels;

import cz.vhromada.catalog.web.components.GenresChoice;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.panels.ImdbPanel;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for show.
 *
 * @author Vladimir Hromada
 */
@Component(ShowFormPanel.ID)
@Scope("prototype")
public class ShowFormPanel extends AbstractFormPanel<ShowMO> {

    /**
     * ID
     */
    public static final String ID = "showFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of ShowFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ShowFormPanel(final String id, final CompoundPropertyModel<ShowMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> czechName = new RequiredTextField<>("czechName");
        czechName.setLabel(Model.of("Czech name"))
                .add(getValidationBehavior());

        final RequiredTextField<String> originalName = new RequiredTextField<>("originalName");
        originalName.setLabel(Model.of("Original name"))
                .add(getValidationBehavior());

        final TextField<String> csfd = new TextField<>("csfd");

        final ImdbPanel imdb = new ImdbPanel("imdb", getModelObject().getImdbCode()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onImdbChange(final Integer imdbCodeValue) {
                ShowFormPanel.this.getModelObject().setImdbCode(imdbCodeValue);
            }

            @Override
            protected AjaxFormComponentUpdatingBehavior getValidationBehavior() {
                return ShowFormPanel.this.getValidationBehavior();
            }

        };

        final TextField<String> wikiCz = new TextField<>("wikiCz");

        final TextField<String> wikiEn = new TextField<>("wikiEn");

        final TextField<String> picture = new TextField<>("picture");

        final TextField<String> note = new TextField<>("note");

        final GenresChoice genres = new GenresChoice("genres", getModelObject().getAllGenres()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected AjaxFormComponentUpdatingBehavior getValidationBehavior() {
                return ShowFormPanel.this.getValidationBehavior();
            }

        };

        getForm().add(czechName, originalName, csfd, imdb, wikiEn, wikiCz, picture, note, genres);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.SHOWS_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<ShowMO> panelForm) {
        final ShowMO show = panelForm.getModelObject();
        if (show.getCsfd() == null) {
            show.setCsfd("");
        }
        if (show.getImdbCode() == null || show.getImdbCode() < 1) {
            show.setImdbCode(-1);
        }
        if (show.getWikiCz() == null) {
            show.setWikiCz("");
        }
        if (show.getWikiEn() == null) {
            show.setWikiEn("");
        }
        if (show.getPicture() == null) {
            show.setPicture("");
        }
        if (show.getNote() == null) {
            show.setNote("");
        }
    }

}
