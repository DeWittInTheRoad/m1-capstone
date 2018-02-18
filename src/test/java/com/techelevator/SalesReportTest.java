package com.techelevator;

import com.techelevator.salesReport.SalesReport;
import org.junit.Before;

public class SalesReportTest {
    private SalesReport salesReport;

    @Before
    public void setUp(){
        salesReport = new SalesReport();
    }
//    @Test
//    public void get_total_sales() {
//        System.out.println(salesReport.getTotalSales());
//    }
//    @Test
//    public void add_75_cents() {
//        salesReport.updateBalance(new BigDecimal(.75));
//        Assert.assertEquals(.75, salesReport.getTotalSales(), 0.0);
//    }
//    @Test
//    public void add_1_dollar() {
//        salesReport.updateBalance(new BigDecimal(1));
//        Assert.assertEquals(1, salesReport.getTotalSales(), 0.0);
//    }
//    @Test
//    public void add_5_dollars() {
//        salesReport.updateBalance(new BigDecimal(5));
//        Assert.assertEquals(5, salesReport.getTotalSales(), 0.0);
//    }
}