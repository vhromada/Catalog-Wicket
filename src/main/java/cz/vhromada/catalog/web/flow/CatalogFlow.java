package cz.vhromada.catalog.web.flow;

import cz.vhromada.web.wicket.controller.Flow;

/**
 * An enumeration represents flow.
 *
 * @author Vladimir Hromada
 */
public enum CatalogFlow implements Flow {

    /**
     * Flow for home
     */
    HOME,

    /**
     * Flow for new movies
     */
    MOVIES_NEW_DATA,

    /**
     * Flow for list of movies
     */
    MOVIES_LIST,

    /**
     * Flow for detail of movie
     */
    MOVIES_DETAIL,

    /**
     * Flow for form for add movie
     */
    MOVIES_ADD,

    /**
     * Flow for adding movie
     */
    MOVIES_ADD_CONFIRM,

    /**
     * Flow for form for update movie
     */
    MOVIES_UPDATE,

    /**
     * Flow for updating movie
     */
    MOVIES_UPDATE_CONFIRM,

    /**
     * Flow for removing movie
     */
    MOVIES_REMOVE,

    /**
     * Flow for duplicating movie
     */
    MOVIES_DUPLICATE,

    /**
     * Flow for moving movie up
     */
    MOVIES_MOVE_UP,

    /**
     * Flow for moving movie down
     */
    MOVIES_MOVE_DOWN,

    /**
     * Flow for updating position for movies
     */
    MOVIES_UPDATE_POSITION,

    /**
     * Flow for cancel updating movie
     */
    MOVIES_CANCEL,

    /**
     * Flow for new shows
     */
    SHOWS_NEW_DATA,

    /**
     * Flow for list of shows
     */
    SHOWS_LIST,

    /**
     * Flow for detail of show
     */
    SHOWS_DETAIL,

    /**
     * Flow for form for add show
     */
    SHOWS_ADD,

    /**
     * Flow for adding show
     */
    SHOWS_ADD_CONFIRM,

    /**
     * Flow for form for update show
     */
    SHOWS_UPDATE,

    /**
     * Flow for updating show
     */
    SHOWS_UPDATE_CONFIRM,

    /**
     * Flow for removing show
     */
    SHOWS_REMOVE,

    /**
     * Flow for duplicating show
     */
    SHOWS_DUPLICATE,

    /**
     * Flow for moving show up
     */
    SHOWS_MOVE_UP,

    /**
     * Flow for moving show down
     */
    SHOWS_MOVE_DOWN,

    /**
     * Flow for updating position for shows
     */
    SHOWS_UPDATE_POSITION,

    /**
     * Flow for cancel updating show
     */
    SHOWS_CANCEL,

    /**
     * Flow for showing show seasons
     */
    SHOWS_SEASONS,

    /**
     * Flow for list of seasons
     */
    SEASONS_LIST,

    /**
     * Flow for detail of season
     */
    SEASONS_DETAIL,

    /**
     * Flow for form for add season
     */
    SEASONS_ADD,

    /**
     * Flow for adding season
     */
    SEASONS_ADD_CONFIRM,

    /**
     * Flow for form for update season
     */
    SEASONS_UPDATE,

    /**
     * Flow for updating season
     */
    SEASONS_UPDATE_CONFIRM,

    /**
     * Flow for removing season
     */
    SEASONS_REMOVE,

    /**
     * Flow for duplicating season
     */
    SEASONS_DUPLICATE,

    /**
     * Flow for moving season up
     */
    SEASONS_MOVE_UP,

    /**
     * Flow for moving season down
     */
    SEASONS_MOVE_DOWN,

    /**
     * Flow for cancel updating season
     */
    SEASONS_CANCEL,

    /**
     * Flow for showing seasons episodes
     */
    SEASONS_EPISODES,

    /**
     * Flow for list of episodes
     */
    EPISODES_LIST,

    /**
     * Flow for detail of episode
     */
    EPISODES_DETAIL,

    /**
     * Flow for form for add episode
     */
    EPISODES_ADD,

    /**
     * Flow for adding episode
     */
    EPISODES_ADD_CONFIRM,

    /**
     * Flow for form for update episode
     */
    EPISODES_UPDATE,

    /**
     * Flow for updating episode
     */
    EPISODES_UPDATE_CONFIRM,

    /**
     * Flow for removing episode
     */
    EPISODES_REMOVE,

    /**
     * Flow for duplicating episode
     */
    EPISODES_DUPLICATE,

    /**
     * Flow for moving episode up
     */
    EPISODES_MOVE_UP,

    /**
     * Flow for moving episode down
     */
    EPISODES_MOVE_DOWN,

    /**
     * Flow for cancel updating episode
     */
    EPISODES_CANCEL,

    /**
     * Flow for new games
     */
    GAMES_NEW_DATA,

    /**
     * Flow for list of games
     */
    GAMES_LIST,

    /**
     * Flow for detail of game
     */
    GAMES_DETAIL,

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
    GAMES_CANCEL,

    /**
     * Flow for new music
     */
    MUSIC_NEW_DATA,

