package com.android.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.Fragment;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.Switch;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;


public class FragSettings extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private OnFragmentInterfaceCom mListener;

    private Switch dayNight, downloadPath, musicPath, allPaths;

    public FragSettings() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static FragSettings newInstance(String param1, String param2) {
        FragSettings fragment = new FragSettings();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_settings, container, false);

        dayNight = (Switch)v.findViewById(R.id.switchDay);
        downloadPath = (Switch)v.findViewById(R.id.switchSongsDownload);
        musicPath = (Switch)v.findViewById(R.id.switchSongsMusic);
        allPaths = (Switch)v.findViewById(R.id.switchSongsAll);


        checkDayNight(v);
        checkPaths(v);

        dayNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();

                if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_NO){
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    editor.putInt("dayNight", AppCompatDelegate.MODE_NIGHT_YES).apply();

                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putInt("dayNight", AppCompatDelegate.MODE_NIGHT_NO).apply();
                }
                editor.commit();
            }
        });

        downloadPath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                int download = preferences.getInt("downloadPath", 0);
                if (download == 0){
                    editor.putInt("downloadPath", 1).apply();
                }else{
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    editor.putInt("downloadPath", 0).apply();
                }
                editor.commit();
            }
        });

        musicPath.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                int music = preferences.getInt("musicPath", 0);
                if (music == 0){
                    editor.putInt("musicPath", 1).apply();
                }else{
                    editor.putInt("musicPath", 0).apply();
                }
                editor.commit();
            }
        });

        allPaths.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
                SharedPreferences.Editor editor = preferences.edit();
                int all = preferences.getInt("allPaths", 0);
                if (all == 0){
                    editor.putInt("allPaths", 1).apply();

                }else{
                    editor.putInt("allPaths", 0).apply();
                }
                editor.commit();
            }
        });


        return v;
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


    public void checkDayNight(View v){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        int dayNightNum = preferences.getInt("dayNight", 1);
        dayNight = (Switch)v.findViewById(R.id.switchDay);
        if (dayNightNum == AppCompatDelegate.MODE_NIGHT_YES){
            dayNight.setChecked(true);
        }else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    public void checkPaths(View v){
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String[] paths = new String[]{"downloadPath","musicPath","allPaths"};

    }

}
