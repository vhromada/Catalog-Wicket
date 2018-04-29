package cz.vhromada.catalog.web.common;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import cz.vhromada.catalog.entity.Medium;
import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.show.mo.ShowMO;
import cz.vhromada.common.utils.CollectionUtils;

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
        show.setPicture(1);
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
        show.setPicture(1);
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
        assertSoftly(softly -> {
            softly.assertThat(expected).isNotNull();
            softly.assertThat(actual).isNotNull();
        });
        assertSoftly(softly -> {
            softly.assertThat(actual.getId()).isEqualTo(expected.getId());
            softly.assertThat(actual.getCzechName()).isEqualTo(expected.getCzechName());
            softly.assertThat(actual.getOriginalName()).isEqualTo(expected.getOriginalName());
            softly.assertThat(actual.getCsfd()).isEqualTo(expected.getCsfd());
            softly.assertThat(actual.getImdbCode()).isEqualTo(expected.getImdbCode());
            softly.assertThat(actual.getWikiEn()).isEqualTo(expected.getWikiEn());
            softly.assertThat(actual.getWikiCz()).isEqualTo(expected.getWikiCz());
            softly.assertThat(actual.getPicture()).isEqualTo(expected.getPicture());
            softly.assertThat(actual.getNote()).isEqualTo(expected.getNote());
            softly.assertThat(actual.getPosition()).isEqualTo(expected.getPosition());
            GenreUtils.assertGenresDeepEquals(expected.getGenres(), actual.getGenres());
        });
    }

}
