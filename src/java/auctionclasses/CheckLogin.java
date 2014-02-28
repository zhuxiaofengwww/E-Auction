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
@WebServlet(name = "checklogin", urlPatterns = {"/checklogin"})
public class CheckLogin extends HttpServlet {

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


            //The CheckLogin.java servlet will process and verify the user information
            //HttpSession session = request.getSession();// starting a new session for the user
            session.setMaxInactiveInterval(7200);// the current session will terminate in 2h (7200 seconds)

            String userEmailAddress = request.getParameter("userEmailAddress");//read in user email from textbox
            String userPassword = request.getParameter("userPassword");// read in user password from textbox
            

            Class.forName(Utility.forClass);  // Loading the Microsoft SQL Server JDBC 4.0 driver 

            Connection con_for_ISM3232AuctionDB = null;// Establishing a connection with SQL Server database
            con_for_ISM3232AuctionDB = DriverManager.getConnection(Utility.connection);

            PreparedStatement prep = con_for_ISM3232AuctionDB.prepareStatement("SELECT * FROM Person WHERE EmailAddress = ? and Password = ?");
            prep.setString(1, userEmailAddress);
            prep.setString(2, userPassword);

            ResultSet rs = prep.executeQuery();

            if (rs.next()) {

                /* If the user enters a valid email and user password, 
                they will be granted access to a restricted page called 
                "MenuForRegisteredUsers.jsp" where they will be given the
                option to sell items on our website.*/

                String id2 = rs.getString(1);
                String first = rs.getString(2);
                String last = rs.getString(3);
                String email = rs.getString(4);
                String password2 = rs.getString(5);
                
                String active = rs.getString(13);
                String role = rs.getString(14);

                auctionclasses.Customer user = new auctionclasses.Customer(id2, first, last, email, password2);
                
                session.setAttribute("PersonID", id2);
                session.setAttribute("customer", user);
                session.setAttribute("EmailAddress", email);
                
                
                String sqlQuery = " UPDATE Person SET statement=? WHERE EmailAddress = '" + userEmailAddress + "'";

                PreparedStatement pst = con_for_ISM3232AuctionDB.prepareStatement(sqlQuery);

                // NOTE:  The user will not be allowed to change their Person ID number; however he/she will still be able to update their personal information.
                pst.setString(1, "1");  

                //The LogOut.java servlet will log a user out of a current session
                //and take them back to the login screen

                //HttpSession session = request.getSession();// starting a new session for the user
                pst.executeUpdate();
                pst.close();//closing the preparedstatement   
                if(role.equals("1")){
                    response.sendRedirect("management.jsp");
                    
                }else{
                    if(active.equals("0")){
                            session.invalidate();// terminating the session
                            response.sendRedirect("InvalidUser.jsp");
                    }else{

                            response.sendRedirect("index.jsp");
                    }
                }
                
            } else {
                response.sendRedirect("BadLogin.jsp");
            }     


            // close the database connection.        
            con_for_ISM3232AuctionDB.close();

            
        } catch (Exception e) {
            e.printStackTrace();
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
