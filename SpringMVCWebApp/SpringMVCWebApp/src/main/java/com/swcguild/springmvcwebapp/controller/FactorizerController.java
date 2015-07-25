/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.springmvcwebapp.controller;

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
public class FactorizerController {

    private String myAnswer;
    private int sumOfFactors;
    private String factorsAre;
    private String isOrIsNotPerfect;
    private String isOrIsNotPrime;
    private String message;

    @RequestMapping(value = "/factorizer", method = RequestMethod.GET)
    public String doGet() {
        return "factorizer";
    }

    @RequestMapping(value = "/factorizer", method = RequestMethod.POST)
    public String doPost(HttpServletRequest request, Model model) {

        try {

            myAnswer = request.getParameter("myAnswer");
            ArrayList<Integer> factors = new ArrayList<>();
            sumOfFactors = 0;
            factorsAre = "";
            isOrIsNotPerfect = "";
            isOrIsNotPrime = "";
            message = "";
            int inputNum = Integer.parseInt(myAnswer);

            for (int i = 1; i < inputNum; i++) {
                if (inputNum % i == 0) {
                    factors.add(i);
                    //System.out.println(i);
                    sumOfFactors = sumOfFactors + i;
                }
            }

            if (sumOfFactors != inputNum) {
                isOrIsNotPerfect = "not";
            }

            if (sumOfFactors != 1) {
                isOrIsNotPrime = "not";
            }

            model.addAttribute("inputNum", inputNum);
            model.addAttribute("factors", factors.toString().replace("[", "").replace("]", "").trim() + ", " + myAnswer);
            model.addAttribute("isOrIsNotPerfect", isOrIsNotPerfect);
            model.addAttribute("isOrIsNotPrime", isOrIsNotPrime);

        } catch (NumberFormatException e) {

        }

        return "factorizerResponse";
    }

}
