package com.javarush.task.task36.task3601;

public class View {
    private Controller controller;

    public void fireEventShowData() {
        System.out.println(new Controller().onDataListShow());
    }
}
