package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.ShowTO;
import cz.vhromada.catalog.web.commons.ShowUtils;
import cz.vhromada.catalog.web.shows.mo.ShowMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link ShowMO} to {@link ShowTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class ShowConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    @Qualifier("webDozerConverter")
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO.
     */
    @Test
    public void testConvertShowMO() {
        final ShowMO showMO = ShowUtils.getShowMO();

        final ShowTO showTO = converter.convert(showMO, ShowTO.class);

        ShowUtils.assertShowDeepEquals(showMO, showTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertShowMO_NullArgument() {
        assertNull(converter.convert(null, ShowTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertShowTO() {
        final ShowTO showTO = ShowUtils.getShowTO();

        final ShowMO showMO = converter.convert(showTO, ShowMO.class);

        ShowUtils.assertShowDeepEquals(showMO, showTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertShowTO_NullArgument() {
        assertNull(converter.convert(null, ShowMO.class));
    }

}
