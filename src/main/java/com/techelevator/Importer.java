package com.techelevator;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.io.File;

public class Importer {

    private final LinkedHashMap<String, List<Item>> itemsInTheMachine = new LinkedHashMap<>();
    private String[] csvItems;



    public LinkedHashMap<String, List<Item>> getItemsInTheMachine() {
        return itemsInTheMachine;
    }


    public void readCSVFile() throws FileNotFoundException {
        StringBuilder line = new StringBuilder();
        File inputFile = new File("vendingmachine.csv");

        try (Scanner in = new Scanner(inputFile)) {
            while (in.hasNextLine()) {
                line.append(in.nextLine()).append("\n");
            }
        }
        csvItems = line.toString().split("[\n]");

    }

    public void createMap() {

        for (String line : csvItems) {

            String[] inputFileItems = line.split("[|]");
            List<Item> itemList = new ArrayList<>();
            String slot = inputFileItems[0];
            String name = inputFileItems[1];
            BigDecimal price = new BigDecimal(inputFileItems[2]);
            System.out.println(slot);
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



    public void loadInventory() throws FileNotFoundException {
        readCSVFile();
        createMap();
    }


}
