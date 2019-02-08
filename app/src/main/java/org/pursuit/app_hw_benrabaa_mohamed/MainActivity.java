package org.pursuit.app_hw_benrabaa_mohamed;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements FragmentInterface {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, MainFragment.newInstance()).commit();
    }

    @Override
    public void showFirstFragment(String param1) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, FirstFragment.newInstance(param1)).addToBackStack(null).commit();
    }

    @Override
    public void showResultFragment(int param1, int param2) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, ResultFragment.newInstance(param1, param2)).addToBackStack(null).commit();

    }
}
