package com.example.bookmarkmyshow.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.bookmarkmyshow.R;
import com.example.bookmarkmyshow.adapter.BookmarkAdapter;
import com.example.bookmarkmyshow.adapter.MoviesAdapter;
import com.example.bookmarkmyshow.bookmarks.BookmarkViewModel;
import com.example.bookmarkmyshow.bookmarks.IDeleteBookmark;
import com.example.bookmarkmyshow.bookmarks.IInsertBookmark;
import com.example.bookmarkmyshow.model.Movie;
import com.example.bookmarkmyshow.rest.ApiSearchResults;
import com.example.bookmarkmyshow.rest.INetworkCallback;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {


    public static final String IMAGE_URL_BASE_PATH="https://image.tmdb.org/t/p/original";
    public static final  String API_KEY = "42a67939ed1c4b8c106331b02b24cb54";

    private RecyclerView popRecyclerView, bookRecyclerView;
    private List<Movie> dataList;
    private List<Movie> bookList;
    private RecyclerView recyclerView,recyclerViewHome;
    private BookmarkAdapter bookAdapter;
    private MoviesAdapter searchAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private BookmarkViewModel bookmarkViewModel;
    private EditText searchQuery;
    private ImageView delBooks;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_bookmark);

        delBooks = findViewById(R.id.deleteBooks);
        searchQuery = findViewById(R.id.searchBar);
        //initialize the list
       dataList = new ArrayList<>();
       bookList = new ArrayList<>();


       bookAdapter = new BookmarkAdapter(this, new IDeleteBookmark() {
           @Override
           public void deleteBookmark(Movie bookmarkMovie) {
               bookmarkViewModel.delete(bookmarkMovie);
               Toast.makeText(getApplicationContext(),"Removing the bookmark for the movie"+bookmarkMovie.getTitle(),Toast.LENGTH_SHORT).show();
               bookAdapter.notifyDataSetChanged();
           }
       });

       layoutManager = new GridLayoutManager(this,3);
       LinearLayoutManager bookLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

       recyclerView = findViewById(R.id.rvSearch);
       bookRecyclerView = findViewById(R.id.rvBookmarks);
       bookRecyclerView.setAdapter(bookAdapter);
       bookRecyclerView.setLayoutManager(bookLayoutManager);

       searchApiHandler("init");

        bookmarkViewModel = new ViewModelProvider(this).get(BookmarkViewModel.class);
        bookmarkViewModel.getAllBookmarks().observe(this, new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> bookmarkMovies) {

                bookAdapter.setBookmarkData(bookmarkMovies);
            }
        });


        delBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bookmarkViewModel.deleteAllBookmarks();
            }
        });




//

//
//
//        adapterHome = new HomeAdapter(this,dataList);
//        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
//        recyclerViewHome = findViewById(R.id.rvMain);
//        recyclerViewHome.setHasFixedSize(true);
//        recyclerViewHome.setLayoutManager(layoutManager);
//        recyclerViewHome.setAdapter(adapterHome);
//
//
//        popRecyclerView = findViewById(R.id.rvMovie);
//        bookRecyclerView = findViewById(R.id.rvBookmarks);
//        popRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//        bookRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
//
//
//        bookmarkViewModel = new ViewModelProvider(this).get(BookmarkViewModel.class);
//
//
//
//        handleNetworkCall();
//        handlePopular();
//        handleNowPlaying();
//        handleBookmarks();


        searchQuery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                String searchText = s.toString();

                if (!(searchText == null))
                    searchApiHandler(searchText);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString();

                if (!(searchText == null))
                    searchApiHandler(searchText);
            }

            @Override
            public void afterTextChanged(Editable s) {
                String searchText = s.toString();

                if (!(searchText == null))
                    searchApiHandler(searchText);

            }
        });
    }

    private void searchApiHandler(String searchKey) {

        ApiSearchResults apiSearchResults = new ApiSearchResults(searchKey);
        apiSearchResults.connectAndGetApiData(new INetworkCallback() {
            @Override
            public void onSuccess(List data) {

                 searchAdapter = new MoviesAdapter(data, getApplicationContext(), new IInsertBookmark() {
                    @Override
                    public void addToBookmark(Movie bookMovie) {
                        bookmarkViewModel.insert(bookMovie);
                        bookAdapter.notifyDataSetChanged();
                    }
                });
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setAdapter(searchAdapter);
            }
            @Override
            public void onError(Exception e) {
            }
        });
    }

