package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.program.mo.ProgramMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for programs.
 *
 * @author Vladimir Hromada
 */
@Mapper
public interface ProgramMapper {

    /**
     * Returns MO for program.
     *
     * @param source program
     * @return MO for program
     */
    ProgramMO map(Program source);

    /**
     * Returns program.
     *
     * @param source MO for program
     * @return program
     */
    Program mapBack(ProgramMO source);

}
