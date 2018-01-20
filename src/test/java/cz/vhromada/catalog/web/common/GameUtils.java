package cz.vhromada.catalog.web.common;

import static org.assertj.core.api.SoftAssertions.assertSoftly;

import cz.vhromada.catalog.entity.Game;
import cz.vhromada.catalog.web.game.mo.GameMO;

/**
 * A class represents utility class for games.
 *
 * @author Vladimir Hromada
 */
public final class GameUtils {

    /**
     * Creates a new instance of GameUtils.
     */
    private GameUtils() {
    }

    /**
     * Returns MO for game.
     *
     * @return MO for game
     */
    public static GameMO getGameMO() {
        final GameMO game = new GameMO();
        game.setId(CatalogUtils.ID);
        game.setName(CatalogUtils.NAME);
        game.setWikiEn(CatalogUtils.EN_WIKI);
        game.setWikiCz(CatalogUtils.CZ_WIKI);
        game.setMediaCount(CatalogUtils.MEDIA);
        game.setCrack(true);
        game.setSerialKey(true);
        game.setPatch(true);
        game.setTrainer(true);
        game.setTrainerData(true);
        game.setEditor(true);
        game.setSaves(true);
        game.setOtherData("Other data");
        game.setNote(CatalogUtils.NOTE);
        game.setPosition(CatalogUtils.POSITION);

        return game;
    }

    /**
     * Returns game.
     *
     * @return game
     */
    public static Game getGame() {
        final Game game = new Game();
        game.setId(CatalogUtils.ID);
        game.setName(CatalogUtils.NAME);
        game.setWikiEn(CatalogUtils.EN_WIKI);
        game.setWikiCz(CatalogUtils.CZ_WIKI);
        game.setMediaCount(CatalogUtils.MEDIA);
        game.setCrack(true);
        game.setSerialKey(true);
        game.setPatch(true);
        game.setTrainer(true);
        game.setTrainerData(true);
        game.setEditor(true);
        game.setSaves(true);
        game.setOtherData("Other data");
        game.setNote(CatalogUtils.NOTE);
        game.setPosition(CatalogUtils.POSITION);

        return game;
    }

    /**
     * Asserts game deep equals.
     *
     * @param expected expected MO for game
     * @param actual   actual game
     */
    public static void assertGameDeepEquals(final GameMO expected, final Game actual) {
        assertSoftly(softly -> {
            softly.assertThat(expected).isNotNull();
            softly.assertThat(actual).isNotNull();
        });
        assertSoftly(softly -> {
            softly.assertThat(actual.getId()).isEqualTo(expected.getId());
            softly.assertThat(actual.getName()).isEqualTo(expected.getName());
            softly.assertThat(actual.getWikiEn()).isEqualTo(expected.getWikiEn());
            softly.assertThat(actual.getWikiCz()).isEqualTo(expected.getWikiCz());
            softly.assertThat(actual.getMediaCount()).isEqualTo(expected.getMediaCount());
            softly.assertThat(actual.getCrack()).isEqualTo(expected.getCrack());
            softly.assertThat(actual.getSerialKey()).isEqualTo(expected.getSerialKey());
            softly.assertThat(actual.getPatch()).isEqualTo(expected.getPatch());
            softly.assertThat(actual.getTrainer()).isEqualTo(expected.getTrainer());
            softly.assertThat(actual.getTrainerData()).isEqualTo(expected.getTrainerData());
            softly.assertThat(actual.getEditor()).isEqualTo(expected.getEditor());
            softly.assertThat(actual.getSaves()).isEqualTo(expected.getSaves());
            softly.assertThat(actual.getOtherData()).isEqualTo(expected.getOtherData());
            softly.assertThat(actual.getNote()).isEqualTo(expected.getNote());
            softly.assertThat(actual.getPosition()).isEqualTo(expected.getPosition());
        });
    }

}
