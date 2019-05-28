package ru.k113.rxhw.task2;

import android.util.Log;
import java.util.ArrayList;

public class Sender implements SendList {

    public int getCoutObserver() {
        return coutObserver;
    }

    private int coutObserver;
    private ArrayList<MyObserver> arrayList;
    private String name, num;

    public Sender() {arrayList = new ArrayList<>(); }

    public void newItem(String name, String num){//новый элемент рассылки
        this.name = name; this.num = num;
        sendAllObserver();//послать всем подписантам
    }

    @Override
    public void addObserver(MyObserver observer) {
        arrayList.add(observer);
        coutObserver=arrayList.size();
    }

    @Override
    public void removeObserver(MyObserver observer) {
        arrayList.remove(observer);
        coutObserver=arrayList.size();
    }

    @Override
    public void sendAllObserver() {
        coutObserver=arrayList.size();
        Log.d(Const.TAG, "sendТо "+ coutObserver + " observer(s)");
        for (int i = 0; i < coutObserver; i++) {
            MyObserver observer = arrayList.get(i);
            observer.updateData(name, num);

        }
    }//sendAllObserver
}
