package com.techelevator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class ImporterTest {

    private Importer importer;
    String[] csvItems;

    @Before
    public void setup() throws IOException {
        importer = new Importer();
    }

        @Test
    public void read_csv_file() throws IOException{
            importer.readCSVFile();
            importer.createMap();
            importer.loadInventory();

            System.out.println(csvItems);
            System.out.println(importer.getItemsInTheMachine());
        }
    }