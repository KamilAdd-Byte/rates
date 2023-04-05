package org.rates.connection;

import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Class responsibility for connection to other API based on URL
 */
@Slf4j
public class ConnectionCreator {
    /**
     * @param url String value for create new URL
     * @return new URL
     */
    public static URL createURL (String url) throws MalformedURLException {
        log.info("Application preparing to create new URL");
        return Try.of(()-> createNewURL(url))
                .onSuccess(u -> log.info("URL is correct created to host: {}", u.getHost()))
                .onFailure(throwable -> log.error("URL was not created: [{}]", throwable.getMessage()))
                .getOrElseThrow(() -> new MalformedURLException());
    }
    /**
     * @param url it is URL for new connection
     * @return new connection or error
     */
    public static URLConnection createConnection (URL url) throws MalformedURLException {
        log.info("Application preparing to create new connection");
        return Try.of(url::openConnection)
                .onSuccess(uc -> log.info("Connection to the url is successfully: [{}]", uc.getURL()))
                .onFailure(throwable -> log.error("Connection has errors: [{}]", throwable.getMessage()))
                .getOrElseThrow(()-> new MalformedURLException());
    }

    private static URL createNewURL(String url) throws MalformedURLException {
        return new URL(url);
    }
}