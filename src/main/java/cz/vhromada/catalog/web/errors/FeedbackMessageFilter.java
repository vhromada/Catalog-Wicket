package cz.vhromada.catalog.web.errors;

import org.apache.wicket.feedback.FeedbackMessage;
import org.apache.wicket.feedback.IFeedbackMessageFilter;

/**
 * A class represents filter for feedback messages.
 *
 * @author Vladimir Hromada
 */
public class FeedbackMessageFilter implements IFeedbackMessageFilter {

    /**
     * ID of feedback message
     */
    private int id;

    /**
     * Construct using fields.
     *
     * @param id ID of feedback message
     */
    public FeedbackMessageFilter(final int id) {
        this.id = id;
    }

    @Override
    public boolean accept(final FeedbackMessage message) {
        if (message instanceof EqualityFeedbackMessage) {
            return ((EqualityFeedbackMessage) message).getId() == id;
        }

        return false;
    }

}
