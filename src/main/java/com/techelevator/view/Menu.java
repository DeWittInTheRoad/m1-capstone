package com.techelevator.view;

import com.techelevator.vendingMachine.VendingMachine;
import com.techelevator.items.Item;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {

    private final PrintWriter out;
    private final Scanner in;


    public Menu(InputStream input, OutputStream output) {
        out = new PrintWriter(output);
        in = new Scanner(input);
    }

    public Object getChoiceFromOptions(Object[] options) {
        Object choice = null;
        while (choice == null) {
            displayMenuOptions(options);
            choice = getChoiceFromUserInput(options);
        }
        return choice;
    }

    private Object getChoiceFromUserInput(Object[] options) {
        Object choice = null;
        String userInput = in.nextLine();
        try {
            int selectedOption = Integer.valueOf(userInput);
            if (selectedOption > 0 && selectedOption <= options.length) {
                choice = options[selectedOption - 1];
            }
        } catch (NumberFormatException e) {
            // eat the exception, an error message will be displayed below since choice will be null
        }
        if (choice == null) {
            out.println("\n*** " + userInput + " is not a valid option ***\n");
        }
        return choice;
    }

    private void displayMenuOptions(Object[] options) {
        out.println();
        for (int i = 0; i < options.length; i++) {
            int optionNum = i + 1;
            out.println(optionNum + ") " + options[i]);
        }
        out.print("\nPlease choose an option >>> ");
        out.flush();
    }

    public void purchaseMenu(VendingMachine vendingMachine) { //Passing Vending Machine in best practise?
        String input = in.nextLine().toUpperCase();
        Map<String, List<Item>> itemsInTheMachine = vendingMachine.getItemsInTheMachine();
        boolean containsKey = itemsInTheMachine.containsKey(input);

        if (containsKey || input.equals("X")) {
            if (containsKey) {
                vendingMachine.buyItem(input);
            }
        } else {
            System.out.println("Not a valid slot.");
        }
    }

    public void feedMoneyMenu(VendingMachine vendingMachine) {
        while (true) {
            System.out.println("");
            System.out.println("Current Money Provided: " + vendingMachine.formattedBalanceToCurrency());
            System.out.println("Please insert $1, $2, $5, or $10 dollars or 0 to return to the previous menu.");
            String userInput = in.nextLine();

            if (userInput.matches("\\d+")) {  //Checks to make sure it is an int
                Integer currency = Integer.valueOf(userInput);
                boolean isRealDenomination = (currency == 1 || currency == 2 || currency == 5 || currency == 10);

                if (isRealDenomination) {
                    vendingMachine.feedMoney(new BigDecimal(currency));
                } else if (currency == 0) {
                    break;
                } else {
                    System.out.println("Not a valid form of currency.");
                }

            } else {
                System.out.println("Not a valid form of currency.");
            }
        }
    }


}
