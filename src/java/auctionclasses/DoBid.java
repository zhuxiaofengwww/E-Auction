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
import java.text.SimpleDateFormat;
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
@WebServlet(name = "DoBid", urlPatterns = {"/DoBid"})
public class DoBid extends HttpServlet {

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
            out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
            out.println("<html xmlns:layout>");
            out.println("<head>");
            out.println(" <title>Home Page</title>");
            out.println("<meta charset='utf-8'>");
            out.println("<link rel='stylesheet' href='CSSCL/reset.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='CSSCL/style.css' type='text/css' media='all'>");
            out.println("<!--[if lt IE 7]>");
            out.println("<link rel='stylesheet' href='CSSCL/ie/ie6.css' type='text/css' media='all'>");
            out.println("<![endif]-->");
            out.println("</head>");
            out.println("<!--</header>  -->");
            out.println("<div id='top'>");

            out.println("<div id='topwrapper'>");
            out.println("<div class='container'>");

            out.println("<div id='logo'><a href='index.jsp'><img src='images/logo.png'/></a></div>");
            out.println("<h1><a href='index.jsp'>My E-Auction</a></h1>");

            out.println("<div id='user'>");

            out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");

            out.println("Welcome!");
            out.println("&nbsp");
            
            out.println("</div>");

            out.println("<div id='cart'><a href='cart.jsp'><img src='images/cart.png'/></a></div>");
            out.println("<div id='kid'><a href='index.jsp'><img src='images/kid.png'/></a></div>");
            out.println("<ul id='menu'>");
            out.println("<li><a href='index.jsp' class='but1_active'>Home Page</a></li>");
            out.println("<li><a href='about.jsp' class='but2'>About Us</a></li>");
            out.println("<li><a href='viewmybiditems.jsp' class='but2'>View My Bids</a></li>");
            out.println("<li><a href='item.jsp' class='but2'>Sell an Item</a></li>");
            out.println("<li><a href='viewmysaleitems.jsp' class='but2'>View My Items</a></li>");
            out.println("<li><a href='help.jsp' class='but2'>Service & Help</a></li>");
            out.println("</ul>");
            out.println("</div>");

            out.println("<div id='welcome'>");
            out.println("<div class='container'>");
            out.println("<div id='search'>");
            out.println("<form name='Search' method='get' action='browseitemlistings.jsp'>");
            out.println("<input type=text size=65 name='name' />");
            out.println("<select name='select'>");
            out.println("<option value='all' selected='selected'>All Categories</option>");
            out.println("<option value='parts'>Parts</option>");
            out.println("<option value='books'>Books</option>");
            out.println("<option value='business'>Business</option>");
            out.println("<option value='clothing'>Clothing</option>");
            out.println("<option value='others'>Others</option>");
            out.println("</select>");
            out.println("<input type='submit' value='Search'/>");
            out.println("</form>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");    
            out.println("</div>");

            out.println("</div>");
            out.println("<div id='mainboxer'>");
            out.println("<div class='container'>");//End of style definition           
            
