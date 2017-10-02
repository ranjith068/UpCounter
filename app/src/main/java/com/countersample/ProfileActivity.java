package com.countersample;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {


    private Chronometer chronometer;
    private TextView counterTxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        counterTxt = (TextView) findViewById(R.id.counter);

        chronometer = (Chronometer) findViewById(R.id.chronometer);
        if (getIntent().getExtras() != null){
            counterTxt.setText(getIntent().getExtras().getString("count"));
            chronometer.setText(getIntent().getExtras().getString("time"));
        }
    }

}
