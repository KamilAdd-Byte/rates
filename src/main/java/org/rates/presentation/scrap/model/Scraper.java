package org.rates.presentation.scrap.model;

import io.vavr.control.Try;
import lombok.NoArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.rates.exception.ElementsCreateException;
import org.rates.presentation.view.Display;
import java.util.Objects;

@NoArgsConstructor
public class Scraper {
    private Document document;
    private String elementByClass;
    private String elementByTag;

    public Scraper(Document document, String byClass, String byTag) {
        this.document = document;
        this.elementByClass = byClass;
        this.elementByTag = byTag;
    }

    public Elements getElementsByClass() {
        return Try.of(() -> document.getElementsByClass(elementByClass))
                .getOrElseThrow(ElementsCreateException::becauseElementsHasErrorByClass);
    }

    public Elements getElementsByTag() {
        return Try.of(() -> document.getElementsByTag(elementByTag))
                .getOrElseThrow(ElementsCreateException::becauseElementsHasErrorByTag);
    }

    public void displayAllCode(Elements elements, String query) {
        elements.select(query).stream()
                .filter(Objects::nonNull)
                .forEach(element -> Display.displayContent(element.text()));
    }

    public void displayTitle(Elements elements) {
        Display.displayContent(elements.text());
    }
}