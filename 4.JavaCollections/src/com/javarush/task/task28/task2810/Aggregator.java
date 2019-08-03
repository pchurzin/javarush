package com.javarush.task.task28.task2810;

import com.javarush.task.task28.task2810.model.HHStrategy;
import com.javarush.task.task28.task2810.model.Model;
import com.javarush.task.task28.task2810.model.MoikrugStrategy;
import com.javarush.task.task28.task2810.model.Provider;
import com.javarush.task.task28.task2810.view.HtmlView;
import com.javarush.task.task28.task2810.view.View;

public class Aggregator {
    public static void main(String[] args) {
        Provider provider = new Provider(new HHStrategy());
        Provider mkProvider = new Provider(new MoikrugStrategy());
        View view = new HtmlView();
        Model model = new Model(view, provider, mkProvider);
        Controller controller = new Controller(model);
        view.setController(controller);
        ((HtmlView)view).userCitySelectEmulationMethod();
    }
}
