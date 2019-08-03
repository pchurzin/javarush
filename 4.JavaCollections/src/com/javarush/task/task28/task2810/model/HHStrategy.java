package com.javarush.task.task28.task2810.model;

import com.javarush.task.task28.task2810.vo.Vacancy;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class HHStrategy implements Strategy {
    private static final String URL_FORMAT = "http://hh.ru/search/vacancy?text=java+%s&page=%d";
//    private static final String CACHE_URL = "http://javarush.ru/testdata/big28data.html";

    @Override
    public List<Vacancy> getVacancies(String searchString) {
        List<Vacancy> vacancies = new LinkedList<>();
        try {
            for (int page = 0; ; page++) {
                Document document = getDocument(searchString, page);
                Elements vacancyElements = document.select("*[data-qa='vacancy-serp__vacancy']");
                if (vacancyElements.isEmpty()) {
                    break;
                }
                for (Element element : vacancyElements) {
                    Vacancy vacancy = new Vacancy();
                    vacancy.setTitle(element.select("*[data-qa='vacancy-serp__vacancy-title']").first().text());
                    vacancy.setCity(element.select("*[data-qa='vacancy-serp__vacancy-address']").first().text());
                    vacancy.setCompanyName(element.select("*[data-qa='vacancy-serp__vacancy-employer']").first().text());
                    vacancy.setSiteName("http://hh.ua");
                    vacancy.setUrl(element.select("*[data-qa='vacancy-serp__vacancy-title']").first().attr("href"));
                    Element salary = element.select("*[data-qa='vacancy-serp__vacancy-compensation']").first();
                    if (salary == null) {
                        vacancy.setSalary("");
                    } else {
                        vacancy.setSalary(salary.text());
                    }
                    vacancies.add(vacancy);
                }
            }
        } catch (IOException ignore) {
            ignore.printStackTrace();
        }
        return vacancies;
    }

    protected Document getDocument(String searchString, int page) throws IOException {
        Connection connection = Jsoup.connect(String.format(URL_FORMAT, searchString, page));
        connection.userAgent("Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:59.0) Gecko/20100101 Firefox/59.0");
        connection.referrer("");
        Document document = connection.get();
        return document;
    }
}
