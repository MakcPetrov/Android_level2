package ru.k113.rxhw;

import android.os.AsyncTask;
import android.util.Log;

import java.util.concurrent.TimeUnit;

class MyAsyncTask extends AsyncTask<Void, Void, Void> {
    static final String TAG = "AsyncActivity";

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Log.d(TAG, "onPreExecute: " + Thread.currentThread().getName());
    }

    @Override
    protected Void doInBackground(Void... voids) {


        try {
            for (int i = 0; i < 5; i++) {
                TimeUnit.SECONDS.sleep(1);
                Log.d(TAG, "doInBackground: " + Thread.currentThread().getName() + ": " + i);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Log.d(TAG, "onPostExecute: " + Thread.currentThread().getName());

    }
}