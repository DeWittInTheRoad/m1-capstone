package com.techelevator;

import com.techelevator.items.Item;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Logger {
    private final File outputFile = new File("Log.txt");

    public void logPurchase(String slot, Item item, BigDecimal startingBalance, BigDecimal endingBalance) {
        String event = item.getName() + " " + slot;
        String entry = buildLogEntryString(event, startingBalance, endingBalance);

        printToFile(entry);
    }

    public void logFeed(BigDecimal amountFed, BigDecimal endingBalance) {
        String event = "FEED MONEY";
        String entry = buildLogEntryString(event, amountFed, endingBalance);

        printToFile(entry);
    }

    public void logChange(BigDecimal changeGiven, BigDecimal endingBalance) {
        String event = "GIVE CHANGE";
        String entry = buildLogEntryString(event, changeGiven, endingBalance);

        printToFile(entry);
    }

    private String buildLogEntryString(String event, BigDecimal startingBalance, BigDecimal endingBalance) {
        String date = (new SimpleDateFormat("MM/dd/YYYY hh:mm:ss a").format(new Date()));
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);
        String startingBalanceString = (numberFormat.format(startingBalance.doubleValue()));
        String endingBalanceString = (numberFormat.format(endingBalance.doubleValue()));

        return String.format("%-25s%-25s%-10s%-10s", date, event, startingBalanceString, endingBalanceString);
    }


    private void printToFile(String entry) {
        try (FileOutputStream f = new FileOutputStream(outputFile, true);
             PrintWriter pw = new PrintWriter(f)) {

            pw.println(entry);
            pw.flush();
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("Could not find file");
        }
    }
}
