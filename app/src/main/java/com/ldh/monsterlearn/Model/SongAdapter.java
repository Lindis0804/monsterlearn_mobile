package com.ldh.monsterlearn.Model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ldh.monsterlearn.R;

import java.util.List;

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ItemViewHolder>{
    List<Song> songs;
    private SongClickListener songClickListener;

    public SongAdapter(List<Song> songs, SongClickListener songClickListener) {
        this.songs = songs;
        this.songClickListener = songClickListener;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
          Song song = songs.get(position);
          holder.imageView.setImageResource(song.getPicture());
          holder.textView.setText(song.getName());
    }

    @Override
    public int getItemCount() {
        return songs.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView textView;
        private SongClickListener _songClickListener;
        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.song_item_imageView);
            textView = itemView.findViewById(R.id.song_item_textView);
            _songClickListener = songClickListener;
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (_songClickListener!=null)
                    _songClickListener.onSongClickListener(getAdapterPosition());
                }
            });
        }
    }
}
