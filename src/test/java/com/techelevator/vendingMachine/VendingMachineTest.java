package com.techelevator.vendingMachine;

import com.techelevator.vendingMachine.VendingMachine;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;


public class VendingMachineTest {

    private VendingMachine vendingMachine;

    @Before
    public void setup(){
        vendingMachine = new VendingMachine();
        vendingMachine.loadInventory();
    }
    @Test
    public void add_1_to_balance() {
        vendingMachine.feedMoney(BigDecimal.valueOf(1));

        Assert.assertEquals(1, vendingMachine.getBalance().doubleValue(), 0.05);
    }
    @Test
    public void add_2_to_balance() {
        vendingMachine.feedMoney(BigDecimal.valueOf(2));

        Assert.assertEquals(2, vendingMachine.getBalance().doubleValue(), 0.05);
    }
    @Test
    public void add_5_to_balance() {
        vendingMachine.feedMoney(BigDecimal.valueOf(5));

        Assert.assertEquals(5, vendingMachine.getBalance().doubleValue(), 0.05);
    }
    @Test
    public void add_10_to_balance() {
        vendingMachine.feedMoney(BigDecimal.valueOf(10));

        Assert.assertEquals(10, vendingMachine.getBalance().doubleValue(), 0.05);
    }

    @Test
    public void add_10_to_balance_then_reset() {
        vendingMachine.feedMoney(BigDecimal.valueOf(10));
        vendingMachine.resetBalance();

        Assert.assertEquals(0, vendingMachine.getBalance().doubleValue(), 0.05);
    }

    @Test
    public void add_multiple_dollars_to_balance() {
        vendingMachine.feedMoney(BigDecimal.valueOf(10));
        vendingMachine.feedMoney(BigDecimal.valueOf(1));
        vendingMachine.feedMoney(BigDecimal.valueOf(5));


        Assert.assertEquals(16, vendingMachine.getBalance().doubleValue(), 0.05);
    }

    @Test
    public void try_to_buy_item_not_enough_money(){

        vendingMachine.feedMoney(BigDecimal.valueOf(0));
        String slot = "A1";
        vendingMachine.buyItem(slot);

        Assert.assertEquals(5, vendingMachine.getItemsInTheMachine().get("A1").size());
    }

    @Test
    public void buyItem(){
        vendingMachine.loadInventory();
        vendingMachine.feedMoney(BigDecimal.valueOf(10));
        String slot = "A1";
        vendingMachine.buyItem(slot);

        Assert.assertEquals(4, vendingMachine.getItemsInTheMachine().get("A1").size());
    }

    @Test
    public void buy_sold_out(){
        vendingMachine.loadInventory();
        vendingMachine.feedMoney(BigDecimal.valueOf(100));
        String slot = "A1";
        vendingMachine.buyItem(slot);
        vendingMachine.buyItem(slot);
        vendingMachine.buyItem(slot);
        vendingMachine.buyItem(slot);
        vendingMachine.buyItem(slot);
        vendingMachine.buyItem(slot);
        Assert.assertEquals(0, vendingMachine.getItemsInTheMachine().get("A1").size());
    }

}