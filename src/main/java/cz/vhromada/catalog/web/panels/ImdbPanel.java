package cz.vhromada.catalog.web.panels;

import cz.vhromada.catalog.utils.Constants;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.form.AjaxFormComponentUpdatingBehavior;
import org.apache.wicket.ajax.markup.html.form.AjaxCheckBox;
import org.apache.wicket.markup.html.form.NumberTextField;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.model.Model;
import org.apache.wicket.validation.validator.RangeValidator;

/**
 * An abstract class represents panel for IMDB code.
 *
 * @author Vladimir Hromada
 */
public abstract class ImdbPanel extends Panel {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * IMDB code
     */
    private Integer imdbCode;

    /**
     * Creates a new instance of ImdbPanel.
     *
     * @param id       ID
     * @param imdbCode IMDB code
     * @throws org.apache.wicket.WicketRuntimeException if ID is null
     */
    public ImdbPanel(final String id, final Integer imdbCode) {
        super(id);

        this.imdbCode = imdbCode;
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        final IModel<Boolean> imdbModel = new Model<>(imdbCode != null && imdbCode > 0);

        final NumberTextField<Integer> imdbCodeField = new NumberTextField<>("imdbCode");
        imdbCodeField.setMinimum(1)
                .setMaximum(Constants.MAX_IMDB_CODE)
                .setLabel(Model.of("IMDB code"))
                .add(RangeValidator.range(1, Constants.MAX_IMDB_CODE))
                .setVisible(imdbModel.getObject())
                .setOutputMarkupPlaceholderTag(true)
                .add(getValidationBehavior());

        final AjaxCheckBox imdb = new AjaxCheckBox("imdb", imdbModel) {

            /**
             * SerialVersionUID
             */
            private static final long serialVersionUID = 1L;

            @Override
            protected void onUpdate(final AjaxRequestTarget target) {
                imdbCodeField.setVisible(getModelObject());
                imdbCodeField.setRequired(getModelObject());
                onImdbChange(getModelObject() ? null : -1);

                target.add(imdbCodeField);
            }

        };

        add(imdbCodeField, imdb);
    }

    /**
     * Callback for IMDB code change.
     *
     * @param imdbCodeValue new IMDB code value
     */
    protected abstract void onImdbChange(Integer imdbCodeValue);

    /**
     * Returns validation behavior.
     *
     * @return validation behavior
     */
    protected abstract AjaxFormComponentUpdatingBehavior getValidationBehavior();

}
