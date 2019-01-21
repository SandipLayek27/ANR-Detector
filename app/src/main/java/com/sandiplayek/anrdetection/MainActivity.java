package com.sandiplayek.anrdetection;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.sandiplayek.anrwatcher.anrdetector.ANRWatchDog;
import com.sandiplayek.anrwatcher.anrdetector.ANRWatchdogTestApplication;


public class MainActivity extends AppCompatActivity {

    TextView tv;
    private final Object _mutex = new Object();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ANRWatchDog anrWatchDog = ((ANRWatchdogTestApplication) getApplication()).anrWatchDog;
        //anrWatchDog.setReportThreadNamePrefix("APP:");
        anrWatchDog.setReportMainThreadOnly();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.tv);
        try{
            String report = getIntent().getExtras().getString("CRASH_REPORT");
            tv.setText(report);
        }catch (Exception e){
            e.printStackTrace();
        }
        findViewById(R.id.simpleAll).setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                /*int i = 0;
                while (true){
                    i++;
                }*/
                //SleepAMinute();
                _deadLock();
            }
        });
    }


    private void _deadLock() {
        new MainActivity.LockerThread().start();

        new Handler().postDelayed(new Runnable() {
            @Override public void run() {
                synchronized (_mutex) {
                    Log.e("ANR-Failed", "There should be a dead lock before this message");
                }
            }
        }, 1000);
    }
    public class LockerThread extends Thread {
        public LockerThread() {
            setName("APP: Locker");
        }
        @Override
        public void run() {
            synchronized (_mutex) {
                //noinspection InfiniteLoopStatement
                while (true)
                    SleepAMinute();
            }
        }
    }
    private static void SleepAMinute() {
        try {
            Thread.sleep(20 * 1000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
