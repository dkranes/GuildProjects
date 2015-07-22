/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterylab.Rev1;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author apprentice
 */
public class App {
     public static void main(String[] args) throws FileNotFoundException, IOException {
        OrderController testRun = new OrderController();
        // matRun = new Materials();
        //Taxes taxRun = new Taxes();
        
        //matRun.loadMatCosts();
        //taxRun.loadTaxes();
        
        testRun.run();
    }
    
}