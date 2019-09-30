import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyTest {

    private Currency currency = new Currency();
    private ConsoleInterface consoleInterface = new ConsoleInterface();

    private List<String> currencyList = new ArrayList<>();
    private String[] everyCurrencyType = new String[]{"EUR"};



    @org.junit.jupiter.api.BeforeEach
    void setUp() throws IOException {
        currencyList.addAll(Arrays.asList(everyCurrencyType));
        currency.showCurrencyPrice(currencyList);
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }


    @org.junit.jupiter.api.Test
    @DisplayName("Test calculate exchange for sell 1000.0 EUR:")
    void calculateExchange(){
        assertEquals(currency.returnCurrencyPrices().get("EUR-BUY"), currency.calculateExchange("EUR", "sell", 1000.0));
    }

    @Test
    void shouldShowExceptionWhenCurrencyArrayIsEmpty() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> currency.returnCurrencyPrices());
        assertEquals("Empty currency array!", exception.getMessage());
    }


   /* @Test
    public void shouldTakeUserInput() {

        String input = "add 5";
        ByteArrayInputStream in = new ByteArrayInputStream("My string");
        System.setIn(in);

        System.out.println("in: "+in);


       *//* assertEquals("add 5", inputOutput.getInput());*//*
    }*/


}