package cz.vhromada.catalog.web.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Program;
import cz.vhromada.catalog.web.program.mo.ProgramMO;

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
        assertThat(actual, is(notNullValue()));
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getName(), is(expected.getName()));
        assertThat(actual.getWikiEn(), is(expected.getWikiEn()));
        assertThat(actual.getWikiCz(), is(expected.getWikiCz()));
        assertThat(actual.getMediaCount(), is(expected.getMediaCount()));
        assertThat(actual.getCrack(), is(expected.getCrack()));
        assertThat(actual.getSerialKey(), is(expected.getSerialKey()));
        assertThat(actual.getOtherData(), is(expected.getOtherData()));
        assertThat(actual.getNote(), is(expected.getNote()));
        assertThat(actual.getPosition(), is(expected.getPosition()));
    }

}
