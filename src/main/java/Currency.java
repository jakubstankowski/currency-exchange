import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


class Currency {
    public final static String API_URL = "https://api.ratesapi.io/api/latest?base=PLN";
    private Map<String, Double> currencyPrices = new HashMap<>();
    private ObjectMapper objectMapper = new ObjectMapper();


    public void showCurrencyPrice(List currencyType) throws IOException {
        System.out.println(currencyType);

        final double sellSpread = 1.05;
        final double buySpread = 0.995;

        int currencyCount = 0;

        while (currencyCount < currencyType.size()) {
            double basicPrice = Double.parseDouble(String.valueOf(objectMapper.readTree(getRequest(API_URL)).get("rates").get((String) currencyType.get(currencyCount))));
            System.out.println("Basic exchange " + currencyType.get(currencyCount) + " price: " + (Math.round((1 / (basicPrice)) * 100.0) / 100.0));
            double finallSellPrice = Math.round((1 / (basicPrice * buySpread)) * 100.0) / 100.0;
            double finallBuyPrice = Math.round((1 / (basicPrice * sellSpread)) * 100.0) / 100.0;
            currencyPrices.put(((String) currencyType.get(currencyCount)).concat("-SELL"), finallSellPrice);
            currencyPrices.put(((String) currencyType.get(currencyCount)).concat("-BUY"), finallBuyPrice);
            currencyCount++;
        }
        for (Map.Entry<String, Double> entry : this.currencyPrices.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    double calculateExchange(String currencyType, String exchangeType, double exchangeValue) {
        double finalExchangePrice = 0;
        if (exchangeType.equals("buy")) {
            finalExchangePrice = exchangeValue * currencyPrices.get(currencyType.concat("-SELL"));
        } else {
            finalExchangePrice = exchangeValue * currencyPrices.get(currencyType.concat("-BUY"));
        }
        return finalExchangePrice;
    }

    double returnSellValueForTest() {
        return currencyPrices.get("EUR-SELL");
    }

    private static String streamToString(InputStream inputStream) {
        String text = new Scanner(inputStream, "UTF-8").useDelimiter("\\Z").next();
        return text;
    }

    private String getRequest(String urlQueryString) {
        String json = null;
        try {
            URL url = new URL(urlQueryString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setInstanceFollowRedirects(false);
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.connect();
            InputStream inStream = connection.getInputStream();
            json = streamToString(inStream); // input stream to string
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return json;
    }

}
