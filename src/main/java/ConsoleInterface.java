import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.*;

public class ConsoleInterface {


    private boolean exit = false;
    private double moneyCount = 0;
    private String currencyType;
    private Scanner inputScanner = new Scanner(System.in);
    private Currency currency = new Currency();
    private List<String> currencyList = new ArrayList<>();
    private CurrencyBeans currencyBeans = new CurrencyBeans();

    void showConsoleInterface() throws IOException {
        System.out.println("Welcome to standev exchange, this is currency list: ");
        currencyBeans.calculateCurrencies(currencyList());

        System.out.println("CURRENCIES: " + currencyBeans.showCurrenciesJSON());
        System.out.println("Which currency type you want buy, or sell? ");

        getCurrencyType();

        while (!exit) {
            System.out.println("What do you want? buy|sell|exit ");
            String input = inputScanner.nextLine();

            switch (input) {
                case "buy":
                    System.out.println("How many " + currencyType + " you want buy? ");
                    getMoneyCount();
                    System.out.println("Your order: " + currencyBeans.calculateExchange(currencyType, moneyCount, "buy"));
                    exit = true;
                    break;
                case "sell":
                    System.out.println("How many " + currencyType + " you want sell? ");
                    getMoneyCount();
                    System.out.println("Your order: " + currencyBeans.calculateExchange(currencyType, moneyCount, "sell"));
                    exit = true;
                    break;
                case "list":
                    System.out.println("CURRENCIES: " + currencyBeans.showCurrenciesJSON());
                    break;
                case "exit":
                    System.out.println("Thanks for using my program! Standev 2019");
                    exit = true;
                    break;
            }

        }
        inputScanner.close();


    }

    public List<String> currencyList() {
        String[] everyCurrencyType = new String[]{"EUR", "USD", "CHF"};
        currencyList.addAll(Arrays.asList(everyCurrencyType));
        return currencyList;
    }

    private void getMoneyCount() {
        try {
            moneyCount = inputScanner.nextDouble();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Money count should be a double number!");
        }
    }

    private void getCurrencyType() {
        currencyType = inputScanner.nextLine();
        if (!currencyList.contains(currencyType)) {
            throw new IllegalArgumentException("This currency is not exist, please chose correct one!");
        }
    }


}
