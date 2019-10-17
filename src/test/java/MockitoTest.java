import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class MockitoTest {
    CurrencyBeans currencyBeans = Mockito.mock(CurrencyBeans.class);



    JSON jsonMock = Mockito.mock(JSON.class);


    private List<String> currencyList = new ArrayList<>();

    @BeforeEach
    void setUp() {

    }

    @DisplayName("This test check how many times funcion calculate currencies start")
    @Test
    public void shouldCheckHowManyTimesFunctionRun() throws IOException {
        currencyBeans.calculateCurrencies(currencyList);
        verify(currencyBeans, times(1)).calculateCurrencies(this.currencyList);
    }

    @org.junit.jupiter.api.Test
    void shouldReturnCorrectCurrencyJSONValue() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode currencyPrices = mapper.createObjectNode();
        currencyPrices.put("basic", 4.1);
        currencyPrices.put("sell", 4.1);
        currencyPrices.put("buy", 4.1);

        when(jsonMock.getCurrencyJSON()).thenReturn(currencyPrices);
        Assert.assertEquals(currencyPrices, jsonMock.getCurrencyJSON());

    }



}
