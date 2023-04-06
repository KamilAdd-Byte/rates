package org.rates.export.csv;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import lombok.extern.slf4j.Slf4j;
import org.rates.data.currency.dto.CurrencyDto;
import org.rates.data.currency.dto.RatesDto;
import org.rates.messages.Message;
import java.io.IOException;
import java.util.List;


/**
 * Class responsibility is serialization of nested objects for CurrencyDto.
 * Nested object for Currency is Rates.
 * @implNote add annotation for CurrencyDto: @JsonSerialize(using = CurrencySerializer.class)
 */
@Slf4j
public class CurrencySerializer extends JsonSerializer<CurrencyDto> {
    @Override
    public void serialize(CurrencyDto value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        log.info(Message.startSerializeMessage());
        gen.writeObject(value.getTable());
        gen.writeObject(value.getCurrency());
        gen.writeObject(value.getCode());
        List<RatesDto> rates = value.getRates();
        for (int i = 0; i < rates.size(); i++) {
            gen.writeObject("obj" + i);
            gen.writeObject(rates.get(i));
        }
        log.info(Message.endSerializeMessage());
    }
}