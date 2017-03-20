package cz.vhromada.catalog.web.common;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import cz.vhromada.catalog.entity.Medium;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.utils.CollectionUtils;
import cz.vhromada.catalog.web.show.mo.ShowMO;

/**
 * A class represents utility class for shows.
 *
 * @author Vladimir Hromada
 */
public final class ShowUtils {

    /**
     * Creates a new instance of ShowUtils.
     */
    private ShowUtils() {
    }

    /**
     * Returns MO for show.
     *
     * @return MO for show
     */
    public static ShowMO getShowMO() {
        final Medium medium = new Medium();
        medium.setId(CatalogUtils.ID);
        medium.setNumber(CatalogUtils.NUMBER);
        medium.setLength(CatalogUtils.LENGTH);

        final ShowMO show = new ShowMO();
        show.setId(CatalogUtils.ID);
        show.setCzechName("czName");
        show.setOriginalName("origName");
        show.setCsfd("Csfd");
        show.setImdbCode(1000);
        show.setWikiEn(CatalogUtils.EN_WIKI);
        show.setWikiCz(CatalogUtils.CZ_WIKI);
        show.setPicture("Picture");
        show.setNote(CatalogUtils.NOTE);
        show.setPosition(CatalogUtils.POSITION);
        show.setGenres(CollectionUtils.newList(GenreUtils.getGenreMO()));

        return show;
    }

    /**
     * Returns show.
     *
     * @return show
     */
    public static Show getShow() {
        final Medium medium = new Medium();
        medium.setId(CatalogUtils.ID);
        medium.setNumber(CatalogUtils.NUMBER);
        medium.setLength(CatalogUtils.LENGTH);

        final Show show = new Show();
        show.setId(CatalogUtils.ID);
        show.setCzechName("czName");
        show.setOriginalName("origName");
        show.setCsfd("Csfd");
        show.setImdbCode(1000);
        show.setWikiEn(CatalogUtils.EN_WIKI);
        show.setWikiCz(CatalogUtils.CZ_WIKI);
        show.setPicture("Picture");
        show.setNote(CatalogUtils.NOTE);
        show.setPosition(CatalogUtils.POSITION);
        show.setGenres(CollectionUtils.newList(GenreUtils.getGenre()));

        return show;
    }

    /**
     * Asserts show deep equals.
     *
     * @param expected expected MO for show
     * @param actual   actual show
     */
    public static void assertShowDeepEquals(final ShowMO expected, final Show actual) {
        assertThat(actual, is(notNullValue()));
        assertThat(actual.getId(), is(expected.getId()));
        assertThat(actual.getCzechName(), is(expected.getCzechName()));
        assertThat(actual.getOriginalName(), is(expected.getOriginalName()));
        assertThat(actual.getCsfd(), is(expected.getCsfd()));
        assertThat(actual.getImdbCode(), is(expected.getImdbCode()));
        assertThat(actual.getWikiEn(), is(expected.getWikiEn()));
        assertThat(actual.getWikiCz(), is(expected.getWikiCz()));
        assertThat(actual.getPicture(), is(expected.getPicture()));
        assertThat(actual.getNote(), is(expected.getNote()));
        assertThat(actual.getPosition(), is(expected.getPosition()));
        GenreUtils.assertGenresDeepEquals(expected.getGenres(), actual.getGenres());
    }

}
