package com.instahello.assignment;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class SongsAdapter extends RecyclerView.Adapter<SongsAdapter.ViewHolder> {

    private Context mContext;
    private List<Song> songs;

    public SongsAdapter(Context mContext, List<Song> offers) {
        this.mContext = mContext;
        this.songs = offers;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);

        if(viewType == 1)
        view = layoutInflater.inflate(R.layout.song_layout, parent, false);
        else
            view = layoutInflater.inflate(R.layout.empty_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if(position == songs.size())return 0;
        else return 1;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if(position==songs.size())return;

        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return songs.size()+1;
    }

    public void addItems(List<Song> songs) {
       this.songs = songs;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(View itemView) {
            super(itemView);

        }

        @SuppressLint("SetTextI18n")
        public void bind(int position) {

            Song song = songs.get(position);

            TextView title =  itemView.findViewById(R.id.title);
            TextView description =  itemView.findViewById(R.id.description);

            title.setText(song.title);
            description.setText(song.description);

        }
    }
}
