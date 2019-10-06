import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class API {

    private final static String API_URL = "https://api.ratesapi.io/api/latest?base=PLN";
    private JsonNode allCurrencyListFromAPI;
    private ObjectMapper objectMapper = new ObjectMapper();


    JsonNode getAllCurrencyListFromAPI() {
        return allCurrencyListFromAPI;
    }

    double getChoiceCurrencyValueFromAPI(String currencyName) {
        return  Math.round((1/getAllCurrencyListFromAPI().get("rates").get(currencyName).doubleValue()) * 100.0) / 100.0;
    }


    void setAllCurrencyListFromAPI() throws IOException {
        try {
            this.allCurrencyListFromAPI = objectMapper.readTree(getRequest(API_URL));
        } catch (IOException e) {
            throw new IOException("IO problem try again later!");
        }
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
