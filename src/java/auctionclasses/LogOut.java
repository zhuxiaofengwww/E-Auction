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
import java.util.*;
import auctionclasses.Utility;

/**
 *
 * @author Preston
 */
@WebServlet(name = "LogOut", urlPatterns = {"/LogOut"})
public class LogOut extends HttpServlet {

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
  

            //The LogOut.java servlet will log a user out of a current session
            //and take them back to the login screen
            
            
                String userEmailAddress = (String) session.getAttribute("EmailAddress");
                Class.forName(Utility.forClass);  // Loading the Microsoft SQL Server JDBC 4.0 driver 

                Connection con_for_LogOutDBDemo = null;
                con_for_LogOutDBDemo = DriverManager.getConnection(Utility.connection);
                
                String Query = "UPDATE Person SET Statement=? where EmailAddress= '"+userEmailAddress+"'";
 
                PreparedStatement pst = con_for_LogOutDBDemo.prepareStatement(Query);
                pst.setString(1, "0"); 
                // NOTE:  The user will not be allowed to change their Person ID number; however he/she will still be able to update their personal information.


                //The LogOut.java servlet will log a user out of a current session
                //and take them back to the login screen

                //HttpSession session = request.getSession();// starting a new session for the user
                pst.executeUpdate();
                pst.close();//closing the preparedstatement 

                // close the database connection.        
                con_for_LogOutDBDemo.close();                
            
            
            session.invalidate();// terminating the session

            response.sendRedirect("ServletLogOutSuccessfully.jsp");
            } catch (ClassNotFoundException e) {
            out.println("Couldn't load database driver: " + e.getMessage());
        } catch (SQLException e) {
            out.println("SQLException caught: " + e.getMessage());
        } catch (Exception e) {
            out.println(e);
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
