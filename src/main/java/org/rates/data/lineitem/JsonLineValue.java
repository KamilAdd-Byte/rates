package org.rates.data.lineitem;

import java.io.IOException;

public interface JsonLineValue {

    JsonLine getJsonLineItemByURL() throws IOException ;

    JsonLine getJsonLine();

}
