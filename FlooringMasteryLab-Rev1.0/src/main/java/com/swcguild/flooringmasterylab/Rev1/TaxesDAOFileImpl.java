/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterylab.Rev1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author apprentice
 */
public class TaxesDAOFileImpl implements Taxes{

    final String TAXES_FILE = "Taxes.txt";
    private HashMap<String, Double> taxMap;

    //constructor
    public TaxesDAOFileImpl() {
        taxMap = new HashMap<>(); //we forgot to instantiate this!! 
        
    }

    @Override
    public HashMap loadTaxes() throws FileNotFoundException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(TAXES_FILE)));

        while (sc.hasNextLine()) {
            String currentLine = sc.nextLine();
            String[] currentTokens = currentLine.split(",");

            String currentStateToken = currentTokens[0];
            Double currentRateToken = Double.parseDouble(currentTokens[1]);

            taxMap.put(currentStateToken, currentRateToken);

        }
        return taxMap;  //this method had to return a data structure in order for it to be available to the other classes

    }

}
