package cz.vhromada.catalog.web.converters;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * A class represents test suite for package cz.vhromada.catalog.web.converters.
 *
 * @author Vladimir Hromada
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ ShowTOToShowMOConverterTest.class, ShowMOToShowTOConverterTest.class, GameTOToGameMOConverterTest.class,
        GameMOToGameTOConverterTest.class, MusicTOToMusicMOConverterTest.class, MusicMOToMusicTOConverterTest.class, SongTOToSongMOConverterTest.class,
        SongMOToSongTOConverterTest.class, ProgramTOToProgramMOConverterTest.class, ProgramMOToProgramTOConverterTest.class,
        BookCategoryTOToBookCategoryMOConverterTest.class, BookCategoryMOToBookCategoryTOConverterTest.class, BookTOToBookMOConverterTest.class,
        BookMOToBookTOConverterTest.class, GenreTOToGenreMOConverterTest.class, GenreMOToGenreTOConverterTest.class, IntegerToTimeMOConverterTest.class,
        TimeMOToIntegerConverterTest.class })
public class ConvertersSuite {
}
