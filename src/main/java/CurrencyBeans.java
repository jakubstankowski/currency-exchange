import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.util.List;

class CurrencyBeans {
    private API API = new API();
    private JSON JSON = new JSON();
    private Currency currency = new Currency();
    private final static double SELL_SPREAD = 1.050;
    private final static double BUY_SPREAD = 0.995;

    void calculateCurrencies(List<String> currencyList) throws IOException {
        try {
            API.setAllCurrencyListFromAPI();
            for (int i = 0; i < currencyList.size(); i++) {
                currency.setBasicPrice(API.getChoiceCurrencyValueFromAPI(currencyList.get(i)));
                currency.setFinallSellPrice(Math.round(((currency.getBasicPrice() * BUY_SPREAD)) * 100.0) / 100.0);
                currency.setFinallBuyPrice(Math.round(((currency.getBasicPrice() * SELL_SPREAD)) * 100.0) / 100.0);
                createCurrencyJSON(currencyList.get(i));
            }

        } catch (IOException e) {
            throw new IOException("Something wrong with get currency from API!");
        }


    }

    private void createCurrencyJSON(String currencyName) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode currencyPrices = mapper.createObjectNode();
        currencyPrices.put("basic", currency.getBasicPrice());
        currencyPrices.put("sell", currency.getFinallSellPrice());
        currencyPrices.put("buy", currency.getFinallBuyPrice());
        JSON.setCurrencyJSON(currencyName, currencyPrices);
    }

    ObjectNode showCurrenciesJSON() {
        return JSON.getCurrencyJSON();
    }

    double calculateExchange(String currencyType, double exchangeValue, String exchangeType) {
        return (JSON.getCurrencyJSON().get(currencyType).get(exchangeType).doubleValue()) * exchangeValue;
    }


}
