package cz.vhromada.catalog.web.flow;

import cz.vhromada.catalog.web.controllers.Flow;
import cz.vhromada.catalog.web.controllers.FlowRunner;
import cz.vhromada.validators.Validators;

import org.apache.wicket.WicketRuntimeException;
import org.apache.wicket.ajax.AjaxChannel;
import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.ajax.attributes.AjaxRequestAttributes;
import org.apache.wicket.ajax.markup.html.AjaxLink;
import org.apache.wicket.model.IModel;

/**
 * A class represents AJAX flow link.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public class AjaxFlowLink<T> extends AjaxLink<T> {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * AJAX channel
     */
    private static final String CHANNEL_FLOW_LINK = "channel.flow.link";

    /**
     * Flow
     */
    private Flow flow;

    /**
     * Creates a new instance of AjaxFlowLink.
     *
     * @param id   ID
     * @param flow flow
     * @throws WicketRuntimeException   if ID is null
     * @throws IllegalArgumentException if flow is null
     */
    public AjaxFlowLink(final String id, final Flow flow) {
        this(id, null, flow);
    }

    /**
     * Creates a new instance of AjaxFlowLink.
     *
     * @param id    ID
     * @param model model
     * @param flow  flow
     * @throws WicketRuntimeException   if ID is null
     * @throws IllegalArgumentException if flow is null
     */
    public AjaxFlowLink(final String id, final IModel<T> model, final Flow flow) {
        super(id, model);

        Validators.validateArgumentNotNull(flow, "Flow");

        this.flow = flow;
    }

    @Override
    public void onClick(final AjaxRequestTarget target) {
        new FlowRunner(this, flow, getModel());
    }

    @Override
    protected void updateAjaxAttributes(final AjaxRequestAttributes attributes) {
        super.updateAjaxAttributes(attributes);

        // allow only one running AJAX request at a time, other AJAX requests in the same channel will be dropped
        attributes.setChannel(new AjaxChannel(CHANNEL_FLOW_LINK, AjaxChannel.Type.ACTIVE));
    }

}