    /**
     * Flow for list of music
     */
    MUSIC_LIST,

    /**
     * Flow for detail of music
     */
    MUSIC_DETAIL,

    /**
     * Flow for form for add music
     */
    MUSIC_ADD,

    /**
     * Flow for adding music
     */
    MUSIC_ADD_CONFIRM,

    /**
     * Flow for form for update music
     */
    MUSIC_UPDATE,

    /**
     * Flow for updating music
     */
    MUSIC_UPDATE_CONFIRM,

    /**
     * Flow for removing music
     */
    MUSIC_REMOVE,

    /**
     * Flow for duplicating music
     */
    MUSIC_DUPLICATE,

    /**
     * Flow for moving music up
     */
    MUSIC_MOVE_UP,

    /**
     * Flow for moving music down
     */
    MUSIC_MOVE_DOWN,

    /**
     * Flow for updating position for music
     */
    MUSIC_UPDATE_POSITION,

    /**
     * Flow for cancel updating music
     */
    MUSIC_CANCEL,

    /**
     * Flow for showing music songs
     */
    MUSIC_SONGS,

    /**
     * Flow for list of songs
     */
    SONGS_LIST,

    /**
     * Flow for detail of songs
     */
    SONGS_DETAIL,

    /**
     * Flow for form for add song
     */
    SONGS_ADD,

    /**
     * Flow for adding song
     */
    SONGS_ADD_CONFIRM,

    /**
     * Flow for form for update song
     */
    SONGS_UPDATE,

    /**
     * Flow for updating song
     */
    SONGS_UPDATE_CONFIRM,

    /**
     * Flow for removing song
     */
    SONGS_REMOVE,

    /**
     * Flow for duplicating song
     */
    SONGS_DUPLICATE,

    /**
     * Flow for moving song up
     */
    SONGS_MOVE_UP,

    /**
     * Flow for moving song down
     */
    SONGS_MOVE_DOWN,

    /**
     * Flow for cancel updating song
     */
    SONGS_CANCEL,

    /**
     * Flow for new programs
     */
    PROGRAMS_NEW_DATA,

    /**
     * Flow for list of programs
     */
    PROGRAMS_LIST,

    /**
     * Flow for detail of programs
     */
    PROGRAMS_DETAIL,

    /**
     * Flow for form for add program
     */
    PROGRAMS_ADD,

    /**
     * Flow for adding program
     */
    PROGRAMS_ADD_CONFIRM,

    /**
     * Flow for form for update program
     */
    PROGRAMS_UPDATE,

    /**
     * Flow for updating program
     */
    PROGRAMS_UPDATE_CONFIRM,

    /**
     * Flow for removing program
     */
    PROGRAMS_REMOVE,

    /**
     * Flow for duplicating program
     */
    PROGRAMS_DUPLICATE,

    /**
     * Flow for moving program up
     */
    PROGRAMS_MOVE_UP,

    /**
     * Flow for moving program down
     */
    PROGRAMS_MOVE_DOWN,

    /**
     * Flow for updating position for programs
     */
    PROGRAMS_UPDATE_POSITION,

    /**
     * Flow for cancel updating program
     */
    PROGRAMS_CANCEL,

    /**
     * Flow for new genres
     */
    GENRES_NEW_DATA,

    /**
     * Flow for list of genres
     */
    GENRES_LIST,

    /**
     * Flow for form for add genre
     */
    GENRES_ADD,

    /**
     * Flow for adding genre
     */
    GENRES_ADD_CONFIRM,

    /**
     * Flow for form for update genre
     */
    GENRES_UPDATE,

    /**
     * Flow for updating genre
     */
    GENRES_UPDATE_CONFIRM,

    /**
     * Flow for removing genre
     */
    GENRES_REMOVE,

    /**
     * Flow for duplicating genre
     */
    GENRES_DUPLICATE,

    /**
     * Flow for moving genre up
     */
    GENRES_MOVE_UP,

    /**
     * Flow for moving genre down
     */
    GENRES_MOVE_DOWN,

    /**
     * Flow for updating position for genres
     */
    GENRES_UPDATE_POSITION,

    /**
     * Flow for cancel updating genre
     */
    GENRES_CANCEL,

    /**
     * Flow for new pictures
     */
    PICTURES_NEW_DATA,

    /**
     * Flow for list of pictures
     */
    PICTURES_LIST,

    /**
     * Flow for form for add picture
     */
    PICTURES_ADD,

    /**
     * Flow for adding picture
     */
    PICTURES_ADD_CONFIRM,

    /**
     * Flow for removing picture
     */
    PICTURES_REMOVE,

    /**
     * Flow for moving picture up
     */
    PICTURES_MOVE_UP,

    /**
     * Flow for moving picture down
     */
    PICTURES_MOVE_DOWN,

    /**
     * Flow for updating position for pictures
     */
    PICTURES_UPDATE_POSITION,

    /**
     * Flow for cancel updating picture
     */
    PICTURES_CANCEL,

    /**
     * Flow for errors
     */
    ERROR;

    @Override
    public String getFlow() {
        return name();
    }

}
