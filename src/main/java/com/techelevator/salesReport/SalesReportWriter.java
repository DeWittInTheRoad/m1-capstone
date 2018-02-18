package com.techelevator.salesReport;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class SalesReportWriter {

    private final File salesReportFile = new File("salesReport.txt");

    public void printToFile() {
        try (FileOutputStream f = new FileOutputStream(salesReportFile, false);
             PrintWriter pw = new PrintWriter(f)) {
            for (Map.Entry<String, Integer> entry : SalesReport.getSalesReportMap().entrySet()) {
                String key = entry.getKey();
                int value = entry.getValue();
                pw.println(key + "|" + value);
                pw.flush();
            }
            pw.println();
            NumberFormat numberFormat = NumberFormat.getCurrencyInstance(Locale.US);

            pw.println("**TOTAL SALES** " + numberFormat.format(SalesReport.getTotalSales()));
        } catch (IOException e) {
            System.out.println(e.toString());
            System.out.println("Could not find file");
        }
    }
    }
