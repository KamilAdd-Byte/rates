package org.rates.connection;

/**
 * Based class which has url paths.
 */
public abstract class BasicAppUrl {

    private String urlForScrapping;
    private static final String URL_BASIC_CODE_CURRENCY = "https://pl.wikipedia.org/wiki/ISO_4217";
    private static final String URL_BASIC_VALUE_GOLD = "https://api.nbp.pl/api/cenyzlota/last/30/?format=json";
    private static final String URL_BASIC_NBP_API = "https://api.nbp.pl/api/exchangerates/rates/";

    protected BasicAppUrl(String urlForScrapping) {
        this.urlForScrapping = urlForScrapping;
    }

    public static String getUrlBasicCodeCurrency() {
        return URL_BASIC_CODE_CURRENCY;
    }

    public static String getUrlBasicValueGold() {
        return URL_BASIC_VALUE_GOLD;
    }

    public static String getUrlBasicNbpApi() {
        return URL_BASIC_NBP_API;
    }

    public String getUrlForScrapping() {
        return urlForScrapping;
    }

}
