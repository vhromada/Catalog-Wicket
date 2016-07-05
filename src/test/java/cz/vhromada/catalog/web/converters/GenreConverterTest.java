package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.GenreTO;
import cz.vhromada.catalog.web.commons.GenreUtils;
import cz.vhromada.catalog.web.genres.mo.GenreMO;
import cz.vhromada.converters.Converter;

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
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class GenreConverterTest {

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
    public void testConvertGenreMO() {
        final GenreMO genreMO = GenreUtils.getGenreMO();

        final GenreTO genreTO = converter.convert(genreMO, GenreTO.class);

        GenreUtils.assertGenreDeepEquals(genreMO, genreTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertGenreMO_NullArgument() {
        assertNull(converter.convert(null, GenreTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertGenreTO() {
        final GenreTO genreTO = GenreUtils.getGenreTO();

        final GenreMO genreMO = converter.convert(genreTO, GenreMO.class);

        GenreUtils.assertGenreDeepEquals(genreMO, genreTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertGenreTO_NullArgument() {
        assertNull(converter.convert(null, GenreMO.class));
    }

}
