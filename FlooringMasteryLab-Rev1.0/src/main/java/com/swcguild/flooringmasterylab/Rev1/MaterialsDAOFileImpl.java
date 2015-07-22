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
public class MaterialsDAOFileImpl implements Materials{

    final String PRODUCT_FILE = "Products.txt";
    //whenever we make our fields, we make them private and then we will know if we didn't put in the getters and setters we needed
    
    private Map<String, double[]> materialsMap = new HashMap<String, double[]>(); //we need to restate what our Types are in the second half as well...
    //esp going from Map to HashMap or List to ArrayLIst (these are interfaceds)
    //if we dont do this, it will just assume this is a random object, and make type object/object

    //constructor
    public void taxes() {
        //materialsMap = new HashMap<>();
        //sqFtCosts[0] = 0;
        //sqFtCosts[1] = 0;
    }

    
    @Override
    public void loadMatCosts() throws FileNotFoundException {
        double[] sqFtCosts = new double[2];
        Scanner sc = new Scanner(new BufferedReader(new FileReader(PRODUCT_FILE)));

        while (sc.hasNextLine()) {
           String currentLine = sc.nextLine();
           String [] currentTokens = currentLine.split(",");
            
            
            String currMat = currentTokens[0];
            Double matCostSqFt = Double.parseDouble(currentTokens[1]);
            Double laborCostSqFt = Double.parseDouble(currentTokens[2]);

            sqFtCosts[0] = matCostSqFt;
            sqFtCosts[1] = laborCostSqFt;

            materialsMap.put(currMat, sqFtCosts);

        }

    }

    @Override
    public double[] getMaterial(String key) {
        //this is a "pass through" method that allows us to by pass "get MaterialsMap" and just go directl to the doubl[]

        return materialsMap.get(key);
    }

}

//our Products.txt file has type//materialSqFtCost/laborCostSqFt
