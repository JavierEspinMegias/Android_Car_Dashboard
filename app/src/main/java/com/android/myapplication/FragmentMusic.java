package com.android.myapplication;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;


public class FragmentMusic extends Fragment {


    public MediaPlayer mediaPlayer;
    public Button play, next, stop;

    public HashMap res;
    public TextView song_t, song_a;



    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private OnFragmentInterfaceCom mListener;

    public FragmentMusic() {

    }

    public static FragmentMusic newInstance(String param1, String param2) {
        FragmentMusic fragment = new FragmentMusic();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,param1);
        args.putSerializable(ARG_PARAM2,param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_fragment_music, container, false);

        play = (Button) v.findViewById(R.id.but_play);
        stop = (Button) v.findViewById(R.id.but_stop);
        next = (Button) v.findViewById(R.id.but_next);
        song_a = (TextView)v.findViewById(R.id.song_a);
        song_t = (TextView)v.findViewById(R.id.song_t);

        if (getArguments() != null && getArguments().getString("song_name")!=null) {
            getSong();
        }


        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChange("playing_music", mediaPlayer );
                getSong();
            }
        });



        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChange("stop_music", mediaPlayer );
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onChange("next_song", mediaPlayer );
                getSong();
            }
        });

        return v;
    }

    public void getSong(){
        if (getArguments()!=null && getArguments().getString("song_name")!=null){
            String newSong = getArguments().getString("song_name");
            String artist = newSong.split("-")[0]+"";
            if (newSong.split("-").length > 1){
                String title = newSong.split("-")[1]+"";
                if (title.length()>25){
                    song_t.setText(title.substring(0,25));
                }else{
                    song_t.setText(title);
                }
            }else{
                song_t.setText("");
            }
            if (artist.length()>15){
                song_a.setText(artist.substring(0,15));
            }else{
                song_a.setText(artist);
            }
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInterfaceCom) {
            mListener = (OnFragmentInterfaceCom) context;
        } else {
            throw new RuntimeException(context.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void onChange(String info, Object data) {
        if (mListener != null) {
            mListener.onFragmentMessage(info, data);
        }
    }

}
