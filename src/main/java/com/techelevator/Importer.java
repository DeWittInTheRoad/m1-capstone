package com.techelevator;

import com.techelevator.items.*;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class Importer {


    public String[] parseInputFileByLine(File inputFile) {
        StringBuilder line = new StringBuilder();
        try (Scanner in = new Scanner(inputFile)) {
            while (in.hasNextLine()) {
                line.append(in.nextLine()).append("\n");
            }

        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("Could not " + inputFile + " file");
        }
        return line.toString().split("[\n]");

    }

    private LinkedHashMap<String,List<Item>> createInventoryMap(String[] parsedFile) {
        LinkedHashMap<String, List<Item>> itemsInTheMachine = new LinkedHashMap<>();
        for (String line : parsedFile) {

            String[] inputFileItems = line.split("[|]");
            List<Item> itemList = new ArrayList<>();
            String slot = inputFileItems[0];
            String name = inputFileItems[1];
            BigDecimal price = new BigDecimal(inputFileItems[2]);
            if (slot.startsWith("A")) {
                Item item = new Chips(name, price);
                for (int i = 0; i < 5; i++) {
                    itemList.add(item);
                    itemsInTheMachine.put(slot, itemList);
                }
            } else if (slot.startsWith("B")) {
                Item item = new Candy(name, price);
                for (int i = 0; i < 5; i++) {
                    itemList.add(item);
                    itemsInTheMachine.put(slot, itemList);
                }
            } else if (slot.startsWith("C")) {
                Item item = new Beverage(name, price);
                for (int i = 0; i < 5; i++) {
                    itemList.add(item);
                    itemsInTheMachine.put(slot, itemList);
                }
            } else if (slot.startsWith("D")) {
                Item item = new Gum(name, price);
                for (int i = 0; i < 5; i++) {
                    itemList.add(item);
                    itemsInTheMachine.put(slot, itemList);
                }
            }

        }
        return itemsInTheMachine;
    }

//    private void itemLoader(List<Item> itemList, String slot, Item item) {
//        for (int i = 0; i < 5; i++) {
//            itemList.add(item);
//            itemsInTheMachine.put(slot, itemList);
//        }
//    }


    public LinkedHashMap<String,List<Item>> importSetup() {
        String[] parsedFile = parseInputFileByLine(new File("vendingmachine.csv"));
        return createInventoryMap(parsedFile);
    }


}
