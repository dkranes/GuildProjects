/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.springmvcwebapp.controller;

import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
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
public class TipCalcController {

    private double originalAmount;
    private double tipAmount;
    private double tipPercentage;
    private double finalAmount;

    

    @RequestMapping(value = "/tipCalc", method = RequestMethod.GET)
    public String doGet() {
        return "tipCalc";
    }

    @RequestMapping(value = "/tipCalc", method = RequestMethod.POST)
    public String calculateTip(HttpServletRequest request, Model model) {

        try {
            originalAmount = Double.parseDouble(request.getParameter("originalAmount"));
            tipPercentage = Double.parseDouble(request.getParameter("tipPercentage"));
            

            tipAmount = (tipPercentage*originalAmount)/100;
            finalAmount = originalAmount+tipAmount;

         

            DecimalFormat df = new DecimalFormat("#.00");

            model.addAttribute("originalAmount", df.format(originalAmount));
            model.addAttribute("tipAmount", df.format(tipAmount));
            model.addAttribute("tipPercentage", tipPercentage);
            model.addAttribute("finalAmount", df.format(finalAmount));
        } catch (NumberFormatException e) {
        }

        return "tipCalcResponse";
    }

    

}
