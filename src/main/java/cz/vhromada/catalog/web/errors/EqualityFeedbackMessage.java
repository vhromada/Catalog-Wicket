package cz.vhromada.catalog.web.errors;

import java.io.Serializable;

import cz.vhromada.validators.Validators;

import org.apache.wicket.Component;
import org.apache.wicket.feedback.FeedbackMessage;

/**
 * A class represents feedback message with equality comparison.
 *
 * @author Vladimir Hromada
 */
public class EqualityFeedbackMessage extends FeedbackMessage {

    /**
     * SerialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * ID of feedback message
     */
    private int id;

    /**
     * Construct using fields.
     *
     * @param id        ID of feedback message
     * @param component component
     * @param message   message
     * @throws IllegalArgumentException if component is null
     *                                  or message is null
     */
    public EqualityFeedbackMessage(final int id, final Component component, final Serializable message) {
        super(component, message, FeedbackMessage.ERROR);

        Validators.validateArgumentNotNull(component, "Component");

        this.id = id;
    }

    /**
     * Returns ID of feedback message.
     *
     * @return ID of feedback message
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof EqualityFeedbackMessage)) {
            return false;
        }
        final EqualityFeedbackMessage equalityFeedbackMessage = (EqualityFeedbackMessage) obj;
        return id == equalityFeedbackMessage.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

}
