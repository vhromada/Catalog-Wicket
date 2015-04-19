package cz.vhromada.catalog.web.converters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A class represents test suite for package cz.vhromada.catalog.web.converters.
 *
 * @author Vladimir Hromada
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ GameTOToGameMOConverterTest.class, GameMOToGameTOConverterTest.class, ProgramTOToProgramMOConverterTest.class,
        ProgramMOToProgramTOConverterTest.class, GenreTOToGenreMOConverterTest.class, GenreMOToGenreTOConverterTest.class })
public class ConvertersSuite {
}
