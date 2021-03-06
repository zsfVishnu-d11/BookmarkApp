package com.example.bookmarkmyshow.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookmarkmyshow.R;
import com.example.bookmarkmyshow.model.Data;

import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {


    private Context context;
    private List<Data> data;
    private MoviesAdapter horizontalAdapter;
    private RecyclerView.RecycledViewPool recycledViewPool;

    public HomeAdapter(Context context, List<Data> data) {
        this.context = context;
        this.data = data;
        recycledViewPool = new RecyclerView.RecycledViewPool();
    }

    @NonNull
    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View theView = LayoutInflater.from(context).inflate(R.layout.row_recycler, parent, false);


        return new HomeViewHolder(theView);    }

    @Override
    public void onBindViewHolder(@NonNull HomeAdapter.HomeViewHolder holder, int position) {

//        holder.textViewCategory.setText(data.get(position).getGenre());
//
//        horizontalAdapter = new MoviesAdapter(data.get(position).getList(), context);
//        holder.recyclerViewHorizontal.setAdapter(horizontalAdapter);
//
//        holder.recyclerViewHorizontal.setRecycledViewPool(recycledViewPool);


    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder {
        private RecyclerView recyclerViewHorizontal;
        private TextView textViewCategory;

        private LinearLayoutManager horizontalManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false);

        public HomeViewHolder(View itemView) {
            super(itemView);

            recyclerViewHorizontal = itemView.findViewById(R.id.home_recycler_view_horizontal);
            recyclerViewHorizontal.setHasFixedSize(true);
            recyclerViewHorizontal.setNestedScrollingEnabled(false);
            recyclerViewHorizontal.setLayoutManager(horizontalManager);
            recyclerViewHorizontal.setItemAnimator(new DefaultItemAnimator());

            textViewCategory = itemView.findViewById(R.id.tv_movie_category);


        }


    }


}
