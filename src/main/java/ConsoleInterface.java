import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class ConsoleInterface {

    private boolean exit = false;
    private double moneyCount = 0;
    String currencyType;
    private Scanner inputScanner = new Scanner(System.in);
    private Currency currency = new Currency();
    List<String> currencyList = new ArrayList<>();


    public void showConsoleInterface() throws IOException {

        System.out.println("Welcome to standev exchange, this is currency list: ");
        currency.showCurrencyPrice(addToCurrencyList());

        System.out.println("Which currency type you want buy, or sell? ");
        currencyType = inputScanner.nextLine();

       while (!exit) {
            System.out.println("What do you want? buy|sell|exit ");
            String input = inputScanner.nextLine();

            switch (input) {
                case "buy":
                    System.out.println("How many "+currencyType+" you want buy? ");
                    getMoneyCountFromUser();
                    System.out.println("Your order: " + currency.calculateExchange(currencyType, "sell", moneyCount) + " ZŁ");
                    exit = true;
                    break;
                case "sell":
                    System.out.println("How many "+currencyType+" you want sell? ");
                    getMoneyCountFromUser();
                    System.out.println("Your order: " + currency.calculateExchange("EUR", "sell", moneyCount) + " ZŁ");
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
    }

    private List addToCurrencyList(){
        String[] everyCurrencyType = new String[] {"EUR", "USD", "GBP", "CHF"};
        currencyList.addAll(Arrays.asList(everyCurrencyType));
        return currencyList;
    }
    private void getMoneyCountFromUser() {
        try {
            moneyCount = inputScanner.nextDouble();
        } catch (Exception e) {
            throw new IllegalArgumentException("item count should be a number!");
        }
    }

}
