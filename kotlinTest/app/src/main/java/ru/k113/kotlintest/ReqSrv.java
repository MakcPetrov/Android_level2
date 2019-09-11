package ru.k113.kotlintest;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

public class ReqSrv extends IntentService {

    private int messageId;

    public ReqSrv() {
        super("ReqSrv");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        BackEnd.isLog("onHandleIntent");
    }

    @Override
    public void onCreate() {
        BackEnd.isLog("onCreate");
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        BackEnd.isLog("onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        BackEnd.isLog("onDestroy");
        super.onDestroy();
    }
}
