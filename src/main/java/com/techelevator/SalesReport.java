package com.techelevator;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

public class SalesReport {

    private static LinkedHashMap<String, Integer> salesReportMap = new LinkedHashMap<>();
    private static double totalSales;
    private final File inputFile = new File("SalesReport.txt");
    private final File outputFile = new File("SalesReport.txt");



    public void parseSalesReport(){
        Importer importer = new Importer();
        String[] salesReportItems = importer.parseInputFileByLine(inputFile);
        createMap(salesReportItems);
        createTotalSalesVariable(salesReportItems);
    }

    private void createMap(String[] salesReportItems) {
        for (String line : salesReportItems) {
            if (!line.startsWith("*") && (!line.isEmpty())) {
                String[] inputFileItems = line.split("[|]");
                String name = inputFileItems[0];
                Integer inventoryCount = Integer.parseInt(inputFileItems[1]);

                salesReportMap.put(name, inventoryCount);
            }

        }
    }

    private void createTotalSalesVariable(String[] salesReportItems) {
        for (String line : salesReportItems) {
            if (line.startsWith("*")) {
                String[] totalSalesGetter = line.split("[$]");
                totalSales = Double.parseDouble(totalSalesGetter[1].trim());
            }
        }
    }


    public void updateBalance(BigDecimal balance) {
        totalSales += balance.doubleValue();
    }

    public void updateInventory(String itemName) {
        if (salesReportMap.containsKey(itemName)) {
            int currentValue = salesReportMap.get(itemName);
            salesReportMap.put(itemName, ++currentValue);
        }
    }


    public void printToFile() {
        try (FileOutputStream f = new FileOutputStream(outputFile, false);
             PrintWriter pw = new PrintWriter(f)) {
            for (Map.Entry<String, Integer> entry : salesReportMap.entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                pw.println(key + "|" + value);
                pw.flush();
            }
            pw.println();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

            pw.println("**TOTAL SALES** " + numberFormat.format(totalSales));
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("Could not find file");
        }
    }


}
