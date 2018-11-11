package cz.vhromada.catalog.web.show.panel;

import cz.vhromada.catalog.web.component.GenresChoice;
import cz.vhromada.catalog.web.component.Image;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.panel.AbstractFormPanel;
import cz.vhromada.catalog.web.panel.ImdbPanel;
import cz.vhromada.catalog.web.panel.PicturesModalPanel;
import cz.vhromada.catalog.web.show.mo.ShowMO;
import cz.vhromada.web.wicket.controller.Flow;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormChoiceComponentUpdatingBehavior;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.AjaxLink;
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

        final Image pictureImage = new Image("pictureImage", getModelObject().getPicture());
        pictureImage.setVisible(getModelObject().getPicture() != null)
            .setOutputMarkupPlaceholderTag(true);

        final AjaxLink<Void> removePicture = new AjaxLink<>("removePicture") {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            public void onClick(final AjaxRequestTarget target) {
                ShowFormPanel.this.getModelObject().setPicture(null);
                ShowFormPanel.this.getForm().modelChanged();
                setVisible(false);
                pictureImage.setVisible(false);
                target.add(this, pictureImage);
            }

        };
        removePicture.setVisible(getModelObject().getPicture() != null)
            .setOutputMarkupPlaceholderTag(true);

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

        final PicturesModalPanel pictures = new PicturesModalPanel("pictures", getModelObject().getAllPictures()) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected AjaxFormChoiceComponentUpdatingBehavior getValidationBehavior() {
                return getPictureChoiceBehavior(pictureImage, removePicture);
            }

        };

        getForm().add(czechName, originalName, csfd, imdb, wikiEn, wikiCz, pictureImage, removePicture, note, genres, pictures);
    }

    @Override
    protected Flow getCancelFlow() {
        return CatalogFlow.SHOWS_CANCEL;
    }

    @Override
    protected void onFormSubmit() {
        final ShowMO show = getForm().getModelObject();
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
        if (show.getNote() == null) {
            show.setNote("");
        }
    }


    /**
     * Returns behavior for picture choice.
     *
     * @param picture       picture
     * @param removePicture link for removing picture
     * @return behavior for picture choice
     */
    @SuppressWarnings("Duplicates")
    private AjaxFormChoiceComponentUpdatingBehavior getPictureChoiceBehavior(final Image picture, final AjaxLink<Void> removePicture) {
        return new AjaxFormChoiceComponentUpdatingBehavior() {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                update(target);
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final RuntimeException e) {
                super.onError(target, e);

                update(target);
            }

            /**
             * Update components.
             *
             * @param target AJAX request target
             */
            private void update(final AjaxRequestTarget target) {
                picture.update(getModelObject().getPicture());
                picture.setVisible(true);
                removePicture.setVisible(true);
                target.add(picture, removePicture);
            }

        };

    }

}
