package com.sandiplayek.anrdetection.AppStopReport;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.sandiplayek.anrdetection.MainActivity;

public class UnCaughtException implements Thread.UncaughtExceptionHandler {
    private Context context;
    public UnCaughtException(Context ctx) {
        context = ctx;
    }
    public void uncaughtException(Thread t, Throwable e) {
        try {
            sendErrorMail(e);
        } catch (Throwable ignore) {
            Log.e(UnCaughtException.class.getName(),
                    "Error while sending error e-mail", ignore);
        }

    }
    public void sendErrorMail(final Throwable exception) {

        /*------------------------------- Stack trace -------------------------------------------------------------*/

        StackTraceElement[] arr = exception.getStackTrace();
        String report = exception.toString()+"\n\n";
        report += "--------- Stack trace ---------\n\n";
        for (int i=0; i<arr.length; i++) {
            report += "    "+arr[i].toString()+"\n";
        }
        report += "-------------------------------\n\n";

        /*------------------------------- Cause -------------------------------------------------------------*/

        report += "--------- Cause ---------\n\n";
        Throwable cause = exception.getCause();
        if(cause != null) {
            report += cause.toString() + "\n\n";
            arr = cause.getStackTrace();
            for (int i=0; i<arr.length; i++) {
                report += "    "+arr[i].toString()+"\n";
            }
        }
        report += "-------------------------------\n\n";

        Intent intent = new Intent(context, MainActivity.class);
        intent.putExtra("CRASH_REPORT",report);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(10);
    }
}