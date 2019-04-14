package cz.vhromada.catalog.web;

import cz.vhromada.catalog.CatalogConfiguration;
import cz.vhromada.web.wicket.WicketUtilsConfiguration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

/**
 * A class represents Spring boot application.
 *
 * @author Vladimir Hromada
 */
@SpringBootApplication
@Import({ CatalogConfiguration.class, WicketUtilsConfiguration.class })
public class WicketApplication extends SpringBootServletInitializer {

    /**
     * Main method.
     *
     * @param args the command line arguments
     */
    //CHECKSTYLE.OFF: UncommentedMain
    public static void main(final String[] args) {
        SpringApplication.run(WicketApplication.class, args);
    }
    //CHECKSTYLE.OFF: UncommentedMain

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        return builder.sources(WicketApplication.class);
    }

}
