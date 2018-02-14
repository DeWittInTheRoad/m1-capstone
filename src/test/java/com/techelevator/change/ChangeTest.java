package com.techelevator.change;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class ChangeTest {
    private Change changeMaker;

    @Before
    public void setUp() throws Exception {
        changeMaker = new Change();
    }

    @Test
    public void makeChange() {


        Assert.assertEquals("1 Dime returned.", changeMaker.makeChange(new BigDecimal(0.10)));
        Assert.assertEquals("2 Quarters returned.", changeMaker.makeChange(new BigDecimal(0.50)));
        Assert.assertEquals("1 Dime returned.", changeMaker.makeChange(new BigDecimal(0.10)));
    }

    @Test
    public void make_multiple_denominations() {
        Assert.assertEquals("2 Quarters 1 Nickel returned.", changeMaker.makeChange(new BigDecimal(0.55)));
        Assert.assertEquals("2 Quarters 2 Dimes returned.", changeMaker.makeChange(new BigDecimal(0.70)));
    }

}