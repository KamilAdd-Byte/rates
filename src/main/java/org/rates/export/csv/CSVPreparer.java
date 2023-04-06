package org.rates.export.csv;

import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvParser;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import lombok.NoArgsConstructor;
import org.rates.data.currency.dto.CurrencyDto;

import java.io.File;
import java.io.IOException;

@NoArgsConstructor
public class CSVPreparer {
    private File file;
    private CsvMapper csvMapper;
    private CsvSchema csvSchema;
    private ObjectWriter writer;

    public void prepareCsv(String fileName, Object object) throws IOException {
        file = new File(fileName + ".csv");

        csvMapper = new CsvMapper();
        csvMapper.enable(CsvParser.Feature.WRAP_AS_ARRAY);

        csvSchema = CsvSchema.builder().setUseHeader(true)
                .addColumn("table")
                .addColumn("currency")
                .addColumn("code")

               . addColumn("rates")
                .addColumn("no")
                .addColumn("effectiveDate")
                .addColumn("mid")
               .addColumn("bid")
                .addColumn("ask")

                .build();

        writer = csvMapper.writerFor(CurrencyDto.class).with(csvSchema);
        writer.writeValues(file).write(object);
    }
}
