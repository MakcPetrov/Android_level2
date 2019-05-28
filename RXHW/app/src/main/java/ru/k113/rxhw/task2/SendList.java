package ru.k113.rxhw.task2;

interface SendList {
    void addObserver(MyObserver observer);
    void removeObserver (MyObserver observer);
    void sendAllObserver();
}
