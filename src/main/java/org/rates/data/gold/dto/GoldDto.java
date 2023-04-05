package org.rates.data.gold.dto;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"data","cena"})
public class GoldDto {

    private String data;
    private String cena;

    public String getCena() {
        return cena;
    }

    public void setCena(String cena) {
        this.cena = cena;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Cena złota (1 g w próbie 1000) " + "cena: " + cena + " data: " + data;
    }
}
