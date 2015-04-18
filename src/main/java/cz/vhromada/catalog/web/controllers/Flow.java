package cz.vhromada.catalog.web.controllers;

/**
 * An enumeration represents flow.
 *
 * @author Vladimir Hromada
 */
public enum Flow {

    /**
     * Flow for home
     */
    HOME,

    /**
     * Flow for new games
     */
    GAMES_NEW_DATA,

    /**
     * Flow for list of games
     */
    GAMES_LIST,

    /**
     * Flow for form for add game
     */
    GAMES_ADD,

    /**
     * Flow for adding game
     */
    GAMES_ADD_CONFIRM,

    /**
     * Flow for form for update game
     */
    GAMES_UPDATE,

    /**
     * Flow for updating game
     */
    GAMES_UPDATE_CONFIRM,

    /**
     * Flow for removing game
     */
    GAMES_REMOVE,

    /**
     * Flow for duplicating game
     */
    GAMES_DUPLICATE,

    /**
     * Flow for moving game up
     */
    GAMES_MOVE_UP,

    /**
     * Flow for moving game down
     */
    GAMES_MOVE_DOWN,

    /**
     * Flow for updating position for games
     */
    GAMES_UPDATE_POSITION,

    /**
     * Flow for cancel updating game
     */
    GAMES_CANCEL

}
