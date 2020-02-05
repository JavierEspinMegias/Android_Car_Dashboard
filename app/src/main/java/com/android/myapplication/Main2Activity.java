package com.android.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        CustomBottomNavigationView curvedBottomNavigationView = findViewById(R.id.customBottomBar);
        curvedBottomNavigationView.inflateMenu(R.menu.bottom_menu);
    }
}
