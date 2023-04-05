package org.rates.presentation.codes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.rates.connection.BasicAppUrl;
import org.rates.presentation.app.RatesLogicApp;

import java.io.IOException;

/**
 * Jsoup:
 * 1. Connection jsoup with url basic .get()
 * 2. Document and get elements by ...
 * 3. ForEach select your fields tag, attribute
 */
public class DisplayCurrency {

    public void getCodeWithWiki() {
        try {
            Document document = Jsoup.connect( BasicAppUrl.getUrlBasicCodeCurrency()).get();
            Elements elements = document.getElementsByClass("wikitable sortable");
            Elements title = document.getElementsByTag("p");
            System.out.println("_________________________________________");
            System.out.println(title.text());
            System.out.println("_________________________________________");

            for (Element element : elements.select("tr")) {
                System.out.println(element.text());
            }
            System.out.println(RatesLogicApp.getUserName() + " wybierz interesujący Cię kod waluty i wyszukaj wartości w opcji 3:");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
