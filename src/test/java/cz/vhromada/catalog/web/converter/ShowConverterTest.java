package cz.vhromada.catalog.web.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.common.ShowUtils;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link ShowMO} to {@link Show}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
public class ShowConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertShowMO() {
        final ShowMO showMO = ShowUtils.getShowMO();

        final Show show = converter.convert(showMO, Show.class);

        ShowUtils.assertShowDeepEquals(showMO, show);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for show.
     */
    @Test
    public void convertShowMO_NullShowMO() {
        assertThat(converter.convert(null, Show.class), is(nullValue()));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertShow() {
        final Show show = ShowUtils.getShow();

        final ShowMO showMO = converter.convert(show, ShowMO.class);

        ShowUtils.assertShowDeepEquals(showMO, show);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null show.
     */
    @Test
    public void convertShow_NullShow() {
        assertThat(converter.convert(null, ShowMO.class), is(nullValue()));
    }

}
