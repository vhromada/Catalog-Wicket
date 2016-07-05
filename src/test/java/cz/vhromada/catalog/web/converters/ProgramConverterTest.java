package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.facade.to.ProgramTO;
import cz.vhromada.catalog.web.commons.ProgramUtils;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.converters.Converter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link ProgramMO} to {@link ProgramTO}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:webDozerMappingContext.xml")
public class ProgramConverterTest {

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
    public void testConvertProgramMO() {
        final ProgramMO programMO = ProgramUtils.getProgramMO();

        final ProgramTO programTO = converter.convert(programMO, ProgramTO.class);

        ProgramUtils.assertProgramDeepEquals(programMO, programTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to TO with null argument.
     */
    @Test
    public void testConvertProgramMO_NullArgument() {
        assertNull(converter.convert(null, ProgramTO.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO.
     */
    @Test
    public void testConvertProgramTO() {
        final ProgramTO programTO = ProgramUtils.getProgramTO();

        final ProgramMO programMO = converter.convert(programTO, ProgramMO.class);

        ProgramUtils.assertProgramDeepEquals(programMO, programTO);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from TO to MO with null argument.
     */
    @Test
    public void testConvertProgramTO_NullArgument() {
        assertNull(converter.convert(null, ProgramMO.class));
    }

}
