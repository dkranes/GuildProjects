/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.factorizerweb;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "FactorizerWebServlet", urlPatterns = {"/FactorizerWebServlet"})
public class FactorizerWebServlet extends HttpServlet {

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
//            out.println("<title>Servlet FactorizerWebServlet</title>");
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FactorizerWebServlet at " + request.getContextPath() + "</h1>");
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
        String myAnswer = request.getParameter("myAnswer");

        if (myAnswer.isEmpty()) {
            request.setAttribute("message", "Invalid entry, please enter a number");
            RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
            rd.forward(request, response);
        }

        String factorsAre = "";

        String isOrIsNotPerfect = "";
        String isOrIsNotPrime = "";
        String message = "";
        ArrayList<Integer> factors = new ArrayList<>();

        int sumOfFactors = 0;

        try {
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

            request.setAttribute("inputNum", inputNum);
            request.setAttribute("factors", factors.toString().replace("[", "").replace("]", "").trim() + ", " + myAnswer);
            request.setAttribute("isOrIsNotPerfect", isOrIsNotPerfect);
            request.setAttribute("isOrIsNotPrime", isOrIsNotPrime);

            //request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher("response.jsp");
            rd.forward(request, response);

        } catch (NumberFormatException e) {
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

}
