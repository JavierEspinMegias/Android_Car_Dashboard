package com.android.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class SongListAdapter extends RecyclerView.Adapter<SongListAdapter.ViewHolder>{

    private ArrayList<HashMap<String, String>> songs;
    public OnFragmentInterfaceCom mListener;

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView name, index;

        public ViewHolder(final View itemView) {
            super(itemView);
            this.index = itemView.findViewById(R.id.song_index);
            this.name = itemView.findViewById(R.id.song_title);

        }
    }


    public SongListAdapter(ArrayList<HashMap<String, String>> songslist){
        this.songs = songslist;
    }

    @Override
    public SongListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.watch_songs, parent, false);
        ViewHolder viewHolder = new ViewHolder(contactView);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final SongListAdapter.ViewHolder viewHolder, final int position) {
        final HashMap<String, String> song = songs.get(position);
        viewHolder.index.setText((position+1)+"");
        viewHolder.name.setText(song.get("file_name").split(".mp3")[0]);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onChange("specific_song", viewHolder.name.getText().toString());
                viewHolder.itemView.setBackgroundColor(viewHolder.itemView.getContext().getResources().getColor(R.color.contrastBg));
            }
        });

    }

    public void onChange(String info, Object data) {
        if (mListener != null) {
            mListener.onFragmentMessage(info, data);
        }
    }


    @Override
    public int getItemCount() {
        return this.songs.size();
    }

}
