/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.springmvcwebapp.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author apprentice
 */
@Controller
public class InterCalcController {

    private double originalBalance;
    private double startingBalance;
    private double intRate;
    private double numYears;
    private double numPeriods;
    private String message;
    private double totalInterest;
    private double newBalance;

    @RequestMapping(value = "/intercalc", method = RequestMethod.GET)
    public String doGet() {
        return "intercalc";
    }

    @RequestMapping(value = "/intercalc", method = RequestMethod.POST)
    public String doPost(HttpServletRequest request, Model model) {
        
        try{
        originalBalance = Double.parseDouble(request.getParameter("myAnswer"));
        startingBalance = originalBalance;
        intRate = Double.parseDouble(request.getParameter("myRate"));
        numYears = Double.parseDouble(request.getParameter("myYears"));
        numPeriods = Double.parseDouble(request.getParameter("myPeriods"));
        message = "";
        //totalInterest;
        //newBalance;
        //List<Map> annualInterest = new ArrayList<>();
        Map yearMap = new HashMap<>();
        DecimalFormat df = new DecimalFormat("#.00");

        int yearCount = 0;

        do {
            newBalance = originalBalance * (Math.pow(1 + ((intRate * .01) / numPeriods), (numPeriods)));
            yearCount++;

            double interestPerYear = newBalance - originalBalance;

            String interestPerYearString = df.format(interestPerYear);

            yearMap.put(yearCount, interestPerYearString);

            originalBalance = newBalance;

        } while (yearCount <= (numYears - 1));
        totalInterest = newBalance - startingBalance;
        //annualInterest.add(yearMap);
        String annualInterestString = yearMap.toString().replace("{", "").replace("}", "").trim();

        model.addAttribute("originalBalance", df.format(startingBalance));
        model.addAttribute("newBalance", df.format(newBalance));
        model.addAttribute("interestRate", intRate);
        model.addAttribute("interestEarned", df.format(totalInterest));
        model.addAttribute("years", df.format(numYears));
        model.addAttribute("periods", df.format(numPeriods));
        model.addAttribute("annualInterest", yearMap);
        
        } catch(NumberFormatException e) {
        }

        return "intercalcResponse";
        //<td><c:out value="${current.id}" /><td>
    }

}
