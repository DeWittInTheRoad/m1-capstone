package com.techelevator.salesReport;

import java.math.BigDecimal;
import java.util.LinkedHashMap;


public class SalesReport {

    private static LinkedHashMap<String, Integer> salesReportMap = new LinkedHashMap<>();
    private static double totalSales;
    private SalesReportReader salesReportReader = new SalesReportReader();

    public static LinkedHashMap<String, Integer> getSalesReportMap() {
        return salesReportMap;
    }

    public static double getTotalSales() {
        return totalSales;
    }

    public void setupSalesReport(){
        salesReportReader.parseSalesReport();
        salesReportMap = salesReportReader.getSalesReportMap();
        totalSales = salesReportReader.getTotalSales();
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

}
