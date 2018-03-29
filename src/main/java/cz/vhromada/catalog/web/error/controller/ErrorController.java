package cz.vhromada.catalog.web.error.controller;

import cz.vhromada.catalog.web.flow.CatalogFlow;
import cz.vhromada.result.Result;
import cz.vhromada.web.wicket.controller.Controller;
import cz.vhromada.web.wicket.controller.Flow;

import org.springframework.stereotype.Component;

/**
 * A class represents controller for showing errors.
 *
 * @author Vladimir Hromada
 */
@Component("errorController")
public class ErrorController extends Controller<Result<?>> {

    @Override
    public void handle(final Result<?> data) {
        throw new IllegalArgumentException("Error" + data);
    }

    @Override
    public Flow getFlow() {
        return CatalogFlow.ERROR;
    }

}
