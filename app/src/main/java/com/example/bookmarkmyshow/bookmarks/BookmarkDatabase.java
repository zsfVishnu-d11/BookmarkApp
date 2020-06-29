package com.example.bookmarkmyshow.bookmarks;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.bookmarkmyshow.model.Movie;

@Database(entities = {Movie.class},exportSchema = false,version = 1)
public abstract class BookmarkDatabase extends RoomDatabase {

    private static BookmarkDatabase bookInstance; //creating into singleton -> only one instance
    public abstract BookmarkDao bookDao(); //for accessing our DAO

//    private static final Object sLock = new Object();

    public static synchronized  BookmarkDatabase getInstance(Context context) { //Synch means only one thread can access this method
                                                                                //at a time
//        synchronized (sLock) {
            if (bookInstance == null) {
                bookInstance = Room.databaseBuilder(context.getApplicationContext(), //builder -> coz its an abstract class
                        BookmarkDatabase.class, "tmdbBookmarks.db")
                        .fallbackToDestructiveMigration() // TODO:database migration scripts
                        // version number related -> crashing due to illegal migration error
                        .build();
            }
            return bookInstance;
//        }
    }
}
