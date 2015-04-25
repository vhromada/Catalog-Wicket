package cz.vhromada.catalog.web.movies.panels;

import java.util.Arrays;

import cz.vhromada.catalog.commons.Constants;
import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.components.GenresChoice;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.catalog.web.panels.ImdbPanel;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Check;
import org.apache.wicket.markup.html.form.CheckGroup;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.form.Radio;
import org.apache.wicket.markup.html.form.RadioGroup;
import org.apache.wicket.markup.html.form.RequiredTextField;
import org.apache.wicket.markup.html.form.TextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * A class represents panel with form for movie.
 *
 * @author Vladimir Hromada
 */
@Component(MovieFormPanel.ID)
@Scope("prototype")
public class MovieFormPanel extends AbstractFormPanel<MovieMO> {

    /**
     * ID
     */
    public static final String ID = "movieFormPanel";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Creates a new instance of MovieFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MovieFormPanel(final String id, final CompoundPropertyModel<MovieMO> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final RequiredTextField<String> czechName = new RequiredTextField<>("czechName");
        czechName.setLabel(Model.of("Czech name"));

        final RequiredTextField<String> originalName = new RequiredTextField<>("originalName");
        originalName.setLabel(Model.of("Original name"));

        final NumberTextField<Integer> year = new NumberTextField<>("year");
        year.setMinimum(Constants.MIN_YEAR)
                .setMaximum(Constants.CURRENT_YEAR)
                .setLabel(Model.of("Year"))
                .setRequired(true)
                .add(RangeValidator.range(Constants.MIN_YEAR, Constants.CURRENT_YEAR));

        final RadioGroup<Language> language = new RadioGroup<>("language");
        language.setLabel(Model.of("Language"))
                .setRequired(true);

        final ListView<Language> languages = new ListView<Language>("languages", Arrays.asList(Language.values())) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Language> item) {
                final Radio<Language> languageItem = new Radio<>("languageItem", item.getModel());
                languageItem.setMarkupId("language" + (item.getIndex() + 1));

                final Label languageItemLabel = new Label("languageItemLabel", item.getModel());

                item.add(languageItem, languageItemLabel);
            }

        };

        final CheckGroup<Language> subtitles = new CheckGroup<>("subtitles");

        final ListView<Language> subtitlesList = new ListView<Language>("subtitlesList", Arrays.asList(Language.CZ, Language.EN)) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void populateItem(final ListItem<Language> item) {
                final Check<Language> subtitlesItem = new Check<>("subtitlesItem", item.getModel());
                subtitlesItem.setMarkupId("subtitlesItem" + (item.getIndex() + 1));

                final Label subtitlesItemLabel = new Label("subtitlesItemLabel", item.getModel());

                item.add(subtitlesItem, subtitlesItemLabel);
            }

        };

        final MediaPanel media = new MediaPanel("media") {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onMediumAdd(final AjaxRequestTarget target) {
                getModelObject().getMedia().add(new TimeMO());
            }

        };

        final TextField<String> csfd = new TextField<>("csfd");

        final ImdbPanel imdb = new ImdbPanel("imdb", getModelObject().getImdbCode()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onImdbChange(final Integer imdbCodeValue) {
                MovieFormPanel.this.getModelObject().setImdbCode(imdbCodeValue);
            }

        };

        final TextField<String> wikiCz = new TextField<>("wikiCz");

        final TextField<String> wikiEn = new TextField<>("wikiEn");

        final TextField<String> picture = new TextField<>("picture");

        final TextField<String> note = new TextField<>("note");

        final GenresChoice genres = new GenresChoice("genres", getModelObject().getAllGenres());

        language.add(languages);
        subtitles.add(subtitlesList);
        getForm().add(czechName, originalName, year, language, subtitles, media, csfd, imdb, wikiEn, wikiCz, picture, note, genres);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.MOVIES_CANCEL;
    }

    @Override
    protected void onFormSubmit(final Form<MovieMO> panelForm) {
        final MovieMO movie = panelForm.getModelObject();
        if (movie.getCsfd() == null) {
            movie.setCsfd("");
        }
        if (movie.getImdbCode() == null || movie.getImdbCode() < 1) {
            movie.setImdbCode(-1);
        }
        if (movie.getWikiCz() == null) {
            movie.setWikiCz("");
        }
        if (movie.getWikiEn() == null) {
            movie.setWikiEn("");
        }
        if (movie.getPicture() == null) {
            movie.setPicture("");
        }
        if (movie.getNote() == null) {
            movie.setNote("");
        }
    }

}