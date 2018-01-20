package cz.vhromada.catalog.web.converter;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.common.ProgramUtils;
import cz.vhromada.catalog.web.program.mo.ProgramMO;
import cz.vhromada.converter.Converter;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * A class represents test for converter from {@link ProgramMO} to {@link Program}.
 *
 * @author Vladimir Hromada
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = ConverterTestConfiguration.class)
class ProgramConverterTest {

    /**
     * Instance of {@link Converter}
     */
    @Autowired
    private Converter converter;

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity.
     */
    @Test
    void convertProgramMO() {
        final ProgramMO programMO = ProgramUtils.getProgramMO();

        final Program program = converter.convert(programMO, Program.class);

        ProgramUtils.assertProgramDeepEquals(programMO, program);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from MO to entity with null MO for program.
     */
    @Test
    void convertProgramMO_NullProgramMO() {
        assertThat(converter.convert(null, Program.class)).isNull();
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO.
     */
    @Test
    void convertProgram() {
        final Program program = ProgramUtils.getProgram();

        final ProgramMO programMO = converter.convert(program, ProgramMO.class);

        ProgramUtils.assertProgramDeepEquals(programMO, program);
    }

    /**
     * Test method for {@link Converter#convert(Object, Class)} from entity to MO with null program.
     */
    @Test
    void convertProgram_NullProgram() {
        assertThat(converter.convert(null, ProgramMO.class)).isNull();
    }

}
