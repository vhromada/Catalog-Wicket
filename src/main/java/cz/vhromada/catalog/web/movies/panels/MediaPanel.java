package cz.vhromada.catalog.web.movies.panels;

import cz.vhromada.catalog.web.TimeMO;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.list.ListItem;
import org.apache.wicket.markup.html.list.ListView;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.Model;
import org.apache.wicket.model.PropertyModel;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * An abstract class represents panel with media.
 *
 * @author Vladimir Hromada
 */
public abstract class MediaPanel extends Panel {

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
     * Creates a new instance of MediaPanel.
     *
     * @param id ID
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public MediaPanel(final String id) {
        super(id);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

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
                onMediumAdd(target);

                target.add(mediaContainer);
            }

        };

        mediaContainer.add(media);
        add(mediaContainer, addMedium);
    }

    /**
     * Callback for adding medium.
     *
     * @param target AJAX request target
     */
    protected abstract void onMediumAdd(final AjaxRequestTarget target);

    /**
     * An abstract class represents list view with media
     */
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
