package cz.vhromada.catalog.web.commons;

import cz.vhromada.catalog.web.events.ControllerEvent;
import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.result.Result;
import cz.vhromada.result.Status;
import cz.vhromada.web.wicket.controllers.Controller;

/**
 * An abstract class represents controller for processing result.
 *
 * @param <T> type of data
 * @author Vladimir Hromada
 */
public abstract class ResultController<T> extends Controller<T> {

    /**
     * Result
     */
    private Result<Void> result;

    /**
     * Adds results.
     *
     * @param results results
     */
    protected void addResults(final Result<?>... results) {
        if (result == null) {
            result = new Result<>();
        }

        for (final Result<?> resultItem : results) {
            this.result.addEvents(resultItem.getEvents());
        }
    }

    /**
     * Process result.
     *
     * @return true if result is OK.
     */
    protected boolean processResult() {
        if (isOk()) {
            return true;
        }

        getUi().fireEvent(new ControllerEvent(CatalogFlow.ERROR, result));
        return false;
    }

    /**
     * Returns true if result is OK.
     *
     * @return true if result is OK.
     */
    protected boolean isOk() {
        return Status.OK == result.getStatus();
    }

}
