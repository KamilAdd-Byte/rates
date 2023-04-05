package org.rates.presentation.scrap;

import lombok.Getter;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.rates.connection.BasicAppUrl;
import org.rates.presentation.app.RatesLogicApp;
import org.rates.presentation.scrap.model.Scraper;
import org.rates.presentation.view.currency.CurrencyInfo;

@Getter
public class ScrapCurrency {

    private static final String SELECT_QUERY= "tr";
    private static final String ELEMENT_BY_CLASS = "wikitable sortable";
    private static final String ELEMENT_BY_TAG = "p";

    public ScrapCurrency() { }

    public void getCodeWithWiki() {
        Document document = getDocument();
        Scraper scraper = new Scraper(document, ELEMENT_BY_CLASS, ELEMENT_BY_TAG);
        Elements elementsByClass = scraper.getElementsByClass();
        Elements elementsByTag = scraper.getElementsByTag();
        CurrencyInfo.printLineSeparate();
        scraper.displayTitle(elementsByTag);
        CurrencyInfo.printLineSeparate();
        scraper.displayAllCode(elementsByClass, SELECT_QUERY);

        System.out.println(RatesLogicApp.getUserName() + " wybierz interesujący Cię kod waluty i wyszukaj wartości w opcji 3:");

    }

    private static Document getDocument() {
        DocumentCreator documentCreator = new DocumentCreator();
        return documentCreator.create(BasicAppUrl.getUrlBasicCodeCurrency());
    }
}