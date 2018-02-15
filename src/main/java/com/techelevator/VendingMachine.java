package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.io.File;

public class VendingMachine {

    private final LinkedHashMap<String, List<Item>> itemsInTheMachine = new LinkedHashMap<>();
    private BigDecimal balance = new BigDecimal(0);
    private String[] csvItems;


    public BigDecimal getBalance() {
        return balance.setScale(2);
    }

    public void buyItem(String slot) {
        List<Item> itemListStockCount = itemsInTheMachine.get(slot);
        if (outOfStock(itemListStockCount)) {
            System.out.println("SOLD OUT");
        } else {
            Item item = itemListStockCount.get(0);
            if (balance.doubleValue() < item.getPrice().doubleValue())
                System.out.println("Please Insert More Money.");

            else {
                balance = balance.subtract(item.getPrice());
                System.out.println(item.getConsumedMessage());
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
                System.out.println(String.format("%-5s%-19s", itemSlot, "SOLD OUT"));
            }
        }
    }

    public Map<String, List<Item>> getItemsInTheMachine() {
        return itemsInTheMachine;
    }


    private void readCSVFile() throws FileNotFoundException {
        StringBuilder line = new StringBuilder();
        File inputFile = new File("vendingmachine.csv");

        try (Scanner in = new Scanner(inputFile)) {
            while (in.hasNextLine()) {
                line.append(in.nextLine()).append("\n");
            }
        }
        csvItems = line.toString().split("[\n]");

    }

    private void createMap() {

        for (String line : csvItems) {

            String[] inputFileItems = line.split("[|]");
            List<Item> itemList = new ArrayList<>();
            String slot = inputFileItems[0];
            String name = inputFileItems[1];
            BigDecimal price = new BigDecimal(inputFileItems[2]);
            if (slot.startsWith("A")) {
                Item item = new Chips(name, price);
                itemLoader(itemList, slot, item);
            } else if (slot.startsWith("B")) {
                Item item = new Candy(name, price);
                itemLoader(itemList, slot, item);
            } else if (slot.startsWith("C")) {
                Item item = new Beverage(name, price);
                itemLoader(itemList, slot, item);
            } else if (slot.startsWith("D")) {
                Item item = new Gum(name, price);
                itemLoader(itemList, slot, item);
            }

        }
    }

    private void itemLoader(List<Item> itemList, String slot, Item item) {
        for (int i = 0; i < 5; i++) {
            itemList.add(item);
            itemsInTheMachine.put(slot, itemList);
        }
    }

    public void resetBalance() {
        balance = new BigDecimal(0);
    }

    public void loadInventory() throws FileNotFoundException {
        readCSVFile();
        createMap();
    }


}