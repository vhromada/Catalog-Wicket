package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
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
 * A class represents test for converter from {@link GenreMO} to {@link GenreTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testWebConvertersContext.xml")
public class GenreMOToGenreTOConverterTest extends ObjectGeneratorTest {

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
        final GenreMO genreMO = objectGenerator.generate(GenreMO.class);

        final GenreTO genreTO = converter.convert(genreMO, GenreTO.class);

        DeepAsserts.assertNotNull(genreTO);
        DeepAsserts.assertEquals(genreMO, genreTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} with null argument.
     */
    @Test
    public void testConvertWithNullArgument() {
        assertNull(converter.convert(null, GenreTO.class));
    }

}
