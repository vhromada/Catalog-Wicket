package cz.vhromada.catalog.web.converter;

import cz.vhromada.converters.orika.OrikaConfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * A class represents Spring configuration for tests of converters.
 *
 * @author Vladimir Hromada
 */
@Configuration
@ComponentScan("cz.vhromada.catalog.web.converter")
@Import(OrikaConfiguration.class)
public class ConverterTestConfiguration {
}
