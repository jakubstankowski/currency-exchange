import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyBeansTest {


    List<String> currencyList = new ArrayList<>();
    private CurrencyBeans currencyBeans = new CurrencyBeans();

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
       // @Mock EmailService emailService;
        currencyList.add("EUR");
        currencyBeans.calculateCurrencies(currencyList);
    }


    @Test
    @DisplayName("Test calculate exchange for sell 1000.0 EUR:")
    void calculateExchange() {
        double sellCurrencyValue =currencyBeans.showCurrenciesJSON().get("EUR").get("sell").asDouble();
        assertEquals(sellCurrencyValue * 1000, currencyBeans.calculateExchange("EUR", 1000, "sell"));
    }
}