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
public class FlooringController {

    private double width;
    private double length;
    private String myCost;

    private double cost;
    private double area;

    private double totalMatCost;

    private double totalLabor;

    private double quote;

    @RequestMapping(value = "/flooring", method = RequestMethod.GET)
    public String doGet() {
        return "flooring";
    }

    @RequestMapping(value = "/flooring", method = RequestMethod.POST)
    public String doPost(HttpServletRequest request, Model model) {

        try {
            width = Double.parseDouble(request.getParameter("myWidth"));
            length = Double.parseDouble(request.getParameter("myLength"));
            myCost = request.getParameter("myCost");

            cost = Double.parseDouble(myCost);
            area = width * length;

            totalMatCost = area * cost;

            totalLabor = laborCost(area);

            quote = totalLabor + totalMatCost;

            DecimalFormat df = new DecimalFormat("#.00");

            model.addAttribute("area", df.format(area));
            model.addAttribute("totalMatCost", df.format(totalMatCost));
            model.addAttribute("totalLabor", df.format(totalLabor));
            model.addAttribute("quote", df.format(quote));
        } catch (NumberFormatException e) {
        }

        return "flooringResponse";
    }

    public double laborCost(double area) {

        final double QUARTER_RATE = 21.5;

        int timeBlocks = 0;

        if (area % 5 != 0) {
            timeBlocks = ((int) area / 5) + 1;
        } else {
            timeBlocks = (int) area / 5;
        }

        return timeBlocks * QUARTER_RATE;

    }

}
