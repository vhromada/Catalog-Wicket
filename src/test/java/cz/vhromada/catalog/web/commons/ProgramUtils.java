package cz.vhromada.catalog.web.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.programs.mo.ProgramMO;

/**
 * A class represents utility class for programs.
 *
 * @author Vladimir Hromada
 */
public final class ProgramUtils {

    /**
     * Creates a new instance of ProgramUtils.
     */
    private ProgramUtils() {
    }

    /**
     * Returns MO for program.
     *
     * @return MO for program
     */
    public static ProgramMO getProgramMO() {
        final ProgramMO program = new ProgramMO();
        program.setId(CatalogUtils.ID);
        program.setName(CatalogUtils.NAME);
        program.setWikiEn(CatalogUtils.EN_WIKI);
        program.setWikiCz(CatalogUtils.CZ_WIKI);
        program.setMediaCount(CatalogUtils.MEDIA);
        program.setCrack(true);
        program.setSerialKey(true);
        program.setOtherData("Other data");
        program.setNote(CatalogUtils.NOTE);
        program.setPosition(CatalogUtils.POSITION);

        return program;
    }

    /**
     * Returns program.
     *
     * @return program
     */
    public static Program getProgram() {
        final Program program = new Program();
        program.setId(CatalogUtils.ID);
        program.setName(CatalogUtils.NAME);
        program.setWikiEn(CatalogUtils.EN_WIKI);
        program.setWikiCz(CatalogUtils.CZ_WIKI);
        program.setMediaCount(CatalogUtils.MEDIA);
        program.setCrack(true);
        program.setSerialKey(true);
        program.setOtherData("Other data");
        program.setNote(CatalogUtils.NOTE);
        program.setPosition(CatalogUtils.POSITION);

        return program;
    }

    /**
     * Asserts program deep equals.
     *
     * @param expected expected MO for program
     * @param actual   actual program
     */
    public static void assertProgramDeepEquals(final ProgramMO expected, final Program actual) {
        assertNotNull(actual);
        assertNotNull(actual.getMediaCount());
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getWikiEn(), actual.getWikiEn());
        assertEquals(expected.getWikiCz(), actual.getWikiCz());
        assertEquals(expected.getMediaCount().intValue(), actual.getMediaCount());
        assertEquals(expected.getCrack(), actual.getCrack());
        assertEquals(expected.getSerialKey(), actual.getSerialKey());
        assertEquals(expected.getOtherData(), actual.getOtherData());
        assertEquals(expected.getNote(), actual.getNote());
        assertEquals(expected.getPosition(), actual.getPosition());
    }

}
