package ru.k113.rxhw.task1;

import android.os.AsyncTask;
import android.util.Log;
import java.util.concurrent.TimeUnit;

class MyAsyncTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                Log.d(Const.TAG, "Running : " + i + " " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }//doInBackground

}