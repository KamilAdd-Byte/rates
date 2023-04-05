package org.rates.data.gold;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.google.gson.Gson;
import io.vavr.control.Try;
import lombok.NoArgsConstructor;
import org.rates.connection.BasicAppUrl;
import org.rates.connection.ConnectionCreator;
import org.rates.data.gold.dto.GoldDto;
import org.rates.data.lineitem.JsonLine;
import org.rates.data.lineitem.JsonLineValue;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

@NoArgsConstructor
public class NbpValueGold extends ConnectionCreator implements JsonLineValue {

    private static JsonLine jsonLine;
    private static Gson gson = null;
    private static BufferedReader reader;
    private static File file;
    private static final Scanner scanner = new Scanner(System.in);

    public NbpValueGold(JsonLine jsonLine) {
      NbpValueGold.jsonLine = jsonLine;
    }

    public JsonLine getEmptyJsonLine() {
        return JsonLine.empty();
    }


    public void onlyPrintGoldValueInConsole() {
        jsonLine = getJsonLine();
        GoldDto[] goldDto = getGoldDtos();
        for (GoldDto dto : goldDto) {
            System.out.println(dto);
        }

    }

    @Override
    public JsonLine getJsonLineItemByURL() throws IOException {
        URL onNbp = createURL(BasicAppUrl.getUrlBasicValueGold());
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

    private static GoldDto[] getGoldDtos() {
        gson = new Gson();
        return gson.fromJson(jsonLine.getValue(), GoldDto[].class);
    }

    public static void printValueGoldToCsv() throws IOException {
        gson = new Gson();

        ObjectWriter writer = getObjectWriter();
        GoldDto[] goldDto = getGoldDtos();

        createFileName();

        try {
            reader = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = reader.readLine())!=null){
                writer.writeValues(file).writeAll(goldDto);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        reader.close();
    }

    private static ObjectWriter getObjectWriter() {
        CsvMapper mapperCsv = new CsvMapper(); // instancja CsvMappera
        mapperCsv.enable(CsvParser.Feature.EMPTY_STRING_AS_NULL); //pomijanie nierozpoznanych typów

        CsvSchema columns = CsvSchema.builder().setUseHeader(true) //utworzenie kolumn
                .addColumn("data")
                .addColumn("cena")
                .build();

        return mapperCsv.writerWithSchemaFor(GoldDto.class).with(columns);
    }

    private static void createFileName() {
        System.out.println("Nadaj nazwe plikowi:");
        String userFileName = scanner.nextLine();
        file = new File(userFileName+".csv");
        }

    private static void setFile(File file) {
        NbpValueGold.file = file;
    }

}
