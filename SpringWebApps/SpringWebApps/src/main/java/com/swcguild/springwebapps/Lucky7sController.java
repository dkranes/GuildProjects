/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.springwebapps;

import java.util.Map;
import java.util.Random;
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
@RequestMapping()
public class Lucky7sController {
    public Lucky7sController() {
    }
    
//    @RequestMapping(value="lucky7s", method=RequestMethod.GET)
//    public String sayHi(Map<String, Object> model) {
//        model.put("message", "Hello from the Lucky 7's controller" );
//        return "lucky7s";
//    }
    
    Random rGen = new Random();
    private int rolls = 0;
    private int maxMoney = 0;
    private int timeToStop = 0;
    private String message = "";

    @RequestMapping(value = "/lucky7s", method = RequestMethod.GET)
    public String sayHi(Map<String, Object> model) {
        model.put("message", "Hello from the Lucky 7's controller" );
        return "/lucky7s";
    }

    @RequestMapping(value = "/lucky7s", method = RequestMethod.POST)
    public String playLucky7s(HttpServletRequest request, Model model) {

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

            model.addAttribute("rolls", rolls - 1);
            model.addAttribute("timeToStop", timeToStop);
            model.addAttribute("maxMoney", maxMoney);


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
//            out.println("<title>Servlet Lucky7sController</title>");            
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet Lucky7sController at " + request.getContextPath() + "</h1>");
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
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Handles the HTTP <code>POST</code> method.
//     *
//     * @param request servlet request
//     * @param response servlet response
//     * @throws ServletException if a servlet-specific error occurs
//     * @throws IOException if an I/O error occurs
//     */
//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        processRequest(request, response);
//    }
//
//    /**
//     * Returns a short description of the servlet.
//     *
//     * @return a String containing servlet description
//     */
//    @Override
//    public String getServletInfo() {
//        return "Short description";
//    }// </editor-fold>

}
