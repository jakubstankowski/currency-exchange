import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JSON {
    private ObjectMapper mapper = new ObjectMapper();
    ObjectNode currencyJSONList = mapper.createObjectNode();

    ObjectNode getCurrencyJSON() {
        return currencyJSONList;
    }

    void setCurrencyJSON(String currencyName, ObjectNode currencyPrices) {
        currencyJSONList.set(currencyName, currencyPrices);
    }
}