//    private void handleNowPlaying() {
//
//
//        ApiNowPlaying apiNowPlaying = new ApiNowPlaying();
//        apiNowPlaying.connectAndGetApiData(new INetworkCallback() {
//            @Override
//            public void onSuccess(List data) {
//                MoviesAdapter moviesAdapter = new MoviesAdapter(data,getApplicationContext());
//                Data sample = new Data(data,"Now Playing","movie");
//                dataList.add(sample);
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//                Log.d("NetworkCall","Error in network call");
//
//            }
//        });
//
//    }
//
//    private void handlePopular() {
//
//        ApiPopular apiPopular = new ApiPopular();
//        apiPopular.connectAndGetApiData(new INetworkCallback() {
//            @Override
//            public void onSuccess(List data) {
////                MoviesAdapter moviesAdapter = new MoviesAdapter(data,getApplicationContext());
//                Data sample = new Data(data,"Popular","movie");
//                dataList.add(sample);
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//                Log.d("NetworkCall","Error in network call");
//
//            }
//        });
//
//
//
//    }
//
//    private void handleNetworkCall() {
//
//        ApiHandler apiHandler = new ApiHandler();
//        apiHandler.connectAndGetApiData(new INetworkCallback() {
//            @Override
//            public void onSuccess(List data) {
//                MoviesAdapter moviesAdapter = new MoviesAdapter(data,getApplicationContext());
//                Data sample = new Data(data,"Top Rated","movie");
//                dataList.add(sample);
//
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onError(Exception e) {
//
//                Log.d("NetworkCall","Error in network call");
//
//            }
//        });
//
//
//
//
//    }
//
//    private void handleBookmarks() {
//
//        bookmarkDatabase = Room.databaseBuilder(getApplicationContext(), BookmarkDatabase.class, DATABASE_NAME)
//                .fallbackToDestructiveMigration().build();
//
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                List<BookmarkMovie> allBooks = bookmarkDatabase.bookDao().findAll();
////                BookmarkAdapter bookmarkAdapter = new BookmarkAdapter(allBooks,R.layout.card_bookmark,getApplicationContext());
//
//                List<Movie> bookMovies = new ArrayList<>();
//                for (int i = 0; i < allBooks.size(); i++) {
//
//                    Movie temp = new Movie();
//                    temp.setTitle(allBooks.get(i).getName());
//                    temp.setPosterPath(allBooks.get(i).getImageUrl());
//                    temp.setId(allBooks.get(i).getId());
//                    temp.setReleaseDate(allBooks.get(i).getReleaseDate());
//
//                    bookMovies.add(temp);
//
//                }
//
//
//                Data bookData = new Data(bookMovies,"Bookmarks","Movies");
//
////                bookRecyclerView.setAdapter(bookmarkAdapter);
////                Log.d("Database","ViewRegion");
////                Log.d("Database", String.valueOf(allBooks.size()));
//
//
//            }
//        }).start();
//        adapter.notifyDataSetChanged();
//
//
//
//
//
//    }


    // This method create an instance of Retrofit to
    // set the base url
//    public void connectAndGetApiData() { //TODO:Refractor the structure. Seperate class for the network calls. Have a
//        //generic callbacks
//
//        Log.d(TAG,"Checkpoint 1");
//        if (retrofit == null) {
//            Log.d(TAG,"Checkpoint 2");
//
//
//            retrofit = new Retrofit.Builder() //building our retrofit object
//                    .baseUrl(BASE_URL)
//                    .addConverterFactory(GsonConverterFactory.create()) //ConvertFactory will takes care
//                    .build();                           // of the parsing of data we're sending and also the responses we get
//            //GsonConvertFactory map our JSON data to the Movie class defined earlier
//            //TODO:what happens when we're not explicitely mentioning the HTTP client
//            Log.d(TAG,"Checkpoint 3");
//        }
//
//        Log.d(TAG,"Checkpoint 4");
//        //Service Call
//        MovieApiService movieApiService = retrofit.create(MovieApiService.class); // Retrofit takes care of the construction
//                                        // of our service interface by injecting the code necessary
//                                        // to make the request, based on our previous annotations.
//        Log.d(TAG,"Checkpoint 5");
//        Call<MovieResponse> call = movieApiService.getTopRatedMovies(API_KEY);
//        //this object makes calls to the api
//        Log.d(TAG,"Checkpoint 6");
//        call.enqueue(new Callback<MovieResponse>() { //what does enque do? -> Calls may be executed
//            @Override                                //synchronously with execute(), or asynchronously with enqueue
//            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
//                List<Movie> movies = response.body().getResults();
//                recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.card_inner_horizontal, getApplicationContext()));
//                Log.d(TAG, "Number of movies received: " + movies.size());
//            }
//
//            @Override
//            public void onFailure(Call<MovieResponse> call, Throwable throwable) {
//                Log.e(TAG, throwable.toString());
//            }
//        });
//    }
}
