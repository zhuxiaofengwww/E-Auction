package auctionclasses;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
@WebServlet(name = "RegistrationServlet", urlPatterns = {"/RegistrationServlet"})
public class RegistrationServletForMan extends HttpServlet {

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
                /*
                if (null == session.getAttribute("EmailAddress")) {
                    response.sendRedirect("BadLogin.jsp");
                }*/            


                String strItemPrefix = "CUST";// All of our assigned customer IDs will begin with 'CUST'.

                int aNumber = (int) (Math.random() * 400 + 100);//generating a random integer to attach to a customer ID
                int bNumber = (int) (Math.random() * 500 * 10);//generating a random integer to attach to a customer ID

                String s3 = Integer.toString(aNumber);
                String s4 = Integer.toString(bNumber);

                String newCustomerIDNumber = strItemPrefix + s3 + s4;// This customer ID number will be used to identify our Customers in the database


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
                                             
                
                PreparedStatement preCheckAvailableEmail = con_for_PrestonRegistrationDBDemo.prepareStatement("Select * from Person where EmailAddress= ?");
                preCheckAvailableEmail.setString(1, strEmailAddress);
                ResultSet rsCheckAvailable = preCheckAvailableEmail.executeQuery();

                if (rsCheckAvailable.next()) {
                    response.sendRedirect("ServletRegisterException.jsp");

                } else {
                    String sqlQuery = "INSERT INTO Person VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

                    PreparedStatement pst = con_for_PrestonRegistrationDBDemo.prepareStatement(sqlQuery);

                    pst.setString(1, newCustomerIDNumber);
                    pst.setString(2, strFirstName);
                    pst.setString(3, strLastName);
                    pst.setString(4, strEmailAddress);
                    pst.setString(5, strPassword);
                    pst.setString(6, strStreetAddress1);
                    pst.setString(7, strStreetAddress2);
                    pst.setString(8, strCity);
                    pst.setString(9, strState);
                    pst.setInt(10, strPostalCode);
                    pst.setString(11, strCountry);

                    pst.setString(12, "1");
                    pst.setString(13, "1");
                    pst.setString(14, "1");                

                    pst.executeUpdate();


                    response.sendRedirect("ServletRegisterSuccessfully.jsp");

                    pst.close();//closing the preparedstatement   


                    // close the database connection.        
                    con_for_PrestonRegistrationDBDemo.close();
                }
                auctionclasses.Customer user = new auctionclasses.Customer(newCustomerIDNumber, strFirstName, strLastName, strEmailAddress, strPassword);
                
                session.setAttribute("PersonID", newCustomerIDNumber);
                session.setAttribute("customer", user);
                session.setAttribute("EmailAddress", strEmailAddress);                 
                
                

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
