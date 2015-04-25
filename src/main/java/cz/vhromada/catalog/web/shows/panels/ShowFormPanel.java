package cz.vhromada.catalog.web.shows.panels;

import cz.vhromada.catalog.web.components.GenresChoice;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
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
     * Maximum IMDB code
     */
    private static final int MAX_IMDB_CODE = 9999999;

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
        czechName.setLabel(Model.of("Czech name"));

        final RequiredTextField<String> originalName = new RequiredTextField<>("originalName");
        originalName.setLabel(Model.of("Original name"));

        final TextField<String> csfd = new TextField<>("csfd");

        final IModel<Boolean> imdbModel = new Model<>();
        imdbModel.setObject(getModelObject().getImdbCode() != null && getModelObject().getImdbCode() > 0);

        final NumberTextField<Integer> imdbCode = new NumberTextField<>("imdbCode");
        imdbCode.setMinimum(1)
                .setMaximum(MAX_IMDB_CODE)
                .setLabel(Model.of("IMDB code"))
                .add(RangeValidator.range(1, MAX_IMDB_CODE))
                .setVisible(imdbModel.getObject())
                .setOutputMarkupPlaceholderTag(true);


        final AjaxCheckBox imdb = new AjaxCheckBox("imdb", imdbModel) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                imdbCode.setVisible(getModelObject());
                imdbCode.setRequired(getModelObject());
                final ShowMO show = ShowFormPanel.this.getModelObject();
                if (getModelObject()) {
                    show.setImdbCode(null);
                } else {
                    show.setImdbCode(-1);
                }
                target.add(imdbCode);
            }

        };

        final TextField<String> wikiCz = new TextField<>("wikiCz");

        final TextField<String> wikiEn = new TextField<>("wikiEn");

        final TextField<String> picture = new TextField<>("picture");

        final TextField<String> note = new TextField<>("note");

        final GenresChoice genres = new GenresChoice("genres", getModelObject().getAllGenres());

        getForm().add(czechName, originalName, csfd, imdb, imdbCode, wikiEn, wikiCz, picture, note, genres);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.SHOWS_CANCEL;
    }

    @Override
    protected void onFormValidation(final Form<ShowMO> panelForm) {
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
