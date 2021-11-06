/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servlety1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author Laptop
 */
public class Iloczyn extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String in = request.getParameter("in");
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Kostka</title>");
            out.println("</head>");
            out.println("<body>");

            out.println("<form method='post'>");
            out.println("<input name='in' value='1' type='number'>");
            out.println("<input type='submit' value='Submit'>");
            Cookie OstatniaWizyta = null;
            for(Cookie c : request.getCookies())
                if(c.getName().equals("bylemtu"))
                { OstatniaWizyta=c; break; }
            if(OstatniaWizyta!= null){
                Integer wynik = Integer.parseInt(in) * Integer.parseInt(OstatniaWizyta.getValue());
                OstatniaWizyta=new Cookie("bylemtu", wynik.toString());
                response.addCookie(OstatniaWizyta);
                out.println("Wynik: "+ OstatniaWizyta.getValue());
            }
            else{
                OstatniaWizyta=new Cookie("bylemtu", "1");
                response.addCookie(OstatniaWizyta);
            }




            out.println("</form>");
            out.println("<br><a href='/Servlety1/'>&#8592 Strona główna</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        processRequest(request, response);
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
        processRequest(request, response);
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
