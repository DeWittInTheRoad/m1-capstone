package com.techelevator;

import com.techelevator.change.Change;
import com.techelevator.view.Menu;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Scanner;

public class VendingMachineCLI {
    private final VendingMachine vendingMachine = new VendingMachine();
    private Logger logger = new Logger();

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

    private final Menu menu;

    private VendingMachineCLI(Menu menu) {
        this.menu = menu;
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

                        menu.feedMoneyMenu(vendingMachine);
                        break;

                    } else if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_FINISH_TRANSACTION)) {
                       vendingMachine.returnChange();
                        break;
                    } else if (purchaseMenuChoiceFromOption.equals(PURCHASE_MENU_SELECT_PRODUCT)) {
                        vendingMachine.displayItems();

                        System.out.println("\nCurrent Money Provided: $" + vendingMachine.getBalance());
                        System.out.println("\nPlease make selection or press X to return to previous menu.");

                        menu.purchaseMenu(vendingMachine);
                    }
                }
            } else if ((choice.equals(MAIN_MENU_OPTION_EXIT))) {
                System.out.println("Goodbye!");
                System.exit(0);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        Menu menu = new Menu(System.in, System.out);
        VendingMachineCLI cli = new VendingMachineCLI(menu);
        cli.run();
    }


}

