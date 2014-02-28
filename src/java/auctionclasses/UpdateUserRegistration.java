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

/**
 *
 * @author Preston
 */
@WebServlet(name = "UpdateUserRegistration", urlPatterns = {"/UpdateUserRegistration"})
public class UpdateUserRegistration extends HttpServlet {

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
           
            Class.forName(Utility.forClass);  // Loading the Microsoft SQL Server JDBC 4.0 driver 

            Connection con_for_PrestonRegistrationDBDemo = null;
            con_for_PrestonRegistrationDBDemo = DriverManager.getConnection(Utility.connection);

            String strFirstName = request.getParameter("txtFirstName");
            String strLastName = request.getParameter("txtLastName");
            String strEmailAddress = request.getParameter("txtEmailAddress");
            String strPassword = request.getParameter("txtPassword");
            String strStreetAddress1 = request.getParameter("txtStreetAddress1");
            String strStreetAddress2 = request.getParameter("txtStreetAddress2");
            String strCity = request.getParameter("txtCity");
            String strState = request.getParameter("txtState");
            int strPostalCode = Integer.parseInt(request.getParameter("txtPostalCode"));
            String strCountry = request.getParameter("txtCountry");
            
            String userPersonID = (String) session.getAttribute("PersonID");

            PreparedStatement preCheckAvailableEmail = con_for_PrestonRegistrationDBDemo.prepareStatement("Select * from Person where EmailAddress= ? and PersonId != '" + userPersonID + "'");
            preCheckAvailableEmail.setString(1, strEmailAddress);
            ResultSet rsCheckAvailable = preCheckAvailableEmail.executeQuery();
            
            if (rsCheckAvailable.next()) {
            response.sendRedirect("ServletRegisterException.jsp");
            } else {            

            //The following code will update ANY REGISTERED USER'S personal information


            String sqlQuery = " UPDATE Person SET FirstName=?, LastName=?, EmailAddress=?, Password=?, StreetAddress1=?, StreetAddress2=?, City=?, State=?, PostalCode=?, Country=?  WHERE PersonId = '" + userPersonID + "'";

            PreparedStatement pst = con_for_PrestonRegistrationDBDemo.prepareStatement(sqlQuery);

            // NOTE:  The user will not be allowed to change their Person ID number; however he/she will still be able to update their personal information.
            pst.setString(1, strFirstName);
            pst.setString(2, strLastName);
            pst.setString(3, strEmailAddress);
            pst.setString(4, strPassword);
            pst.setString(5, strStreetAddress1);
            pst.setString(6, strStreetAddress2);
            pst.setString(7, strCity);
            pst.setString(8, strState);
            pst.setInt(9, strPostalCode);
            pst.setString(10, strCountry);

            pst.executeUpdate();
            
             auctionclasses.Customer user = new auctionclasses.Customer(userPersonID, strFirstName, strLastName, strEmailAddress, strPassword);
                
             session.setAttribute("PersonID", userPersonID);
             session.setAttribute("customer", user);
             session.setAttribute("EmailAddress", strEmailAddress);             

            //response.sendRedirect("ServletUpgradeSuccessfully.jsp");
            response.sendRedirect("index.jsp");

            pst.close();//closing the preparedstatement         



            // close the database connection.        
            con_for_PrestonRegistrationDBDemo.close();
            }

        } catch (Exception ex) {
            response.sendRedirect("ServletUpgradeException.jsp");
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
