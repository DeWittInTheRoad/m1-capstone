package com.techelevator;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Logger {

    public void logPurchase(String slot, Item item, BigDecimal startingBalance){
        BigDecimal endingBalance = startingBalance.subtract(item.getPrice());
        String event = item.getName() + "  " + slot;
        outputToFile(event, startingBalance, endingBalance);
    }

    public void logFeed(BigDecimal amountFed, BigDecimal endingBalance){
        outputToFile("FEED MONEY $"+ amountFed.setScale(2), endingBalance);

    }

    public void logChange(BigDecimal changeGiven, BigDecimal endingBalance){
        outputToFile("GIVE CHANGE $"+changeGiven, endingBalance);

    }

    public void outputToFile(String action, BigDecimal startingBalance, BigDecimal endingBalance){
        File outputFile = new File("Log.txt");

        StringBuilder entry = new StringBuilder();

        entry.append(new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new Date()));
        entry.append(" ");
        entry.append(action);
        entry.append(" $");
        entry.append(startingBalance);
        entry.append(" $");
        entry.append(endingBalance);


        try (FileOutputStream f = new FileOutputStream(outputFile, true);
             PrintWriter pw = new PrintWriter(f)) {

            pw.println(entry);
            pw.flush();
        }
        catch(IOException e){
            System.out.println(e.toString());
            System.out.println("Could not find file");
        }


    }
    public void outputToFile(String action, BigDecimal endingBalance){
        File outputFile = new File("Log.txt");
        StringBuilder entry = new StringBuilder();

        entry.append(new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new Date()));
        entry.append(" ");
        entry.append(action);
        entry.append(" ");
        entry.append(" $");
        entry.append(endingBalance);

        try (FileOutputStream f = new FileOutputStream(outputFile, true);
             PrintWriter pw = new PrintWriter(f)) {

            pw.println(entry);
            pw.flush();
            }
        catch(IOException e){
            System.out.println(e.toString());
            System.out.println("Could not find file");
        }


    }
}
