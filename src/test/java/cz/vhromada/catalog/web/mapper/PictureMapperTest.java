package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.web.common.PictureUtils;
import cz.vhromada.catalog.web.picture.mo.PictureMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Picture} and {@link PictureMO}.
 *
 * @author Vladimir Hromada
 */
class PictureMapperTest {

    /**
     * Mapper for pictures
     */
    private PictureMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(PictureMapper.class);
    }

    /**
     * Test method for {@link PictureMapper#map(Picture)}.
     */
    @Test
    void map() {
        final Picture picture = PictureUtils.getPicture();

        final PictureMO pictureMO = mapper.map(picture);

        PictureUtils.assertPictureDeepEquals(pictureMO, picture);
    }

    /**
     * Test method for {@link PictureMapper#map(Picture)} with null picture.
     */
    @Test
    void map_NullPicture() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link PictureMapper#mapBack(PictureMO)}.
     */
    @Test
    void mapBack() {
        final PictureMO pictureMO = PictureUtils.getPictureMO();

        final Picture picture = mapper.mapBack(pictureMO);

        PictureUtils.assertPictureDeepEquals(pictureMO, picture);
    }

    /**
     * Test method for {@link PictureMapper#mapBack(PictureMO)} with null picture.
     */
    @Test
    void mapBack_NullPicture() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
