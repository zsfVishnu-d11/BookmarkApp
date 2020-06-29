package com.example.bookmarkmyshow.bookmarks;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.bookmarkmyshow.model.Movie;

import java.util.List;

public class BookmarkRepository {

    private BookmarkDao bookmarkDao;
    private LiveData<List<Movie>> allBookmarks;

    public BookmarkRepository(Application application){    //Application extends context

        BookmarkDatabase bookmarkDatabase = BookmarkDatabase.getInstance(application);
        bookmarkDao = bookmarkDatabase.bookDao();
        allBookmarks = bookmarkDao.getAllBookmarks();
    }

    public void insert(Movie bookmarkMovie){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    bookmarkDao.save(bookmarkMovie);
                }
                catch (Exception e){

                    Log.d("DB","Insert exception");
                }

            }
        }).start();

    }

    public void update(Movie bookmarkMovie){
        new Thread(new Runnable() {
            @Override
            public void run() {
                bookmarkDao.update(bookmarkMovie);
            }
        }).start();

    }

    public void delete(Movie bookmarkMovie){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    bookmarkDao.delete(bookmarkMovie);
                }
                catch (Exception e){
                    Log.d("DB","Deletion exception");
                }

            }
        }).start();

    }

    public void deleteAllBookmarks(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try
                {
                    bookmarkDao.deleteAllBookmarks();
                }
                catch (Exception e){
                    Log.d("DB","Delete all exception");
                }

            }
        }).start();

    }

    public LiveData<List<Movie>> getAllBookmarks(){
        return allBookmarks;
    }

}
