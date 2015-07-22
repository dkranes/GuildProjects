/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.flooringcalculatorweb;

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
@WebServlet(name = "FlooringCalculatorWebServlet", urlPatterns = {"/FlooringCalculatorWebServlet"})
public class FlooringCalculatorWebServlet extends HttpServlet {

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
//            out.println("<title>Servlet FlooringCalculatorWebServlet</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet FlooringCalculatorWebServlet at " + request.getContextPath() + "</h1>");
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

        double width = Double.parseDouble(request.getParameter("myWidth"));
        double length = Double.parseDouble(request.getParameter("myLength"));
        String myCost = request.getParameter("myCost");
        double cost = Double.parseDouble(myCost);
        double area = width * length;

        double totalMatCost = area * cost;

        double totalLabor = laborCost(area);

        double quote = totalLabor + totalMatCost;
        
        DecimalFormat df=new DecimalFormat("#.##");
        
        request.setAttribute("area", df.format(area));
        request.setAttribute("totalMatCost", df.format(totalMatCost));
        request.setAttribute("totalLabor", df.format(totalLabor));
        request.setAttribute("quote", df.format(quote));
        

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

    public double laborCost(double area) {

        final double QUARTER_RATE = 21.5;

        int timeBlocks = 0;
        
        
        if (area % 5 != 0) {
            timeBlocks = ((int)area / 5) + 1;
        } else {
            timeBlocks = (int)area / 5;
        }

        return timeBlocks * QUARTER_RATE;

    }

}
