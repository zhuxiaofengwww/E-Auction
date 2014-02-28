/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclasses;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.*;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ListResourceBundle;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Preston
 */
@WebServlet(name = "DeleteItem", urlPatterns = {"/DeleteItem"})
public class DeleteItem extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            HttpSession session = request.getSession();

            // First, we must make sure that the user bidding on the item is logged in.
            // DoBid.java will check the session variable "EmailAddress" in the CheckLogin.java page.
            if (null == session.getAttribute("EmailAddress")) {
                response.sendRedirect("BadLogin.jsp");
            }            

             // load the driver and create the connection
            Connection con = null;
            Class.forName(Utility.forClass);  // load the driver
            con = DriverManager.getConnection(Utility.connection);
            String id = request.getParameter("ItemID");

            PreparedStatement prep = con.prepareStatement("DELETE FROM Item WHERE ItemId = ?");
            prep.setString(1, id);
            int result = prep.executeUpdate();
            response.sendRedirect("ServletDeleteItemSuccessfully.jsp");

            prep.close();


            // close the database connection.        
            con.close();
        } catch (Exception ex) {
            response.sendRedirect("ServletDeleteItemException.jsp");
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
