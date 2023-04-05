package org.rates.presentation.scrap;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.rates.exception.DocumentCreateException;
import org.rates.messages.Message;
import java.io.IOException;

@Slf4j
public class DocumentCreator {

    protected Document create (String baseUri) {
        return Try.of(()-> createNewDocument(baseUri))
                .onSuccess(d -> log.info(Message.documentCreatedMessage()))
                .getOrElseThrow(DocumentCreateException::becauseDocumentNotCreated);
    }

    private Document createNewDocument(String baseUri) throws IOException {
        log.debug("Starting connect jsoup to uri [{}]", baseUri);
        return Jsoup.connect(baseUri).get();
    }
}
