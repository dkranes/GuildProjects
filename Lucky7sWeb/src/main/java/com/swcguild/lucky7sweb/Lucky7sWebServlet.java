/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.lucky7sweb;

import java.io.IOException;
import java.util.Random;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author apprentice
 */
@WebServlet(name = "Lucky7sWebServlet", urlPatterns = {"/Lucky7sWebServlet"})
public class Lucky7sWebServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
//       String myAnswer = request.getParameter("myAnswer");
//       String myReason = request.getParameter("myReason");
//       String myNotes = request.getParameter("myNotes");
//       
//       String message = "";
//       
//       if("No".equals(myAnswer)){
//           message = "Sorry you can't make it";
//       } else {
//           message = "Can't wait to see you";
//       }
        String myAnswer = request.getParameter("myAnswer");
        
        //check if empty, then try catch around conversion to int
        if (myAnswer.isEmpty()){
            request.setAttribute("message", "Invalid Entry");
            RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
            rd.forward(request, response);
        }
        
        
        Random rGen = new Random();
        int rolls = 0;
        int maxMoney = 0;
        int timeToStop = 0;
        String message = "";
        
        try{
        int wallet=Integer.parseInt(myAnswer);

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

       
       request.setAttribute("rolls", rolls-1);
       request.setAttribute("timeToStop", timeToStop);
       request.setAttribute("maxMoney", maxMoney);
       
       RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
       rd.forward(request, response);
       
       
        } catch(NumberFormatException e) {
            request.setAttribute("message", "Invalid entry, please enter a number");
            RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
            rd.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private static int adjustWallet(int rInt, int wallet) {
        if (rInt != 7) {
            wallet -= 1;
        } else {
            wallet += 4;
        }

        return wallet;

    }

}
