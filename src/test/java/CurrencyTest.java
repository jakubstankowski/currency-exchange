import org.junit.jupiter.api.DisplayName;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    private Currency currency = new Currency();
    private ConsoleInterface consoleInterface = new ConsoleInterface();

    private List<String> currencyList = new ArrayList<>();

    @org.junit.jupiter.api.BeforeEach
    void setUp() {


    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void showCurrencyPrice() {
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Test calculate exchange for sell 1000.0 EUR:")
    void calculateExchange() throws IOException {
        String[] everyCurrencyType = new String[]{"EUR"};
        currencyList.addAll(Arrays.asList(everyCurrencyType));

        try {
            currency.showCurrencyPrice(currencyList);
            consoleInterface.addToCurrencyList();
            assertEquals(currency.returnSellValueForTest()*1000, currency.calculateExchange("EUR", "sell", 1000.0));
        } catch (IOException e) {
            throw new IOException();
        }
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Test calculate exchange for sell 1000.0 EUR:")
    void returnSellValueForTest(){


    }

}