package com.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Switch;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class SplashActivity extends AppCompatActivity {

    public ArrayList<HashMap<String,String>> songList;
    final String download_path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)+"";
    final String music_path =  Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC)+"";
    final String external_path =  Environment.getExternalStorageDirectory().getAbsolutePath()+"";
    public TextView showInfoTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        showInfoTextView = (TextView) this.findViewById(R.id.showInfo);
        showInfoTextView.setText("Loading songs... \n\n");
        ReadFiles rd = new ReadFiles();
        rd.execute();

    }
    class ReadFiles extends AsyncTask<String, String, String> {


        @Override
        protected String doInBackground(String... strings) {
            songList = loadSongs();
            return null;
        }
        @Override
        protected void onPostExecute(String data){
            super.onPostExecute("");
            Intent goMain = new Intent(getApplicationContext(), MainActivity.class);
            Bundle b = new Bundle();
            if (songList!=null){
                b.putSerializable("",songList);
                goMain.putExtra("songList", songList);
            }
            startActivity(goMain);
            finish();
        }
    }

    //      MUSIC PLAYER LIST
    public ArrayList<HashMap<String,String>> getPlayList(String rootPath) {
        ArrayList<HashMap<String,String>> fileList = new ArrayList<>();
        try {
            File rootFolder = new File(rootPath);
            File[] files = rootFolder.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    if (getPlayList(file.getAbsolutePath()) != null) {
                        fileList.addAll(getPlayList(file.getAbsolutePath()));
                    } else {
                        break;
                    }
                } else if (file.getName().endsWith(".mp3")) {
                    String lastInfo = showInfoTextView.getText().toString();
                    showInfoTextView.setText(lastInfo+file.getName()+"\n");
                    HashMap<String, String> song = new HashMap<>();
                    song.put("file_path", file.getAbsolutePath());
                    song.put("file_name", file.getName());
                    fileList.add(song);
                }
            }
            return fileList;
        } catch (Exception e) {
            return null;
        }
    }

    //      LOAD SONGS
    public ArrayList<HashMap<String, String>> loadSongs(){
        songList = new ArrayList<>();

        songList = getPlayList(external_path);

        for(HashMap<String,String> new_data: getPlayList(music_path)){
            songList.add(new_data);
        }

        for(HashMap<String,String> new_data: getPlayList(download_path)){
            songList.add(new_data);
        }
        return songList;
    }


    //      PERMISSIONS
    public void checkPermissions(){

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);

        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 1);
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH}, 1);
        }
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.BLUETOOTH_ADMIN) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.BLUETOOTH_ADMIN}, 1);
        }

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
    }
}