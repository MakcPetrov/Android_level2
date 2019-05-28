package ru.k113.rxhw.task2;

import android.util.Log;

public class Iam implements MyObserver {

    @Override
    public void updateData(String name, String num) {
        Log.d(Const.TAG, Thread.currentThread().getName() + "Получен элемент рассылки" + name + num);
    }
}
