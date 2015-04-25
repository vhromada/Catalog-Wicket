package cz.vhromada.catalog.web.movies.panels;

import java.util.Arrays;

import cz.vhromada.catalog.commons.Constants;
import cz.vhromada.catalog.commons.Language;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.components.GenresChoice;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
import cz.vhromada.catalog.web.panels.AbstractFormPanel;
import cz.vhromada.web.wicket.controllers.Flow;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
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
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
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
     * Maximum hours
     */
    private static final int MAX_HOURS = 23;

    /**
     * Maximum minutes
     */
    private static final int MAX_MINUTES = 59;

    /**
     * Maximum seconds
     */
    private static final int MAX_SECONDS = 59;

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

        final WebMarkupContainer mediaContainer = new WebMarkupContainer("mediaContainer");
        mediaContainer.setOutputMarkupId(true);

        final MediaListView media = new MediaListView("media") {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onMediumRemove(final AjaxRequestTarget target) {
                target.add(mediaContainer);
            }

        };

        final AjaxLink<Void> addMedium = new AjaxLink<Void>("addMedium") {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                MovieFormPanel.this.getModelObject().getMedia().add(new TimeMO());

                target.add(mediaContainer);
            }

        };

        final TextField<String> csfd = new TextField<>("csfd");

        final IModel<Boolean> imdbModel = new Model<>(getModelObject().getImdbCode() != null && getModelObject().getImdbCode() > 0);

        final NumberTextField<Integer> imdbCode = new NumberTextField<>("imdbCode");
        imdbCode.setMinimum(1)
                .setMaximum(Constants.MAX_IMDB_CODE)
                .setLabel(Model.of("IMDB code"))
                .add(RangeValidator.range(1, Constants.MAX_IMDB_CODE))
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
                final MovieMO movie = MovieFormPanel.this.getModelObject();
                if (getModelObject()) {
                    movie.setImdbCode(null);
                } else {
                    movie.setImdbCode(-1);
                }

                target.add(imdbCode);
            }

        };

        final TextField<String> wikiCz = new TextField<>("wikiCz");

        final TextField<String> wikiEn = new TextField<>("wikiEn");

        final TextField<String> picture = new TextField<>("picture");

        final TextField<String> note = new TextField<>("note");

        final GenresChoice genres = new GenresChoice("genres", getModelObject().getAllGenres());

        language.add(languages);
        subtitles.add(subtitlesList);
        mediaContainer.add(media);
        getForm().add(czechName, originalName, year, language, subtitles, mediaContainer, addMedium, csfd, imdb, imdbCode, wikiEn, wikiCz, picture, note,
                genres);
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

    private abstract static class MediaListView extends ListView<TimeMO> {

        /**
         * SerialVersionUID
         */
        private static final long serialVersionUID = 1L;

        /**
         * Creates a new instance of MediaListView.
         *
         * @param id ID
         * @throws org.apache.wicket.WicketRuntimeException if ID is null
         */
        private MediaListView(final String id) {
            super(id);
        }

        @Override
        protected void populateItem(final ListItem<TimeMO> item) {
            final int index = item.getIndex();
            final int mediumIndex = index + 1;
            final String baseName = "Medium " + mediumIndex;
            final String baseId = "medium" + mediumIndex;

            final Label mediumLabel = new Label("mediumLabel", baseName);

            final AjaxLink<Void> remove = new AjaxLink<Void>("remove") {

                /**
                 * SerialVersionUID
                 */
                private static final long serialVersionUID = 1L;

                @Override
                public void onClick(final AjaxRequestTarget target) {
                    MediaListView.this.getModelObject().remove(index);

                    onMediumRemove(target);
                }

                @Override
                protected void onComponentTag(final ComponentTag tag) {
                    super.onComponentTag(tag);

                    tag.put("value", "Remove");
                }

            };
            remove.setMarkupId("remove" + mediumIndex)
                    .setVisible(index > 0);

            final Label mediumHoursLabel = new Label("mediumHoursLabel", baseName + " hours");

            final NumberTextField<Integer> mediumHours = new NumberTextField<>("mediumHours", new PropertyModel<Integer>(item.getModelObject(), "hours"));
            mediumHours.setMinimum(0)
                    .setMaximum(MAX_HOURS)
                    .setLabel(Model.of(baseName + " hours"))
                    .setRequired(true)
                    .add(RangeValidator.range(0, MAX_HOURS))
                    .setMarkupId(baseId + "Hours");

            final Label mediumMinutesLabel = new Label("mediumMinutesLabel", baseName + " minutes");

            final NumberTextField<Integer> mediumMinutes = new NumberTextField<>("mediumMinutes",
                    new PropertyModel<Integer>(item.getModelObject(), "minutes"));
            mediumMinutes.setMinimum(0)
                    .setMaximum(MAX_MINUTES)
                    .setLabel(Model.of(baseName + " minutes"))
                    .setRequired(true)
                    .add(RangeValidator.range(0, MAX_MINUTES))
                    .setMarkupId(baseId + "Minutes");

            final Label mediumSecondsLabel = new Label("mediumSecondsLabel", baseName + " seconds");

            final NumberTextField<Integer> mediumSeconds = new NumberTextField<>("mediumSeconds",
                    new PropertyModel<Integer>(item.getModelObject(), "seconds"));
            mediumSeconds.setMinimum(0)
                    .setMaximum(MAX_SECONDS)
                    .setLabel(Model.of(baseName + " seconds"))
                    .setRequired(true)
                    .add(RangeValidator.range(0, MAX_SECONDS))
                    .setMarkupId(baseId + "Seconds");

            item.add(mediumLabel, remove, mediumHoursLabel, mediumHours, mediumMinutesLabel, mediumMinutes, mediumSecondsLabel, mediumSeconds);
        }

        /**
         * Callback for removing medium.
         *
         * @param target AJAX request target
         */
        protected abstract void onMediumRemove(final AjaxRequestTarget target);

    }

}
