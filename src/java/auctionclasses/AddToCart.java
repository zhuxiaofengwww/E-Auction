/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package auctionclasses;

/**
 *
 * @author Xiaofeng
 */
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ListResourceBundle;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Preston
 */
@WebServlet(name = "AddToCart", urlPatterns = {"/AddToCart"})
public class AddToCart extends HttpServlet {

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
            

            String strItemPrefix = "Sale";// All bid transaction numbers will begin with the 'BID' prefix.

            int aNumber = (int) (Math.random() * 400 + 100);//generating a random integer to attach to an BID ID
            int bNumber = (int) (Math.random() * 500 * 10);//generating a random integer to attach to an BID ID

            String s3 = Integer.toString(aNumber);
            String s4 = Integer.toString(bNumber);

            String newSaleIDNumber = strItemPrefix + s3 + s4;// This Bid ID number will be used to identify the bids on the database
            
            
                Date today = new Date();

                // Formatting the date into a readable format
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                String timestamp;
                timestamp = formatter.format(today);            

             // load the driver and create the connection
            Connection con = null;
            Class.forName(Utility.forClass);  // load the driver
            con = DriverManager.getConnection(Utility.connection);
            
            String itemid = request.getParameter("ItemID");
            String bidid = request.getParameter("BidID");
            Double bidamount = Double.parseDouble(request.getParameter("BidAmount")); 
            String bidderid = request.getParameter("BidderID");
            
            PreparedStatement prep = con.prepareStatement("INSERT INTO Sale VALUES (?,?,?,?,?,?)");
            prep.setString(1, newSaleIDNumber);
            prep.setString(2, bidid);
            prep.setString(3, itemid);
            prep.setDouble(4, bidamount);
            prep.setString(5, timestamp); 
            prep.setString(6, bidderid);            
            int result = prep.executeUpdate();
            prep.close();

//delete the item from bid when the user chooses to add it to cart
/*           
            
            Statement statementForwinner = null;
            statementForwinner = con.createStatement();

            ResultSet rsForwinner = null;//variable to hold the data from the database table          
            String queryStringForwinner = "DELETE FROM Bid WHERE BidID = '" + bidid + "'"; 
            rsForwinner = statementForwinner.executeQuery(queryStringForwinner);
            
            rsForwinner.close();
            statementForwinner.close();
*/
//end of deleting the bid
/*
            out.println(result + " item Added!<br/>");
            out.println("<a href= 'viewmybiditems.jsp'   >");
            out.println("Back to my bid");
            out.println("</a>");
*/


//            out.println(result + " item Added!<br/>");
            response.sendRedirect("ServletAddToCartSuccessfully.jsp");           


            // close the database connection.        
            con.close();
        } catch (Exception ex) {
            response.sendRedirect("ServletAddToCartException.jsp");

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

