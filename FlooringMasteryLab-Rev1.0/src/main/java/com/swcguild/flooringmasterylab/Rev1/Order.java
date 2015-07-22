/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringmasterylab.Rev1;

/**
 *
 * @author apprentice
 */
public class Order {
    //int orderNum;
    String lastName;
    String state;
    double taxRate;
    String flooringType;
    double area;
    double materialCostSqFt;
    double laborCostSqFt;
    double totalMatCost;
    double totalLaborCost;
    double taxAdded;
    double totalCost;
    private String orderNotes;
    
    int id;
    //static int counter = 0; //we want this to be available to the DAO class
                            /*
                              public void setOrderNum(int orderNum) {
                                  this.orderNum = orderNum;
                              }
                              */
    //constructor
    public Order (String lastName, String flooringType, String state, double area){
    this.lastName = lastName;
    this.flooringType = flooringType;
    this.state = state;
    this.area = area;
    //this.id = ++counter;
    }
                            /*
                                public int getOrderNum() {
                                    return orderNum;
                                }
                             */   
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
   

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getFlooringType() {
        return flooringType;
    }

    public void setFlooringType(String flooringType) {
        this.flooringType = flooringType;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(double taxRate) {
        this.taxRate = taxRate;
    }

    public double getMaterialCostSqFt() {
        return materialCostSqFt;
    }

    public void setMaterialCostSqFt(double materialCostSqFt) {
        this.materialCostSqFt = materialCostSqFt;
    }

    public double getLaborCostSqFt() {
        return laborCostSqFt;
    }

    public void setLaborCostSqFt(double laborCostSqFt) {
        this.laborCostSqFt = laborCostSqFt;
    }

    public double getTotalMatCost() {
        return totalMatCost;
    }

    public void setTotalMatCost(double totalMatCost) {
        this.totalMatCost = totalMatCost;
    }

    public double getTotalLaborCost() {
        return totalLaborCost;
    }

    public void setTotalLaborCost(double totalLaborCost) {
        this.totalLaborCost = totalLaborCost;
    }

    public double getTaxAdded() {
        return taxAdded;
    }

    public void setTaxAdded(double taxAdded) {
        this.taxAdded = taxAdded;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public String getOrderNotes() {
        return orderNotes;
    }

    public void setOrderNotes(String orderNotes) {
        this.orderNotes = orderNotes;
    }


    
    
    
    
    
}
