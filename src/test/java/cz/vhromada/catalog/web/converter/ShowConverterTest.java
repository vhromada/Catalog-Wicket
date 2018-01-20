package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.common.ShowUtils;
import cz.vhromada.catalog.web.show.mo.ShowMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link ShowMO} to {@link Show}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class ShowConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertShowMO() {
        final ShowMO showMO = ShowUtils.getShowMO();

        final Show show = converter.convert(showMO, Show.class);

        ShowUtils.assertShowDeepEquals(showMO, show);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for show.
     */
    @Test
    void convertShowMO_NullShowMO() {
        assertThat(converter.convert(null, Show.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertShow() {
        final Show show = ShowUtils.getShow();

        final ShowMO showMO = converter.convert(show, ShowMO.class);

        ShowUtils.assertShowDeepEquals(showMO, show);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null show.
     */
    @Test
    void convertShow_NullShow() {
        assertThat(converter.convert(null, ShowMO.class)).isNull();
    }

}
