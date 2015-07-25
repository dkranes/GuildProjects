/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.springmvcwebapp.controller;

import java.text.DecimalFormat;
import java.util.Random;
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
public class Lucky7sController {

    Random rGen = new Random();
    private int rolls = 0;
    private int maxMoney = 0;
    private int timeToStop = 0;
    private String message = "";

    @RequestMapping(value = "/lucky7s", method = RequestMethod.GET)
    public String doGet() {
        return "lucky7s";
    }

    @RequestMapping(value = "/lucky7s", method = RequestMethod.POST)
    public String doPost(HttpServletRequest request, Model model) {

        String myAnswer = request.getParameter("myAnswer");

        //check if empty, then try catch around conversion to int
        try {

            int wallet = Integer.parseInt(request.getParameter("myAnswer"));

            do {

                int rInt = rGen.nextInt(11) + 2;

                if (wallet > maxMoney) {
                    maxMoney = wallet;
                }
                if (maxMoney <= wallet) {
                    timeToStop = rolls;
                }
                wallet = adjustWallet(rInt, wallet);
                rolls++;

            } while (wallet >= 1);

            DecimalFormat df = new DecimalFormat("#.00");
            
            model.addAttribute("rolls", rolls - 1);
            model.addAttribute("timeToStop", timeToStop);
            model.addAttribute("maxMoney", df.format(maxMoney));


        } catch (NumberFormatException e) {
            
        }

        return "lucky7sResponse";

    }

    private static int adjustWallet(int rInt, int wallet) {
        if (rInt != 7) {
            wallet -= 1;
        } else {
            wallet += 4;
        }

        return wallet;

    }

}
