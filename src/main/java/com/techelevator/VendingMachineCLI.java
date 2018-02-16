package com.techelevator;

import com.techelevator.change.Change;
import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class VendingMachineCLI {
    private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
    private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
    private static final String MAIN_MENU_OPTION_EXIT = "Exit";
    private static final String PURCHASE_MENU_FEED_MONEY = "Feed Money";
    private static final String PURCHASE_MENU_SELECT_PRODUCT = "Select Product";
    private static final String PURCHASE_MENU_FINISH_TRANSACTION = "Finish Transaction";
    private static final String[] MAIN_MENU_OPTIONS = {MAIN_MENU_OPTION_DISPLAY_ITEMS,
            MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT};
    private static final String[] PURCHASE_MENU_OPTIONS = {PURCHASE_MENU_FEED_MONEY, PURCHASE_MENU_SELECT_PRODUCT,
            PURCHASE_MENU_FINISH_TRANSACTION};
    private final VendingMachine vendingMachine = new VendingMachine();
    private final Logger logger = new Logger();
    private final Menu menu;
    private final Scanner in = new Scanner(System.in);

    private VendingMachineCLI(Menu menu) {
        this.menu = menu;
    }

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }

    private void run() throws IOException {

        vendingMachine.loadInventory();

        while (true) {
            String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

            if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
                vendingMachine.displayItems();

            } else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

                while (true) {
                    System.out.println("Current Money Provided: $" + vendingMachine.getBalance());
                    String purchaseMenuChoiceFromOption = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);

                    if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_FEED_MONEY)) {
                        while (true) {
                            System.out.println("");
                            System.out.println("Current Money Provided: $" + vendingMachine.getBalance());
                            System.out.println("Please insert $1, $2, $5, or $10 dollars or 0 to return to the previous menu.");
                            String userInput = in.nextLine();

                            if (userInput.matches("\\d+")) {
                                if (Integer.valueOf(userInput) == 0) {
                                    break;
                                } else if (Integer.valueOf(userInput) == 1 || Integer.valueOf(userInput) == 2 || Integer.valueOf(userInput) == 5 || Integer.valueOf(userInput) == 10) {
                                    vendingMachine.feedMoney(new BigDecimal(Integer.valueOf(userInput)));
                                    logger.logFeed(new BigDecimal(Integer.valueOf(userInput)), vendingMachine.getBalance());
                                } else {
                                    System.out.println("Not a valid form of currency.");
                                }
                            } else {
                                System.out.println("Not a valid form of currency.");
                            }

                        }

                    } else if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
                        Change change = new Change();
                        BigDecimal changeGiven = vendingMachine.getBalance();

                        System.out.println("");
                        System.out.println(change.makeChange(vendingMachine.getBalance()));

                        vendingMachine.resetBalance();
                        logger.logChange(changeGiven, vendingMachine.getBalance());
                        break;
                    } else if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
                        System.out.println("");
                        System.out.println("Current Money Provided: $" + vendingMachine.getBalance());
                        vendingMachine.displayItems();
                        System.out.println();
                        System.out.println("Please make selection or press X to return to previous menu.");

                        purchaseMenu();
                    }
                }
            } else if ((choice.equals(MAIN_MENU_OPTION_EXIT))) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }

    private void purchaseMenu() {
        String input = in.nextLine().toUpperCase();

        Map<String, List<Item>> itemsInTheMachine = vendingMachine.getItemsInTheMachine();
        boolean containsKey = itemsInTheMachine.containsKey(input);
        if (containsKey || input.equals("X")) {
            if (input.equals("X")) {
            } else if (containsKey) {
                boolean outOfStock = itemsInTheMachine.get(input).isEmpty();
                if (outOfStock) {
                    System.out.println("Item sold out! Please select another item.");
                } else {
                    Item item = itemsInTheMachine.get(input).get(0);
                    logger.logPurchase(input, item, vendingMachine.getBalance());
                    vendingMachine.buyItem(input);
                }
            }
        } else {
            System.out.println("Not a valid slot.");
        }
    }


}

