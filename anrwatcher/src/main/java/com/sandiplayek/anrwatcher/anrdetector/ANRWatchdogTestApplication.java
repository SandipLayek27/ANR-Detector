package com.sandiplayek.anrwatcher.anrdetector;

import android.app.Application;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ANRWatchdogTestApplication extends Application {
    public ANRWatchDog anrWatchDog = new ANRWatchDog(ANRTimeout.getTime);
    @Override
    public void onCreate() {
        super.onCreate();

        anrWatchDog.setANRListener(new ANRWatchDog.ANRListener() {
            @Override
            public void onAppNotResponding(ANRError error) {
                Log.e("ANR-Watchdog", "Detected Application Not Responding!");
                try {
                    new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(error);
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                Log.i("ANR-Watchdog", "Error was successfully serialized");
                throw error;
            }
        });
        anrWatchDog.start();
    }
}
