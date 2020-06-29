package com.example.bookmarkmyshow.bookmarks;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.bookmarkmyshow.model.Movie;

import java.util.List;

@Dao
public interface BookmarkDao {

    @Insert
    void save(Movie bookMovie);

    @Update
    void update(Movie bookMovie);

    @Delete
    void delete(Movie bookMovie);

    @Query("DELETE FROM TMDBBOOKMARKS")
    void deleteAllBookmarks();

    @Query("SELECT * FROM tmdbBookmarks")
    LiveData<List<Movie>> getAllBookmarks();


}