        if(request.getParameter("txtUserBidAmount")==""){
/*
            out.println("&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp");
            out.println("Welcome!");
            out.println("&nbsp");
            if(session.getAttribute("customer")!=null){
            out.println(session.getAttribute("customer"));
            out.println("<a href='registrationupdateform.jsp'>Edit Information</a>");
            out.println("&nbsp");
            out.println("<a href='LogOut'>Log Out</a>");}
            else{
                out.println("<a href='login.jsp'>Sign in</a> or <a href='register.jsp'>register</a>");
            }
*/            
  
            
                          out.println("<br/>");
                          out.println("You should  input a positive number.Invalid Input. Please try again");
                          out.println("<a href= 'javascript:history.back(-1)'>Bak to the item listings</a>");
                out.println("</div>");// Closing tag for "Mainboxer"
                out.println("</div>");// Closing tag for "Container"
                out.println("<script src='CSSCL/commonfooter.js'></script>");


                out.println("</body>");// Closing body tag
                out.println("</html>");// End of HTML Code                                
                                
        }else{
        try {
            HttpSession session = request.getSession();

            // First, we must make sure that the user bidding on the item is logged in.
            // DoBid.java will check the session variable "EmailAddress" in the CheckLogin.java page.
            if (null == session.getAttribute("EmailAddress")) {
                response.sendRedirect("BadLogin.jsp");
            }            

            //HttpSession session = request.getSession();
            String strItemPrefix = "BID";// All bid transaction numbers will begin with the 'BID' prefix.

            int aNumber = (int) (Math.random() * 400 + 100);//generating a random integer to attach to an BID ID
            int bNumber = (int) (Math.random() * 500 * 10);//generating a random integer to attach to an BID ID

            String s3 = Integer.toString(aNumber);
            String s4 = Integer.toString(bNumber);

            String newBidIDNumber = strItemPrefix + s3 + s4;// This Bid ID number will be used to identify the bids on the database

            // First, we must make sure that the user bidding on the item is logged in.
            // DoBid.java will check the session variable "EmailAddress" in the CheckLogin.java page.
            if (null == session.getAttribute("EmailAddress")) {
                response.sendRedirect("BadLogin.jsp");
            } else {

                // Getting the current date and time so that we know when the bid was placed.
                Date today = new Date();

                // Formatting the date into a readable format
                SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                String timestamp;
                timestamp = formatter.format(today);

                Double dblBidPrice = Double.parseDouble(request.getParameter("txtUserBidAmount"));// Getting the bid amount from Bid


                // We will use the PersonID that is stored in the session variable from CheckLogin.java to link the bid item to the bidder.
                // The ItemNumber session variable from bid.jsp will be used to insert the item into the database so that we know what the bidder is bidding on.

                String userEmailAddress = (String) session.getAttribute("EmailAddress");
                String userItemID = (String) session.getAttribute("ItemNumber");

                /**************************************Starting connection to the database*******************************************************************************/
                Class.forName(Utility.forClass);  // Loading the Microsoft SQL Server JDBC 4.0 driver 

                // Establishing a database connection with ISM3232AuctionDB
                Connection con_for_ISM3232AuctionDB = null;
                con_for_ISM3232AuctionDB = DriverManager.getConnection(Utility.connection);

                Statement statementForAskingPrice = null;
                statementForAskingPrice = con_for_ISM3232AuctionDB.createStatement();

                ResultSet rsForAskingPrice = null; //variable to hold the data for the asking price and current bid amount from the database table            
                String sqlQueryForAskingPrice = "SELECT AskingPrice FROM Item WHERE ItemID = '" + userItemID + "'";
                rsForAskingPrice = statementForAskingPrice.executeQuery(sqlQueryForAskingPrice);
                double dbAskingPrice = 0.00;
                if (rsForAskingPrice.next()) {

                    dbAskingPrice = rsForAskingPrice.getDouble(1);// storing the bid amount from the query into a variable 
                }
                Statement statementForpreviousBidAmount = null;
                statementForpreviousBidAmount = con_for_ISM3232AuctionDB.createStatement();
                ResultSet rsForForForBidAmount = null;
                String sqlQueryForpreviousBidAmount = "SELECT Bid.BidAmount FROM Bid WHERE Bid.ItemID = '" + userItemID + "' and Bid.BidderID='" + userEmailAddress + "'";
                rsForForForBidAmount = statementForpreviousBidAmount.executeQuery(sqlQueryForpreviousBidAmount);

                // 2. Start of query for bid amount************************************************************************************
                Statement statementForBidAmount = null;
                statementForBidAmount = con_for_ISM3232AuctionDB.createStatement();

                ResultSet rsForForBidAmount = null; //variable to hold the data for the asking price and current bid amount from the database table
                //String sqlQueryForBidAmount = "SELECT BidAmount FROM Bid WHERE ItemID = '" + userItemID + "'";
                //String sqlQueryForBidAmount = "SELECT Max(Bid.BidAmount) FROM Item, Bid WHERE Item.ItemID = Bid.ItemID and Bid.ItemID = '" + userItemID + "'";
                String sqlQueryForBidAmount = "SELECT Max(Bid.BidAmount) AS maxBidAmount FROM Bid WHERE ItemID = '" + userItemID + "'";
                rsForForBidAmount = statementForBidAmount.executeQuery(sqlQueryForBidAmount);


                if (rsForForBidAmount.next()) {
                    double dblBidAmount = rsForForBidAmount.getDouble("maxBidAmount");// storing the bid amount from the query into a variable
                    if (dblBidAmount > dbAskingPrice) {

                        if (rsForForForBidAmount.next()) {
                            if (dblBidPrice <= dblBidAmount) {
                                out.println("Sorry, you must bid above the current bid amount.");
                                out.println("<a href= 'javascript:history.back(-1)'>");
                                out.println("Back to item listings");
                                out.println("</a>");
                            } else {
                                //String sqlQuery = "UPDATE Bid SET BidID = ?,ItemID = ?,BidderID = ?, TimeStampForBid = ?, BidAmount = ? WHERE BidderID='" + userEmailAddress + "' AND ItemID='" + userItemID + "'";
                                String sqlQuery = "UPDATE Bid SET BidID = ?, TimeStampForBid = ?, BidAmount = ? WHERE BidderID='" + userEmailAddress + "' AND ItemID='" + userItemID + "'";
                                PreparedStatement pst = con_for_ISM3232AuctionDB.prepareStatement(sqlQuery);
                                pst.setString(1, newBidIDNumber);// Inserting the BID Number
                                pst.setString(2, timestamp);// Inserting the timestamp (date the bid was placed)
                                pst.setDouble(3, dblBidPrice);// Inserting the bid amount the customer has submitted                     
                                pst.executeUpdate();
                                pst.close();//closing the preparedstatement
                                out.println("Thank you for placing your bid. Click here to go back.");
                                out.println("<a href= 'index.jsp'   >");
                                out.println("User Menu");
                                out.println("</a>");
                            }
                        } else {
                            if (dblBidPrice <= dblBidAmount) {
                                out.println("Sorry, you must bid above the current bid amount.");
                                out.println("<a href= 'javascript:history.back(-1)'>");
                                out.println("Back to item listings");
                                out.println("</a>");
                            } else {

                                String sqlQuery = "INSERT INTO Bid VALUES (?,?,?,?,?)";
                                PreparedStatement pst = con_for_ISM3232AuctionDB.prepareStatement(sqlQuery);

                                pst.setString(1, newBidIDNumber);// Inserting the BID Number
                                pst.setString(2, userItemID); // Inserting the Item Number
                                pst.setString(3, userEmailAddress);// Inserting the Email Address
                                pst.setString(4, timestamp);// Inserting the timestamp (date the bid was placed)
                                pst.setDouble(5, dblBidPrice);// Inserting the bid amount the customer has submitted                     
                                pst.executeUpdate();
                                pst.close();//closing the preparedstatement
                                out.println("Thank you for placing your bid. Click here to go back.");
                                out.println("<a href= 'index.jsp'   >");
                                out.println("User Menu");
                                out.println("</a>");
                            }
                        }
                    } else {

                        // 1. Start of query for asking price*******************************************************************************    

                        if (dblBidPrice <= dbAskingPrice) {
                            out.println("Sorry, you must bid above the asking price. Click here to try again.");
                            out.println("<a href= 'javascript:history.back(-1)'>");
                            out.println("Back to item listings");
                            out.println("</a>");
                        } else {
                            String sqlQuery = "INSERT INTO Bid VALUES (?,?,?,?,?)";
                            PreparedStatement pst = con_for_ISM3232AuctionDB.prepareStatement(sqlQuery);

                            pst.setString(1, newBidIDNumber);// Inserting the BID Number
                            pst.setString(2, userItemID); // Inserting the Item Number
                            pst.setString(3, userEmailAddress);// Inserting the Email Address
                            pst.setString(4, timestamp);// Inserting the timestamp (date the bid was placed)
                            pst.setDouble(5, dblBidPrice);// Inserting the bid amount the customer has submitted                     
                            pst.executeUpdate();
                            pst.close();//closing the preparedstatement

                            out.println("Thank you for placing your bid. Click here to go back.");
                            out.println("<a href= 'index.jsp'   >");
                            out.println("User Menu");
                            out.println("</a>");
                        }

                    }//end of else
                }
//End of query for bid amount****************************************************************************************** 
                out.println("<br/>");


                out.println("</div>");// Closing tag for "Mainboxer"
                out.println("</div>");// Closing tag for "Container"
                out.println("<script src='CSSCL/commonfooter.js'></script>");


                out.println("</body>");// Closing body tag
                out.println("</html>");// End of HTML Code


                con_for_ISM3232AuctionDB.close();// closing the database connection
                
            }
        } catch (ClassNotFoundException e) {
            out.println("Couldn't load database driver: " + e.getMessage());
        } catch (SQLException e) {
            out.println("SQLException caught: " + e.getMessage());
        } catch (Exception e) {
            out.println(e);
            out.println("<br/>");
            out.println("Invalid Input. Please try again");
            out.println("<a href= 'javascript:history.back(-1)'>Bak to the item listings</a>");
        }//end of try catch
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
