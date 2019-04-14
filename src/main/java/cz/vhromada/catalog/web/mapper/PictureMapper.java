package cz.vhromada.catalog.web.mapper;

import cz.vhromada.catalog.entity.Picture;
import cz.vhromada.catalog.web.picture.mo.PictureMO;

import org.mapstruct.Mapper;

/**
 * An interface represents mapper for pictures.
 *
 * @author Vladimir Hromada
 */
@Mapper
public interface PictureMapper {

    /**
     * Returns MO for picture.
     *
     * @param source picture
     * @return MO for picture
     */
    PictureMO map(Picture source);

    /**
     * Returns picture.
     *
     * @param source MO for picture
     * @return picture
     */
    Picture mapBack(PictureMO source);

}
