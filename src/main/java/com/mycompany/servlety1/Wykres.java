/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.servlety1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Laptop
 */
public class Wykres extends HttpServlet {

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
        String in = request.getParameter("in");
        ArrayList<Integer> lista = new ArrayList<Integer>();
        Integer a = 0;
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            response.setContentType("text/html;charset=UTF-8");

            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Wykres</title>");
            out.println("</head>");
            out.println("<body style='background-color:#3f3f3f;color:white'>");

            out.println("<form method='post'>");
            out.println("<input name='in' value='1' type='number'>");
            out.println("<input style='background-color:white;'type='submit' value='Submit'>");
            out.println("<input style='background-color:white;'type='submit' name='svg' value='SVG'>");

            HttpSession sesja= request.getSession();
            Pattern pp = Pattern.compile("^\\d");
            Matcher mp = pp.matcher(in);
            if(mp.matches() && Integer.parseInt(in)>=0 && Integer.parseInt(in)<=10){
                out.println("<p>Inupt data: "+in);
                if(sesja.getAttribute("lista")==null){
                    ArrayList<Integer> temp = new ArrayList<Integer>();
                    sesja.setAttribute("lista", temp);

                }
                else{
                    lista =(ArrayList<Integer>)sesja.getAttribute("lista");
                    lista.add(Integer.parseInt(in));
                    sesja.setAttribute("lista", lista);
                }
            }

            else    out.println("<p style='color:red'>Incorrect number!");
            out.println(lista.toString());
            out.println("</form>");

            if(!request.getParameter("svg").equals(null)){
                lista =(ArrayList<Integer>)sesja.getAttribute("lista");
                out.print("<svg style='transform: scaleY(-1);'width='1500px' height='500px'>");
                int osx=0;
                for(Integer x : lista){
                            out.print("<rect style='fill:cyan;stroke:pink;stroke-width:2;fill-opacity:0.5;stroke-opacity:0.9' y='0' width='50' x='"+osx+"' height='"+x*50+"'/>");
                    osx+=50;
                }
                out.print("</svg>");
            }

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
