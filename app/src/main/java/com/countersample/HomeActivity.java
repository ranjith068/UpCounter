package com.countersample;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.countersample.utils.MyTabLayout;

public class HomeActivity extends AppCompatActivity implements TabLayout.OnTabSelectedListener {

    int counter = 0;
    TextView counterTxt;
    private Button timerBtn;
    Chronometer chronometer;
    private boolean timerFlag = false;
    private int interval = 1;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
                switch (item.getItemId()) {
                    case R.id.navigation_home:

                        return true;
                    case R.id.navigation_dashboard:

                        return true;
                    case R.id.navigation_profile:



                        startActivity(new Intent(HomeActivity.this,ProfileActivity.class));
                        overridePendingTransition(R.anim.slidein,R.anim.slideout);

                        return true;
                }
                return false;
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        ProgressBar mProgress = (ProgressBar) findViewById(R.id.circularProgressbar);
        mProgress.setMax(100); // Maximum Progress


        ObjectAnimator animation = ObjectAnimator.ofInt(mProgress, "progress", 0, 100);
        animation.setDuration(990);
        animation.setInterpolator(new DecelerateInterpolator());

        MyTabLayout tabLayout = (MyTabLayout) findViewById(R.id.tabs);
        timerBtn = (Button) findViewById(R.id.timer);
        counterTxt = (TextView) findViewById(R.id.counter);

        chronometer = (Chronometer) findViewById(R.id.chronometer);

        timerBtn.setText("Go");



        tabLayout.addOnTabSelectedListener(this);
        tabLayout.setScrollPosition(1, 0f, true);
        interval = 1;

        timerBtn.setOnClickListener(v -> {
            counter = 0;
            if (timerFlag) {
                animation.end();
                chronometer.stop();
                timerBtn.setText("Go");
            } else {


                animation.start();

                chronometer.setBase(SystemClock.elapsedRealtime());
                chronometer.start();
                timerBtn.setText("Stop");
            }
            timerFlag = !timerFlag;
        });


        chronometer.setOnChronometerTickListener(
                chronometer1 -> {
                    // TODO Auto-generated method stub
                    long myElapsedMillis = SystemClock.elapsedRealtime() - chronometer1.getBase();

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

                    animation.start();

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
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onTabSelected(TabLayout.Tab tab) {


        if(tab.getPosition() == 0)
        {
            interval = 2;
        } else if(tab.getPosition() == 1){
            interval = 1;
        }else if(tab.getPosition() == 2){
            interval = 5;
        } else{
            interval = 10;
        }

    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
        if(tab.getPosition() == 0)
        {
            interval = 2;
        } else if(tab.getPosition() == 1){
            interval = 1;
        }else if(tab.getPosition() == 2){
            interval = 5;
        } else{
            interval = 10;
        }
    }


}
