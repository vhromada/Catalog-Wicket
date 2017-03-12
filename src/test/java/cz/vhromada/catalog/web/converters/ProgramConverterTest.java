package cz.vhromada.catalog.web.converters;

import static org.junit.Assert.assertNull;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.WicketApplication;
import cz.vhromada.catalog.web.commons.ProgramUtils;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;
import cz.vhromada.converters.Converter;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * A class represents test for converter from {@link ProgramMO} to {@link Program}.
 *
 * @author Vladimir Hromada
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WicketApplication.class)
public class ProgramConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    public void convertProgramMO() {
        final ProgramMO programMO = ProgramUtils.getProgramMO();

        final Program program = converter.convert(programMO, Program.class);

        ProgramUtils.assertProgramDeepEquals(programMO, program);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for program.
     */
    @Test
    public void convertProgramMO_NullProgramMO() {
        assertNull(converter.convert(null, Program.class));
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    public void convertProgram() {
        final Program program = ProgramUtils.getProgram();

        final ProgramMO programMO = converter.convert(program, ProgramMO.class);

        ProgramUtils.assertProgramDeepEquals(programMO, program);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null program.
     */
    @Test
    public void convertProgram_NullProgram() {
        assertNull(converter.convert(null, ProgramMO.class));
    }

}
