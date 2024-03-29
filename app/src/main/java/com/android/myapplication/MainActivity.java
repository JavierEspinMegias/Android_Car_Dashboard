package com.android.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements OnFragmentInterfaceCom{

    private CustomBottomNavigationView menuBottom;
    private BottomNavigationItemView invi;

    public ArrayList<HashMap<String,String>> songList;

    public FragmentMusic frag_music;

    public MediaPlayer carMediaPlayer = new MediaPlayer();
    public HashMap res;
    public int music_index;
    public boolean isPlaying;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        checkDayNight();
        setContentView(R.layout.activity_main);

        if(getIntent().getSerializableExtra("songList") != null){
            this.songList = (ArrayList<HashMap<String, String>>) getIntent().getSerializableExtra("songList");
        }

        isPlaying = false;

        loadMenu();

        invi = (BottomNavigationItemView)findViewById(R.id.action_add);
        invi.setClickable(false);

    }


    //      SET FRAGMENTS ON MENU
    public void loadMenu(){
        menuBottom = (CustomBottomNavigationView)findViewById(R.id.customBottomBar);
        menuBottom.setOnNavigationItemSelectedListener(new CustomBottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_music:
                        frag_music = FragmentMusic.newInstance("", "");
                        Bundle bundAllSongs = new Bundle();
                        bundAllSongs.putSerializable("songs", songList);
                        frag_music.setArguments(bundAllSongs);
                        loadFragment(frag_music).addToBackStack("music").commit();
                        break;
                    case R.id.action_maps:
                        Fragment2 frag2 = Fragment2.newInstance("", "");
                        loadFragment(frag2).addToBackStack("maps").commit();
                        break;
                    case R.id.action_search:
                        Fragment4 frag4 = Fragment4.newInstance("", "");
                        loadFragment(frag4).addToBackStack("search").commit();
                        break;
                    case R.id.action_settings:
                        FragSettings frag5 = FragSettings.newInstance("", "");
                        loadFragment(frag5).addToBackStack("settings").commit();
                        break;
                }

                return true;
            }
        });
    }
    @Override
    public void onResume(){
        super.onResume();
    }


    public FragmentTransaction loadFragment(Fragment loadFrag){
        getSupportFragmentManager().popBackStack();
        return getSupportFragmentManager()
                .beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .setCustomAnimations(R.animator.fade_in, R.animator.fade_out)
                .replace(R.id.frame_fragments, loadFrag)
                .addSharedElement((View)findViewById(R.id.frame_fragments), "transition");
    }

    @Override
    public void onFragmentMessage(String TAG, Object data) {
        if (TAG.equals("playing_music")){
            Toast.makeText(this, "Playing...", Toast.LENGTH_SHORT).show();
            if (!isPlaying){
                playAll();
                isPlaying = true;
            }

        }
        else if (TAG.equals("stop_music") && isPlaying){
            if (carMediaPlayer.isPlaying()){
                carMediaPlayer.pause();
                carMediaPlayer.stop();
                isPlaying = false;
            }
        }
        else if (TAG.equals("specific_song") && isPlaying){
            if (carMediaPlayer.isPlaying()){
                carMediaPlayer.pause();
                carMediaPlayer.stop();
                isPlaying = false;
            }
        }
        else if (TAG.equals("next_song") && isPlaying){
            nextSong();
        }
    }


    //      PLAY SONGS
    public void playAll(){
        carMediaPlayer = new MediaPlayer();
        if (carMediaPlayer.isPlaying()){
            carMediaPlayer.pause();
            isPlaying = false;
        }else{
            if (songList.size()>0){
                isPlaying = true;

                res = songList.iterator().next();
                music_index = songList.indexOf(res);

                try {
                    carMediaPlayer.setDataSource(res.get("file_path")+"");
                    carMediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                passSong(res.get("file_name")+"");

                carMediaPlayer.start();

                carMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        if (music_index+1 < songList.size()){
                            music_index+=1;
                            res = songList.get(songList.indexOf(music_index));
                            try {
                                carMediaPlayer.reset();
                                carMediaPlayer.setDataSource(res.get("file_path")+"");
                                carMediaPlayer.prepare();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            carMediaPlayer.start();
                        }
                    }
                });

            }else{
                Toast.makeText(this, "No songs found", Toast.LENGTH_SHORT).show();
            }
        }
    }


    //      NEXT SONGS
    public void nextSong(){
        if (carMediaPlayer.isPlaying()){
            carMediaPlayer.stop();
            carMediaPlayer = new MediaPlayer();
            music_index+=1;
            res = songList.get(music_index);
            try {
                carMediaPlayer.reset();
                carMediaPlayer.setDataSource(res.get("file_path")+"");
                carMediaPlayer.prepare();
                passSong(res.get("file_name")+"");
            } catch (IOException e) {
                e.printStackTrace();
            }
            carMediaPlayer.start();


            carMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                if (music_index+1 < songList.size()){
                    music_index+=1;
                    res = songList.get(songList.indexOf(res));
                    try {
                        carMediaPlayer.reset();
                        carMediaPlayer.setDataSource(res.get("file_path")+"");
                        carMediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    carMediaPlayer.start();
                }
                }
            });
        }


    }

    public void passSong(String newSong){
        Bundle bundPlay = new Bundle();
        bundPlay.putString("song_name", newSong);
        frag_music.setArguments(bundPlay);
    }


    //      DAY/NIGHT MODE
    public void checkDayNight(){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        int dayNight = preferences.getInt("dayNight", 1);
        if (dayNight == AppCompatDelegate.MODE_NIGHT_YES){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

}
