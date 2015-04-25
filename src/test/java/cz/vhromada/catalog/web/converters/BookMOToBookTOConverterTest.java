package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.BookTO;
import cz.vhromada.catalog.web.books.mo.BookMO;
import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
import cz.vhromada.converters.Converter;
import cz.vhromada.generator.ObjectGenerator;
import cz.vhromada.test.DeepAsserts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link BookMO} to {@link BookTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testWebConvertersContext.xml")
public class BookMOToBookTOConverterTest extends ObjectGeneratorTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    @Qualifier("webDozerConverter")
    private Converter converter;

    /**
     * Instance of {@link ObjectGenerator}
     */
    @Autowired
    private ObjectGenerator objectGenerator;

    /**
     * Test method for {@link Converter#convert(Object, Class)}.
     */
    @Test
    public void testConvert() {
        final BookMO bookMO = objectGenerator.generate(BookMO.class);

        final BookTO bookTO = converter.convert(bookMO, BookTO.class);

        DeepAsserts.assertNotNull(bookTO, "bookCategory");
        DeepAsserts.assertEquals(bookMO, bookTO, "bookCategory");
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} with null argument.
     */
    @Test
    public void testConvertWithNullArgument() {
        assertNull(converter.convert(null, BookTO.class));
    }

}
