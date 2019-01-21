package com.sandiplayek.anrdetection;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.sandiplayek.anrwatcher.anrdetector.ANRWatchDog;
import com.sandiplayek.anrwatcher.anrdetector.ANRWatchdogTestApplication;


public class MainActivity extends AppCompatActivity {

    TextView tv;

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
                int i = 0;
                while (true){
                    i++;
                }
            }
        });
    }
}
