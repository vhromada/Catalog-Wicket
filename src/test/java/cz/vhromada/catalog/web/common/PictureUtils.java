package cz.vhromada.catalog.web.common;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.web.picture.mo.PictureMO;

/**
 * A class represents utility class for pictures.
 *
 * @author Vladimir Hromada
 */
public final class PictureUtils {

    /**
     * Creates a new instance of PictureUtils.
     */
    private PictureUtils() {
    }

    /**
     * Returns MO for picture.
     *
     * @return MO for picture
     */
    public static PictureMO getPictureMO() {
        final PictureMO picture = new PictureMO();
        picture.setId(CatalogUtils.ID);
        picture.setContent("Data".getBytes());
        picture.setPosition(CatalogUtils.POSITION);

        return picture;
    }

    /**
     * Returns picture.
     *
     * @return picture
     */
    public static Picture getPicture() {
        final Picture picture = new Picture();
        picture.setId(CatalogUtils.ID);
        picture.setContent("Data".getBytes());
        picture.setPosition(CatalogUtils.POSITION);

        return picture;
    }

    /**
     * Asserts picture deep equals.
     *
     * @param expected expected MO for picture
     * @param actual   actual picture
     */
    public static void assertPictureDeepEquals(final PictureMO expected, final Picture actual) {
        assertSoftly(softly -> {
            softly.assertThat(expected).isNotNull();
            softly.assertThat(actual).isNotNull();
        });
        assertSoftly(softly -> {
            softly.assertThat(actual.getId()).isEqualTo(expected.getId());
            softly.assertThat(actual.getContent()).isEqualTo(expected.getContent());
            softly.assertThat(actual.getPosition()).isEqualTo(expected.getPosition());
        });
    }

}
