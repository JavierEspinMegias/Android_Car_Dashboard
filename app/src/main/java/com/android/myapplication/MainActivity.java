package com.android.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements OnFragmentInterfaceCom {

    private BottomNavigationView menuBottom;
    private BottomNavigationItemView but1, but2, but3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        checkDayNight();

        menuBottom = (BottomNavigationView)findViewById(R.id.bottom_navigation);
        menuBottom.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Persona person1 = new Persona("Pepico");
                switch (item.getItemId()) {
                    case R.id.action_search:
                        FragUsers frag1 = FragUsers.newInstanceData("persona", person1);
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).setCustomAnimations(R.animator.fade_in, R.animator.fade_out).replace(R.id.frame_fragments, frag1).commit();
                        break;
                    case R.id.action_add:
                        Fragment2 frag2 = Fragment2.newInstance("", "");
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).setCustomAnimations(R.animator.fade_in, R.animator.fade_out).replace(R.id.frame_fragments, frag2).commit();
                        break;
                    case R.id.action_camera:
                        Fragment3 frag3 = Fragment3.newInstance("", "");
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).setCustomAnimations(R.animator.fade_in, R.animator.fade_out).replace(R.id.frame_fragments, frag3).commit();
                        break;
                    case R.id.action_navi:
                        Fragment4 frag4 = Fragment4.newInstance("", "");
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).setCustomAnimations(R.animator.fade_in, R.animator.fade_out).replace(R.id.frame_fragments, frag4).commit();
                        break;
                    case R.id.action_settings:
                        FragSettings frag5 = FragSettings.newInstance("", "");
                        getSupportFragmentManager().beginTransaction().setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE).setCustomAnimations(R.animator.fade_in, R.animator.fade_out).replace(R.id.frame_fragments, frag5).commit();
                        break;
                }
                return true;
            }
        });

    }

    @Override
    public void onFragmentMessage(String TAG, Object data) {
        if (TAG.equals("TAGFragment1")){
            //MAIN GESTIONARA INFORMACION PROVENIENTE DEL Fragment 1
            Toast.makeText(this, "info recibida del frag1: "+data.toString(), Toast.LENGTH_SHORT).show();
        }
        else if (TAG.equals("TAGFragment2")){
            //MAIN GESTIONARA INFORMACION PROVENIENTE DEL Fragment 2
        }
        else if (TAG.equals("TAGFragment3")){
            //MAIN GESTIONARA INFORMACION PROVENIENTE DEL Fragment 3
        }
    }


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
