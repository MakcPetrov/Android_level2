package ru.k113.rxhw.task1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import ru.k113.rxhw.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(Const.TAG, "onCreate " + Thread.currentThread().getName());
    }//onCreate

//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.d(Const.TAG, "onStop " + Thread.currentThread().getName());
//    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        Log.d(Const.TAG, "onDestroy " + Thread.currentThread().getName());
//    }

    public void onClickButton1(View view) {
        Log.d(Const.TAG, "Button1 " + Thread.currentThread().getName());
        MyAsyncTask myAsyncTask = new MyAsyncTask();
        myAsyncTask.execute();
        Log.d(Const.TAG, "AsyncTask started " + Thread.currentThread().getName());
    }//onClickButton1

}
