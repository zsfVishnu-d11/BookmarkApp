package com.example.bookmarkmyshow.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookmarkmyshow.R;
import com.example.bookmarkmyshow.bookmarks.BookmarkMovie;
import com.example.bookmarkmyshow.bookmarks.IDeleteBookmark;
import com.example.bookmarkmyshow.model.Movie;

import java.util.ArrayList;
import java.util.List;

import static com.example.bookmarkmyshow.activity.MainActivity.IMAGE_URL_BASE_PATH;

public class BookmarkAdapter extends RecyclerView.Adapter<BookmarkAdapter.BookmarkViewHolder> {
    private List<Movie> movies;
    private Context context;
    private IDeleteBookmark iDeleteBookmark;
    public BookmarkAdapter(){}
    private Dialog dialog;

    public BookmarkAdapter(Context context, IDeleteBookmark iDeleteBookmark) {
        this.context = context;
        this.iDeleteBookmark = iDeleteBookmark;

    }

    //A view holder inner class where we get reference to the views in the layout using their ID
    public class BookmarkViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView releaseDate;
        ImageView movieImage;
        ImageView bookmarkHome;

        public BookmarkViewHolder(View v) {
            super(v);
            movieImage = v.findViewById(R.id.movieImg);
            movieTitle = v.findViewById(R.id.movieTitle);
            releaseDate = v.findViewById(R.id.releaseDate);
            bookmarkHome = v.findViewById(R.id.bookmarkHome);

        }

        void bind(final Movie movie){

            if (movie!=null){
                movieTitle.setText(movie.getTitle());
                releaseDate.setText(movie.getReleaseDate());
            }
        }
    }
    @Override
    public BookmarkAdapter.BookmarkViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_bookmark, parent, false);
        dialog = new Dialog(view.getContext());

        return new BookmarkViewHolder(view);
    }
    @Override
    public void onBindViewHolder(BookmarkViewHolder holder, final int position) {

        String image_url = IMAGE_URL_BASE_PATH + movies.get(position).getPosterPath();
        Glide.with(context)
                .load(image_url)
                .into(holder.movieImage);

        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showPopup(v,movies.get(position));
            }
        });
        holder.movieTitle.setText(movies.get(position).getTitle());
        holder.releaseDate.setText(movies.get(position).getReleaseDate());
        holder.bookmarkHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context,"Clicked item name is "+movies.get(position).getTitle(),Toast.LENGTH_SHORT).show();
                iDeleteBookmark.deleteBookmark(movies.get(position));

            }
        });


    }
    @Override
    public int getItemCount() {
        if (movies != null)
            return movies.size();
        else return 0;
    }


    public void setBookmarkData(List<Movie> newData) {
        this.movies = newData;
        notifyDataSetChanged();
    }
    public void showPopup(View v,Movie movie) {
        dialog.setContentView(R.layout.popup_details);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView popupPoster = dialog.findViewById(R.id.popupPoster);
        TextView popupTitle = dialog.findViewById(R.id.popupTitle);
        TextView popupRating = dialog.findViewById(R.id.popupRating);
        TextView popupDescription = dialog.findViewById(R.id.popupDescription);
        ImageView bookIcon = dialog.findViewById(R.id.bookIcon);


        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + movie.getBackdropPath())
                .into(popupPoster);


        popupTitle.setText(movie.getTitle());
        popupRating.setText(String.valueOf(movie.getPopularity()));
        popupDescription.setText(movie.getOverview());

        bookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iDeleteBookmark.deleteBookmark(movie);

                Glide.with(context)
                        .load(R.drawable.book_filled)
                        .into(bookIcon);
            }
        });

        dialog.show();
    }

}