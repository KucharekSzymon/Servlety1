/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servlety1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Random;
import java.util.regex.*;
import javax.servlet.ServletException;
import javax.servlet.http.*;

/**
 *
 * @author Laptop
 */
public class Kostka extends HttpServlet {

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
        Random rand = new Random(); //instance of random class
        String in = request.getParameter("in");
        int a = 0;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");

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
            Pattern pp = Pattern.compile("^\\d");
            Matcher mp = pp.matcher(in);
            if(mp.matches()){
                int randzik = rand.nextInt(7)+1;
                out.println("<p>Inupt data: "+in);
                HttpSession sesja= request.getSession();
                if(sesja.getAttribute("ile")==null)
                    sesja.setAttribute("ile", 10);
                else
                {
                    int licznik=Integer.parseInt(sesja.getAttribute("ile").toString());
                    if (randzik == Integer.parseInt(in))
                        licznik++;
                    else
                        licznik--;
                    if(licznik<0){
                        out.println("<script>alert('You lost!');</script>");
                        licznik = 10;

                    }
                    sesja.setAttribute("ile", licznik);
                    a = Integer.parseInt(sesja.getAttribute("ile").toString());
                }
                out.println("<p>Wylosowana: " + randzik + "</p>");
                out.println("<p name='out'>" + a + "</p>");
            }

            else    out.println("<p style='color:red'>Incorrect number!");

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
