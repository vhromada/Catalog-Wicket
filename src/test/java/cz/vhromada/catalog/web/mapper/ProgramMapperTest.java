package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.common.ProgramUtils;
import cz.vhromada.catalog.web.program.mo.ProgramMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Program} and {@link ProgramMO}.
 *
 * @author Vladimir Hromada
 */
class ProgramMapperTest {

    /**
     * Mapper for programs
     */
    private ProgramMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ProgramMapper.class);
    }

    /**
     * Test method for {@link ProgramMapper#map(Program)}.
     */
    @Test
    void map() {
        final Program program = ProgramUtils.getProgram();

        final ProgramMO programMO = mapper.map(program);

        ProgramUtils.assertProgramDeepEquals(programMO, program);
    }

    /**
     * Test method for {@link ProgramMapper#map(Program)} with null program.
     */
    @Test
    void map_NullProgram() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link ProgramMapper#mapBack(ProgramMO)}.
     */
    @Test
    void mapBack() {
        final ProgramMO programMO = ProgramUtils.getProgramMO();

        final Program program = mapper.mapBack(programMO);

        ProgramUtils.assertProgramDeepEquals(programMO, program);
    }

    /**
     * Test method for {@link ProgramMapper#mapBack(ProgramMO)} with null program.
     */
    @Test
    void mapBack_NullProgram() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
