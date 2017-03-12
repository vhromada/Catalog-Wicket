package cz.vhromada.catalog.web.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.WicketApplication;
import cz.vhromada.catalog.web.common.ProgramUtils;
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
        assertThat(converter.convert(null, Program.class), is(nullValue()));
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
        assertThat(converter.convert(null, ProgramMO.class), is(nullValue()));
    }

}
