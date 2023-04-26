package org.rates.data.currency;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import io.vavr.control.Try;
import lombok.NoArgsConstructor;
import org.rates.connection.BasicAppUrl;
import org.rates.connection.ConnectionCreator;
import org.rates.data.currency.dto.CurrencyDto;
import org.rates.data.lineitem.JsonLine;
import org.rates.data.lineitem.JsonLineValue;
import org.rates.export.csv.CSVPreparer;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

@NoArgsConstructor
public class NbpValueCurrency extends ConnectionCreator implements JsonLineValue {

    private static JsonLine jsonLine;

    private static String table;
    private static String currency;
    private static LocalDate date;

    public NbpValueCurrency(JsonLine jsonLine) {
        NbpValueCurrency.jsonLine = jsonLine;
    }

    public static String getTable() {
        return table;
    }

    public static void setTable(String table) {
        NbpValueCurrency.table = table;
    }

    public static String getCurrency() {
        return currency;
    }

    public static void setCurrency(String currency) {
        NbpValueCurrency.currency = currency;
    }

    public static LocalDate getDate() {
        return date;
    }

    public static void setDate(LocalDate date) {
        NbpValueCurrency.date = date;
    }

    public void getCurrencyValueOnNbpApi() throws IOException {
            jsonLine = getJsonLine();

            Gson gson = new Gson();
            CurrencyDto currency = gson.fromJson(jsonLine.getValue(), CurrencyDto.class);
            printCurrencyValue(currency);

    }

    @Override
    public JsonLine getJsonLineItemByURL() throws IOException {
        var urlNbp = BasicAppUrl.getUrlBasicNbpApi() + table + "/" + currency + "/" + date;
        URL onNbp = createURL(urlNbp);
        URLConnection urlConnection = createConnection(onNbp);
        BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
        jsonLine = new JsonLine();
        jsonLine.setValue(reader.readLine());
        return jsonLine;
    }

    @Override
    public JsonLine getJsonLine() {
        return Try.of(this::getJsonLineItemByURL)
                .onFailure(Throwable::printStackTrace)
                .getOrElse(JsonLine.empty());
    }

    private static void printCurrencyValue(CurrencyDto currency) throws IOException {
        System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ Pozyskane dane: ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
        System.out.println(currency);
        System.out.println("VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV");
        printCurrencyToCsv(currency);
    }

    public static void printCurrencyToCsv(CurrencyDto dto) throws IOException {
        CSVPreparer csvPreparer = new CSVPreparer();
        csvPreparer.prepareCsv("curr",dto);
    }

    /**
     * @param output Value to scanner.in
     * @return LocalDate pattern yyyy-MM-DD.
     */
    public static LocalDate convertOutputLineToDate(String output){
        return LocalDate.parse(output);
    }
}
