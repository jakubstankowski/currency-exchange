import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class ConsoleInterfaceTest {
    private Currency currency = new Currency();
    private ConsoleInterface consoleInterface = new ConsoleInterface();


    @Test
    void showConsoleInterface() {
    }

  /*  @Test
  void shouldntAcceptCurrencyTypeIfIsNotExistInArray() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> consoleInterface.getCurrencyType()
        );
        assertEquals("Weight can't be negative!", exception.getMessage());
    }
*/


}