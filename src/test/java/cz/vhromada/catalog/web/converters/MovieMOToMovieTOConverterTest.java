package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import java.util.List;

import cz.vhromada.catalog.commons.Time;
import cz.vhromada.catalog.facade.to.MovieTO;
import cz.vhromada.catalog.web.TimeMO;
import cz.vhromada.catalog.web.commons.ObjectGeneratorTest;
import cz.vhromada.catalog.web.movies.mo.MovieMO;
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
 * A class represents test for converter from {@link MovieMO} to {@link MovieTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:testWebConvertersContext.xml")
public class MovieMOToMovieTOConverterTest extends ObjectGeneratorTest {

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
        final MovieMO movieMO = objectGenerator.generate(MovieMO.class);

        final MovieTO movieTO = converter.convert(movieMO, MovieTO.class);

        DeepAsserts.assertNotNull(movieTO);
        DeepAsserts.assertEquals(movieMO, movieTO, "media", "allGenres");
        assertMediaDeepEquals(movieMO.getMedia(), movieTO.getMedia());
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} with null argument.
     */
    @Test
    public void testConvertWithNullArgument() {
        assertNull(converter.convert(null, MovieTO.class));
    }

    /**
     * Assert media deep equals.
     *
     * @param expected expected media
     * @param actual   actual media
     */
    private static void assertMediaDeepEquals(final List<TimeMO> expected, final List<Integer> actual) {
        DeepAsserts.assertEquals(expected.size(), actual.size());
        for (int i = 0; i < expected.size(); i++) {
            assertMediumDeepEquals(expected.get(i), actual.get(i));
        }
    }

    /**
     * Assert medium deep equals.
     *
     * @param expected expected medium
     * @param actual   actual medium
     */
    private static void assertMediumDeepEquals(final TimeMO expected, final Integer actual) {
        final int hours = expected.getHours();
        final int minutes = expected.getMinutes();
        final int seconds = expected.getSeconds();
        final Time length = new Time(hours, minutes, seconds);
        DeepAsserts.assertEquals(length.getLength(), actual);
    }

}
