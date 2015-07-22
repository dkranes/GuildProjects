/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterylab.Rev1;

//import static com.swcguild.flooringmasterylab.Order.counter;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author apprentice
 */
public class OrderFactory {
    
    double area;
    HashMap<String, Double> taxMap;
    HashMap<String, double []> materialsMap;
    
    MaterialsDAOFileImpl matObjRef = new MaterialsDAOFileImpl();
    TaxesDAOFileImpl taxObjRef = new TaxesDAOFileImpl();
    
   
    //constructor--default
    public OrderFactory() {
        try {
            matObjRef.loadMatCosts();
             taxMap = taxObjRef.loadTaxes();
             
        } catch (FileNotFoundException ex) {
            Logger.getLogger(OrderFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
    }

    public Order createFinalOrder(String lastName, double length, double width, String flooringType, String state){
        
        area = calculateArea(length, width);
        //we have enough to instantiate
        Order newOrder = new Order(lastName, flooringType, state, area);  //// are we creating the order object twice in this chain?  once here and then again when we call this method in conroller?

        //need to get data from the MaterialsDAOFileImpl dataMap corresponding to the flooring type
        //double [] tempArray = new double [2];
        //tempArray =  matObjRef.materialsMap.get(flooringType);
        //double [] tempArray = materialsMap.get(flooringType);
        double materialCostSqFt = matObjRef.getMaterial(flooringType)[0]; //.get(flooringType)[0];
        double laborCostSqFt = matObjRef.getMaterial(flooringType)[1];

        //need to get tax rate data according to state
        double taxRate = taxMap.get(state);

        //need to run some calculations to add the following properties to the object
        double totalLaborCost = laborCost(area, laborCostSqFt);
        double totalMatCost = calculateMaterials(area, materialCostSqFt);
        double orderTotal = calcOrderTotal(totalLaborCost, totalMatCost, state);

        //set remaining fields to order object
        newOrder.setLaborCostSqFt(area);
        newOrder.setMaterialCostSqFt(area);
        newOrder.setTaxRate(taxRate);
        newOrder.setTaxAdded(taxRate * orderTotal);
        newOrder.setTotalLaborCost(totalLaborCost);
        newOrder.setTotalMatCost(totalMatCost);
        newOrder.setTotalCost(orderTotal);
        
        //call method to check last id in current Hashmap
        
        //newOrder.setId(counter);
        return newOrder;
        
    
    }
    
    public Order createEditedOrder(Order object){
        
        area = object.getArea();
        
        double materialCostSqFt = matObjRef.getMaterial(object.getFlooringType())[0]; //.get(flooringType)[0];
        double laborCostSqFt = matObjRef.getMaterial(object.getFlooringType())[1];

        //need to get tax rate data according to state
        double taxRate = taxMap.get(object.getState());

        //need to run some calculations to add the following properties to the object
        double totalLaborCost = laborCost(area, laborCostSqFt);
        double totalMatCost = calculateMaterials(area, materialCostSqFt);
        double orderTotal = calcOrderTotal(totalLaborCost, totalMatCost, object.getState());

        //set remaining fields to order object
        object.setLaborCostSqFt(area);
        object.setMaterialCostSqFt(area);
        object.setTaxRate(taxRate);
        object.setTaxAdded(taxRate * orderTotal);
        object.setTotalLaborCost(totalLaborCost);
        object.setTotalMatCost(totalMatCost);
        object.setTotalCost(orderTotal);
        
        return object;
    
    }
    
    
    
    public double calculateArea(double length, double width){
      area = length*width;
      return area;
    
    }
    
    public double calculateMaterials(double area, double materialCostSqFt){
        double currentMatCost = area*materialCostSqFt; 
        return currentMatCost;
        
    
    }
    
    public double laborCost(double area, double laborCostSqFt){
    double currentLaborCost=area*laborCostSqFt;
    return currentLaborCost;
    
    }
    
    public double calcOrderTotal(double currentLaborCost, double currentMatCost, String state){
        
        
        double orderTotal = (currentLaborCost + currentMatCost)*(taxMap.get(state));
                
        return orderTotal;        
                
    }
    
}
