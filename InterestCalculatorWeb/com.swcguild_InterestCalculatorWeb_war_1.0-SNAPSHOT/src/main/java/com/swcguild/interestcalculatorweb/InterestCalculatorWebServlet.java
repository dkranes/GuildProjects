/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.interestcalculatorweb;

import java.io.IOException;
import java.text.DecimalFormat;
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
@WebServlet(name = "InterestCalculatorWebServlet", urlPatterns = {"/InterestCalculatorWebServlet"})
public class InterestCalculatorWebServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        response.setContentType("text/html;charset=UTF-8");
//        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet InterestCalculatorWebServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet InterestCalculatorWebServlet at " + request.getContextPath() + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
//        }
//    }
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
        double originalBalance = Double.parseDouble(request.getParameter("myAnswer"));
        double startingBalance = originalBalance;
        double intRate = Double.parseDouble(request.getParameter("myRate"));
        double numYears = Double.parseDouble(request.getParameter("myYears"));
        double numPeriods = Double.parseDouble(request.getParameter("myPeriods"));
        String message = "";
        double annualInterest;
        double newBalance;

        int yearCount = 0;

        do {
            newBalance = originalBalance * (Math.pow(1 + ((intRate * .01) / numPeriods), (numPeriods)));
            yearCount++;
            originalBalance = newBalance;
        } while (yearCount <= (numYears - 1));
        annualInterest = newBalance - startingBalance;

        DecimalFormat df = new DecimalFormat("#.##");

        request.setAttribute("originalBalance", df.format(startingBalance));
        request.setAttribute("newBalance", df.format(newBalance));
        request.setAttribute("interestEarned", df.format(annualInterest));
        request.setAttribute("years", df.format(numYears));
        request.setAttribute("periods", df.format(numPeriods));

        RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
        rd.forward(request, response);

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

}
