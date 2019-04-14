package cz.vhromada.catalog.web.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import cz.vhromada.catalog.entity.Show;
import cz.vhromada.catalog.web.common.ShowUtils;
import cz.vhromada.catalog.web.show.mo.ShowMO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

/**
 * A class represents test for mapper between {@link Show} and {@link ShowMO}.
 *
 * @author Vladimir Hromada
 */
class ShowMapperTest {

    /**
     * Mapper for shows
     */
    private ShowMapper mapper;

    /**
     * Initializes mapper.
     */
    @BeforeEach
    void setUp() {
        mapper = Mappers.getMapper(ShowMapper.class);
    }

    /**
     * Test method for {@link ShowMapper#map(Show)}.
     */
    @Test
    void map() {
        final Show show = ShowUtils.getShow();

        final ShowMO showMO = mapper.map(show);

        ShowUtils.assertShowDeepEquals(showMO, show);
    }

    /**
     * Test method for {@link ShowMapper#map(Show)} with null show.
     */
    @Test
    void map_NullShow() {
        assertThat(mapper.map(null)).isNull();
    }

    /**
     * Test method for {@link ShowMapper#mapBack(ShowMO)}.
     */
    @Test
    void mapBack() {
        final ShowMO showMO = ShowUtils.getShowMO();

        final Show show = mapper.mapBack(showMO);

        ShowUtils.assertShowDeepEquals(showMO, show);
    }

    /**
     * Test method for {@link ShowMapper#mapBack(ShowMO)} with null show.
     */
    @Test
    void mapBack_NullShow() {
        assertThat(mapper.mapBack(null)).isNull();
    }

}
