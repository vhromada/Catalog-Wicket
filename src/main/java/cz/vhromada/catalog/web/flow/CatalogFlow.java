package cz.vhromada.catalog.web.flow;

import cz.vhromada.web.wicket.controllers.Flow;

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
     * Flow for showing show seasons
     */
    SHOWS_SEASONS,

    /**
     * Flow for cancel updating show
     */
    SHOWS_CANCEL,

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
     * Flow for new book categories
     */
    BOOK_CATEGORIES_NEW_DATA,

    /**
     * Flow for list of book categories
     */
    BOOK_CATEGORIES_LIST,

    /**
     * Flow for form for add book category
     */
    BOOK_CATEGORIES_ADD,

    /**
     * Flow for adding book category
     */
    BOOK_CATEGORIES_ADD_CONFIRM,

    /**
     * Flow for form for update book category
     */
    BOOK_CATEGORIES_UPDATE,

    /**
     * Flow for updating book category
     */
    BOOK_CATEGORIES_UPDATE_CONFIRM,

    /**
     * Flow for removing book category
     */
    BOOK_CATEGORIES_REMOVE,

    /**
     * Flow for duplicating book category
     */
    BOOK_CATEGORIES_DUPLICATE,

    /**
     * Flow for moving book category up
     */
    BOOK_CATEGORIES_MOVE_UP,

    /**
     * Flow for moving book category down
     */
    BOOK_CATEGORIES_MOVE_DOWN,

    /**
     * Flow for updating position for book categories
     */
    BOOK_CATEGORIES_UPDATE_POSITION,

    /**
     * Flow for cancel updating book category
     */
    BOOK_CATEGORIES_CANCEL,

    /**
     * Flow for showing book category books
     */
    BOOK_CATEGORIES_BOOKS,

    /**
     * Flow for list of books
     */
    BOOKS_LIST,

    /**
     * Flow for form for add book
     */
    BOOKS_ADD,

    /**
     * Flow for adding book
     */
    BOOKS_ADD_CONFIRM,

    /**
     * Flow for form for update book
     */
    BOOKS_UPDATE,

    /**
     * Flow for updating book
     */
    BOOKS_UPDATE_CONFIRM,

    /**
     * Flow for removing book
     */
    BOOKS_REMOVE,

    /**
     * Flow for duplicating book
     */
    BOOKS_DUPLICATE,

    /**
     * Flow for moving book up
     */
    BOOKS_MOVE_UP,

    /**
     * Flow for moving book down
     */
    BOOKS_MOVE_DOWN,

    /**
     * Flow for cancel updating book
     */
    BOOKS_CANCEL,

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
     * Flow for cancel updating genre
     */
    GENRES_CANCEL;

    @Override
    public String getFlow() {
        return name();
    }

}
