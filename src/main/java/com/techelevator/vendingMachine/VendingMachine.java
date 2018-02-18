package com.techelevator.vendingMachine;

import com.techelevator.change.Change;
import com.techelevator.items.Item;
import com.techelevator.salesReport.SalesReport;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VendingMachine {

    private LinkedHashMap<String, List<Item>> itemsInTheMachine = new LinkedHashMap<>();
    private final Logger logger = new Logger();
    private SalesReport salesReport = new SalesReport();
    private BigDecimal balance = new BigDecimal(0);

    public BigDecimal getBalance() {
        return balance;
    }

    public void buyItem(String slot) {
        List<Item> itemListStockCount = itemsInTheMachine.get(slot);
        if (outOfStock(itemListStockCount)) {
            System.out.println("SOLD OUT");
        } else {
            Item item = itemListStockCount.get(0);
            BigDecimal itemPrice = item.getPrice();
            if (balance.doubleValue() < itemPrice.doubleValue())
                System.out.println("Please Insert More Money.");

            else {
                System.out.println("\nConsuming " + item.getName() + "..." + "\n" + item.getConsumedMessage() + "\n");
                BigDecimal startingBalance = balance;

                balance = balance.subtract(itemPrice);

                logger.logPurchase(slot, item, startingBalance, balance);
                salesReport.updateBalance(itemPrice);
                salesReport.updateInventory(item.getName());
                itemListStockCount.remove(0);
            }
        }
    }

    private boolean outOfStock(List<Item> items) {
        return items.isEmpty();
    }

    public void feedMoney(BigDecimal money) {
        balance = balance.add(money);
        System.out.println("Inserted $" + money.toString() + " dollars.");
        logger.logFeed(money, balance);

    }

    public void displayItems() {
        System.out.println();
        System.out.println(String.format("%-5s%-19s%10s%10s", "Slot", "Name", "Price", "Count"));
        System.out.println("---------------------------------------------");
        for (Map.Entry<String, List<Item>> entry : itemsInTheMachine.entrySet()) {
            if (!outOfStock(entry.getValue())) {
                String itemSlot = entry.getKey();
                String itemName = entry.getValue().get(0).getName();
                String itemPrice = "$" + entry.getValue().get(0).getPrice().toString();
                String itemCount = String.valueOf(entry.getValue().size());

                System.out.println(String.format("%-5s%-19s%10s%10s", itemSlot, itemName, itemPrice, itemCount));
            } else {
                String itemSlot = entry.getKey();
                System.out.println(String.format("%-15s%7s", itemSlot, "SOLD OUT")); //Prevents Array Out Of Bounds error
            }
        }
        System.out.println("---------------------------------------------");
    }

    public Map<String, List<Item>> getItemsInTheMachine() {
        return itemsInTheMachine;
    }


    public void resetBalance() {
        balance = new BigDecimal(0);
    }


    public void loadInventory() {
        Importer importer = new Importer();
        itemsInTheMachine = importer.passImportMapToVendingMachine();
    }

    public void returnChange() {
        if (balance.doubleValue() > 0) {
            Change change = new Change();
            BigDecimal changeGiven = getBalance();

            System.out.println("\n" + change.makeChange(getBalance()));

            resetBalance();
            logger.logChange(changeGiven, getBalance());
        }
    }

    public String formattedBalanceToCurrency() {
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return numberFormat.format(balance.doubleValue());
    }

}
