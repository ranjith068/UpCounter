package com.countersample;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import com.countersample.utils.MyTabLayout;

public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    int counter = 0;


    TextView counterTxt;
    private Button timerBtn;
    Chronometer chronometer;
    private boolean timerFlag = false;
    private int interval = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        MyTabLayout tabLayout = (MyTabLayout) findViewById(R.id.tabs);
        timerBtn = (Button) findViewById(R.id.timer);
        counterTxt = (TextView) findViewById(R.id.counter);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        timerBtn.setText("Start");

        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setScrollPosition(1, 0f, true);


        timerBtn.setOnClickListener(v -> {
            counter = 0;
            if (timerFlag) {

                chronometer.stop();
            } else {
                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
            }
            timerFlag = !timerFlag;
        });


        chronometer.setOnChronometerTickListener(
                chronometer1 -> {
                    // TODO Auto-generated method stub
                    long myElapsedMillis = SystemClock.elapsedRealtime() - chronometer1.getBase();
//                    String strElapsedMillis = "Elapsed milliseconds: " + myElapsedMillis;

                    int seconds = (int) (myElapsedMillis / 1000);

                    if (seconds == 0) {
                        counterTxt.setText(String.valueOf(0));
                    } else if (seconds == 1) {

                        counter = interval;
                        counterTxt.setText(String.valueOf(interval));

                    } else {
                        counter = interval + counter;
                        counterTxt.setText(String.valueOf(counter));

                    }


                }
        );


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {


        Toast.makeText(HomeActivity.this, "Tapped " + (tab.getPosition() + 1), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        Toast.makeText(HomeActivity.this, "Tapped " + tab.getText(), Toast.LENGTH_SHORT).show();
    }


}
