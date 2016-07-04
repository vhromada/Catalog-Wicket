package cz.vhromada.catalog.web.panels;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.catalog.web.system.CatalogApplication;
import cz.vhromada.web.wicket.controllers.Flow;
import cz.vhromada.web.wicket.flow.AjaxFlowLink;
import cz.vhromada.web.wicket.flow.AjaxFlowSubmitLink;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormValidatingBehavior;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.form.Form;
import org.apache.wicket.markup.html.panel.FeedbackPanel;
import org.apache.wicket.markup.html.panel.GenericPanel;
import org.apache.wicket.model.CompoundPropertyModel;
import org.apache.wicket.util.time.Duration;

/**
 * An abstract class represents form.
 *
 * @param <T> type of model data
 * @author Vladimir Hromada
 */
public abstract class AbstractFormPanel<T> extends GenericPanel<T> {

    /**
     * Name for session attribute with flow on form submit
     */
    public static final String SUBMIT_FLOW = "submitFlow";

    /**
     * Name for session attribute with text for submit button
     */
    public static final String SUBMIT_MESSAGE = "submitMessage";

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * Form
     */
    private Form<T> form;

    /**
     * Creates a new instance of AbstractFormPanel.
     *
     * @param id    ID
     * @param model model
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public AbstractFormPanel(final String id, final CompoundPropertyModel<T> model) {
        super(id, model);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        form = new Form<>("form", getModel());

        final FeedbackPanel feedbackPanel = new FeedbackPanel("feedbackPanel");
        feedbackPanel.setOutputMarkupId(true);

        final AjaxFlowSubmitLink submit = new AjaxFlowSubmitLink("submit", form, CatalogApplication.<CatalogFlow>getSessionAttribute(SUBMIT_FLOW)) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            @SuppressWarnings("unchecked")
            public void onSubmit(final AjaxRequestTarget target, final Form<?> linkForm) {
                onFormSubmit((Form<T>) linkForm);

                super.onSubmit(target, linkForm);
            }

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);

                tag.put("value", CatalogApplication.<String>getSessionAttribute(SUBMIT_MESSAGE));
            }

            @Override
            protected void onError(final AjaxRequestTarget target, final Form<?> linkForm) {
                super.onError(target, linkForm);

                target.add(feedbackPanel);
            }

        };

        final AjaxFlowLink cancel = new AjaxFlowLink("cancel", getCancelFlow()) {

            /**
             * SerialVersionUID
             * */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onComponentTag(final ComponentTag tag) {
                super.onComponentTag(tag);

                tag.put("value", "Cancel");
            }

        };


        form.add(new AjaxFormValidatingBehavior("onchange", Duration.ONE_SECOND));

        form.add(submit, cancel);
        add(feedbackPanel, form);
    }

    /**
     * Returns flow on form cancel.
     *
     * @return flow on form cancel
     */
    protected abstract Flow getCancelFlow();

    /**
     * Returns form.
     *
     * @return form
     */
    protected Form<T> getForm() {
        return form;
    }

    /**
     * Callback for form submit.
     *
     * @param panelForm form
     */
    protected abstract void onFormSubmit(final Form<T> panelForm);

}
