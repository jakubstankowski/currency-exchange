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


    public void showConsoleInterface() throws IOException {

        System.out.println("Welcome to standev exchange, this is currency list: ");

        try {
            currency.showCurrencyPrice(addToCurrencyList());
            System.out.println("Which currency type you want buy, or sell? ");
            getCurrencyType();

            while (!exit) {
                System.out.println("What do you want? buy|sell|exit ");
                String input = inputScanner.nextLine();

                switch (input) {
                    case "buy":
                        System.out.println("How many " + currencyType + " you want buy? ");
                        getMoneyCount();
                        System.out.println("Your order: " + currency.calculateExchange(currencyType, "sell", moneyCount) + " ZŁ");
                        exit = true;
                        break;
                    case "sell":
                        System.out.println("How many " + currencyType + " you want sell? ");
                        getMoneyCount();
                        System.out.println("Your order: " + currency.calculateExchange(currencyType, "sell", moneyCount) + " ZŁ");
                        exit = true;
                        break;
                    case "list":
                        System.out.println("This is currency list: ");
                        currency.showCurrencyPrice(addToCurrencyList());
                        break;
                    case "exit":
                        System.out.println("Thanks for using my program! Standev 2019");
                        exit = true;
                        break;
                }

            }
            inputScanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public List addToCurrencyList() {
        String[] everyCurrencyType = new String[]{"EUR", "USD", "GBP", "CHF", "HKD"};
        currencyList.addAll(Arrays.asList(everyCurrencyType));
        return currencyList;
    }

    public void getMoneyCount() {
        try {
            moneyCount = inputScanner.nextDouble();
        } catch (InputMismatchException e) {
            throw new InputMismatchException("Money count should be a double number!");
        }
    }

    public void getCurrencyType() {
        currencyType = inputScanner.nextLine();
        if (!currencyList.contains(currencyType)) {
            throw new IllegalArgumentException("This currency is not exist, please chose correct one!");
        }
    }


}
