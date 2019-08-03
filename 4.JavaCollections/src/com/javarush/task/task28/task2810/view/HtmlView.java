package com.javarush.task.task28.task2810.view;

import com.javarush.task.task28.task2810.Controller;
import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class HtmlView implements View {
    private Controller controller;
    private final String filePath = "./src/" + this.getClass().getPackage().getName().replace('.', '/') +
            "/vacancies.html";

    @Override
    public void update(List<Vacancy> vacancies) {
        try {
            updateFile(getUpdatedFileContent(vacancies));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Some exception occured");
        }
    }

    @Override
    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void userCitySelectEmulationMethod() {
        controller.onCitySelect("Odessa");
    }

    private String getUpdatedFileContent(List<Vacancy> vacancies) {
        Document document = null;
        try {
            document = getDocument();

            Element template = document.getElementsByClass("template").first();
            Element templateCopy = template.clone();
            templateCopy.removeAttr("style");
            templateCopy.removeClass("template");
            Elements oldVacancies = document.select("tr.vacancy");
            for (Element element : oldVacancies) {
                if (element.hasClass("template")) {
                    continue;
                }
                element.remove();
            }

            for (Vacancy vacancy : vacancies) {
                Element vacancyElement = templateCopy.clone();
                vacancyElement.select(".city").first().text(vacancy.getCity());
                vacancyElement.select(".companyName").first().text(vacancy.getCompanyName());
                vacancyElement.select(".salary").first().text(vacancy.getSalary());
                vacancyElement.select("a").first().text(vacancy.getTitle()).attr("href", vacancy.getUrl());
                document.select("tr.template").first().before(vacancyElement.outerHtml());
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Some exception occurred";
        }
        return document.outerHtml();
    }

    private void updateFile(String content) {
        try {
//            FileWriter fileWriter = new FileWriter(new File("/home/cpv/IdeaProjects/JavaRushTasks/4.JavaCollections/" + filePath));
            FileWriter fileWriter = new FileWriter(new File(filePath));
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Document getDocument() throws IOException {

//        Document document = Jsoup.parse(new File("/home/cpv/IdeaProjects/JavaRushTasks/4.JavaCollections/" + filePath), "UTF-8");
        Document document = Jsoup.parse(new File(filePath), "UTF-8");
        return document;
    }
}
