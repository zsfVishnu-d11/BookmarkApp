package com.example.bookmarkmyshow.adapter;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookmarkmyshow.R;
import com.example.bookmarkmyshow.bookmarks.IInsertBookmark;
import com.example.bookmarkmyshow.model.Movie;

import org.w3c.dom.Text;

import java.util.List;

import static com.example.bookmarkmyshow.activity.MainActivity.IMAGE_URL_BASE_PATH;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;
    public static final String IMAGE_URL_BASE_PATH = "https://image.tmdb.org/t/p/original";
    private IInsertBookmark iInsertBookmark;
    private Dialog dialog;


    public MoviesAdapter(List<Movie> movies, Context context, IInsertBookmark iInsertBookmark) {
        this.movies = movies;
        this.context = context;
        this.iInsertBookmark = iInsertBookmark;
    }

    //A view holder inner class where we get reference to the views in the layout using their ID
    public static class MovieViewHolder extends RecyclerView.ViewHolder {
        LinearLayout moviesLayout;
        TextView movieTitle;
        TextView releaseDate;
        ImageView movieImage;
        ImageView bookmarkHome;

        public MovieViewHolder(View v) {
            super(v);
            movieImage = v.findViewById(R.id.movieImg);
            movieTitle = v.findViewById(R.id.movieTitle);
            releaseDate = v.findViewById(R.id.releaseDate);
            bookmarkHome = v.findViewById(R.id.bookmarkHome);

        }
    }

    @Override
    public MoviesAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent,
                                                            int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_inner_horizontal, parent, false);
        dialog = new Dialog(view.getContext());
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, final int position) {
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
                Toast.makeText(context, "Clicked item name is" + movies.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                iInsertBookmark.addToBookmark(movies.get(position));
            }
        });



    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    public void showPopup(View v,Movie movie) {
        dialog.setContentView(R.layout.popup_details);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ImageView popupPoster = dialog.findViewById(R.id.popupPoster);
        TextView popupTitle = dialog.findViewById(R.id.popupTitle);
        TextView popupRating = dialog.findViewById(R.id.popupRating);
        TextView popupDescription = dialog.findViewById(R.id.popupDescription);
        ImageView bookIcon = dialog.findViewById(R.id.bookIcon);
//        TextView release = dialog.findViewById(R.id.releaseDate);

        Glide.with(context)
                .load(R.drawable.pin)
                .into(bookIcon);

        Glide.with(context)
                .load(IMAGE_URL_BASE_PATH + movie.getBackdropPath())
                .into(popupPoster);


        popupTitle.setText(movie.getTitle());
        popupRating.setText(String.valueOf(movie.getPopularity()));
        popupDescription.setText(movie.getOverview());
//        release.setText(movie.getReleaseDate());

        bookIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iInsertBookmark.addToBookmark(movie);

                Glide.with(context)
                        .load(R.drawable.book)
                        .into(bookIcon);
            }
        });

        dialog.show();
    }

}