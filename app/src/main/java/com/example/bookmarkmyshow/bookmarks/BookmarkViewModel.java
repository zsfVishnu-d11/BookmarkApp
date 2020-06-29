package com.example.bookmarkmyshow.bookmarks;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.bookmarkmyshow.model.Movie;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BookmarkViewModel extends AndroidViewModel { //androidVm is a subclass of Vm . AVM gets the application in the constructor

    private BookmarkRepository bookmarkRepository;
    private LiveData<List<Movie>> allBookmarks;

    public BookmarkViewModel(@NonNull Application application) {
        super(application);

        bookmarkRepository = new BookmarkRepository(application);
        allBookmarks = bookmarkRepository.getAllBookmarks();
    }

    public void insert(Movie bookmarkMovie){
        bookmarkRepository.insert(bookmarkMovie);
    }

    public void update(Movie bookmarkMovie){
        bookmarkRepository.update(bookmarkMovie);
    }

    public void delete(Movie bookmarkMovie){
        bookmarkRepository.delete(bookmarkMovie);
    }

    public void deleteAllBookmarks(){
        bookmarkRepository.deleteAllBookmarks();
    }

    public LiveData<List<Movie>> getAllBookmarks(){
        return allBookmarks;
    }

}
