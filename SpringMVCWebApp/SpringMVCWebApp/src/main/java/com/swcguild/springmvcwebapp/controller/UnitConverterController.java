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
public class UnitConverterController {

    private double originalValue;
    private String conversionType;
    private String convertFrom;
    private String convertTo;
    private double result = 0;

    @RequestMapping(value = "/unitConverter", method = RequestMethod.GET)
    public String doGet() {
        return "unitConverter";
    }

    @RequestMapping(value = "/unitConverter", method = RequestMethod.POST)
    public String convertUnits(HttpServletRequest request, Model model) {

        try {
            originalValue = Double.parseDouble(request.getParameter("originalValue"));
            conversionType = request.getParameter("conversionType");
            convertFrom = request.getParameter("convertFrom");
            convertTo = request.getParameter("convertTo");

            if (conversionType.equalsIgnoreCase("Temperature")) {
                result = convertTemp(originalValue);
                
                if (convertFrom.equals("1")){
                    convertFrom = "Fahrenheit";
                } else if (convertFrom.equals("2")) {
                    convertFrom = "Celsius";
                } else {
                    convertFrom = "Kelvin";
                }
                
                if (convertTo.equals("1")){
                    convertTo = "Fahrenheit";
                } else if (convertTo.equals("2")) {
                    convertTo = "Celsius";
                } else {
                    convertTo = "Kelvin";
                }
                
            }

            if (conversionType.equalsIgnoreCase("Currency")) {
                result = convertCurrency(originalValue);
                
                if (convertFrom.equals("1")){
                    convertFrom = "Dollars";
                } else {
                    convertFrom = "Euro";
                }
                
                if (convertTo.equals("1")){
                    convertTo = "Dollars";
                } else {
                    convertTo = "Euro";
                }
                
                
            }
            
            if(conversionType.equalsIgnoreCase("Volume")) {
                result = convertVolume(originalValue);
                
                if (convertFrom.equals("1")) {
                    convertFrom = "Gallon";
                } else {
                    convertFrom = "Liter";
                }

                if (convertTo.equals("1")) {
                    convertTo = "Gallon";
                } else {
                    convertTo = "Liter";
                }
                
            }
            
            if(conversionType.equalsIgnoreCase("Mass")){
                result = convertMass(originalValue);
                
                if (convertFrom.equals("1")) {
                    convertFrom = "Pound";
                } else {
                    convertFrom = "Kilogram";
                }

                if (convertTo.equals("1")) {
                    convertTo = "Pound";
                } else {
                    convertTo = "Kilogram";
                }
            }

            DecimalFormat df = new DecimalFormat("0.00");

            model.addAttribute("result", df.format(result));
            model.addAttribute("originalValue", df.format(originalValue));
            model.addAttribute("conversionType", conversionType);
            model.addAttribute("convertFrom", convertFrom);
            model.addAttribute("convertTo", convertTo);

//            model.addAttribute("originalAmount", df.format(originalAmount));
//            model.addAttribute("tipAmount", df.format(tipAmount));
//            model.addAttribute("tipPercentage", df.format(tipPercentage));
//            model.addAttribute("finalAmount", df.format(finalAmount));
        } catch (NumberFormatException e) {
        }

        return "unitConverterResponse";
    }

    private double convertTemp(double originalValue) {

        if (convertFrom.equalsIgnoreCase("1")) {
            if (convertTo.equalsIgnoreCase("2")) {
                result = (originalValue - 32) * (0.55555555555);
            } else if (convertTo.equalsIgnoreCase("3")) {
                result = ((originalValue - 32) * (0.55555555555)) + 273.15;
            } else {
                result = 0;
            }
        }

        if (convertFrom.equalsIgnoreCase("2")) {
            if (convertTo.equalsIgnoreCase("1")) {
                result = (originalValue / 0.55555555555) + 32;
            } else if (convertTo.equalsIgnoreCase("3")) {
                result = (originalValue + 273.15);
            } else {
                result = 0;
            }
        } else if (convertFrom.equalsIgnoreCase("3")) {
            if (convertTo.equalsIgnoreCase("1")) {
                result = (originalValue - 273.15) * (0.55555555555) + 32;
            } else if (convertTo.equalsIgnoreCase("2")) {
                result = (originalValue - 273.15);
            } else {
                result = 0;
            }
        }

        return result;
    }

    private double convertCurrency(double originalValue) {

        if (convertFrom.equalsIgnoreCase("1")) {
            if (convertTo.equalsIgnoreCase("2")) {
                result = originalValue * 0.92;
            } else {
                result = 0;
            }
        } else if (convertFrom.equalsIgnoreCase("2")) {
            if (convertTo.equalsIgnoreCase("1")) {
                result = originalValue * 1.09;
            } else {
                result = 0;
            }
        }
        return result;
    }

    private double convertVolume(double originalValue) {
        if(convertFrom.equalsIgnoreCase("1")){
            result = originalValue/0.26417;
        } else if (convertFrom.equalsIgnoreCase("2")){
            result = originalValue*0.26417;
        } else {
            result = 0;
        }
        
        return result;
    }

    private double convertMass(double originalValue) {
        if(convertFrom.equalsIgnoreCase("1")){
            result = originalValue/2.2046;
        } else if (convertFrom.equalsIgnoreCase("2")){
            result = originalValue * 2.2046;
        } else {
            result = 0;
        }
        return result;
    }

}
