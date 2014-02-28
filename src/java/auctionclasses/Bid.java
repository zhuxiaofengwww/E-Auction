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
import java.text.DecimalFormat;
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
@WebServlet(name = "Bid", urlPatterns = {"/Bid"})
public class Bid extends HttpServlet {

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

            out.println("<!DOCTYPE html PUBLIC '-//W3C//DTD XHTML 1.0 Transitional//EN' 'http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd'>");
            out.println("<html xmlns:layout>");
            out.println("<head>");
            out.println(" <title>Home Page</title>");
            out.println("<meta charset='utf-8'>");
            out.println("<link rel='stylesheet' href='css/reset.css' type='text/css' media='all'>");
            out.println("<link rel='stylesheet' href='css/style.css' type='text/css' media='all'>");
            out.println("<!--[if lt IE 7]>");
            out.println("<link rel='stylesheet' href='css/ie/ie6.css' type='text/css' media='all'>");
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
            if(session.getAttribute("customer")!=null){
            out.println(session.getAttribute("customer"));
            out.println("<a href='registrationupdateform.jsp'>Edit Information</a>");
            out.println("&nbsp");
            out.println("<a href='LogOut'>Log Out</a>");}
            else{
                out.println("<a href='login.jsp'>Sign in</a> or <a href='register.jsp'>register</a>");
            }
            
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
            


            /*I MOVED THE SESSION CLASS OBJECT TO LINE #46*/
            /*SO THAT THE CUSTOMER'S NAME CAN SHOW ON ALL PAGES*/
           /*PUT SESSION INFO BACK HERE IF YOU ARE HAVING PROBLEMS!*/
            

//          out.println("<body bgcolor='#FFFF99'>");
            
            //Adding a print button
            out.println("<input type='button' value='Print This Page' onclick='window.print()'/>");
            out.println("<br/>");
            out.println("<h2 align='center'><u>Item Information</u></h2>");

            String strItemListingNumber = request.getParameter("ItemID");
            String strItemListingName = request.getParameter("ItemName");
            String strItemDescription = request.getParameter("ItemDescription");
            String strItemEmailAddress = request.getParameter("EmailAddress");
            String strAskingPrice = request.getParameter("AskingPrice");
            String strListingDate = request.getParameter("ListingDate");
            String strExpirationDate = request.getParameter("ExpirationDate");
            String strPicturePath = request.getParameter("PicturePath");            

            //Assigning the ItemNumber from the listing to a session variable
            //so that we can write the item number into the database

            session.setAttribute("ItemNumber", strItemListingNumber);
  
            
            out.println("<div id='biddiv'>");
//          out.println("<form action='DoBid' name='bid' onSubmit='return checkForm();'>");
            out.println("<form action='DoBid' name='bid'>");
// item's picture
            out.println("<img src=strPicturePath alt='' width='120' height='100'/>");            
//          out.println("<img src=<%=strPicturePath%> alt='' width='120' height='100'/>");
            out.println("path is"+strPicturePath);            
            out.println("<b>Item Number:</b>");// The item number the user is bidding on will be displayed in this area.
            out.println(strItemListingNumber);
            out.println("<br/>");
            out.println("<b>Item Name:</b>"); // The item name the user is bidding on will be displayed in this area.
            out.println("<font color='#FF8040'><b>");
            out.println(strItemListingName);
            out.println("</b></font>");
            out.println("<br/>");
            out.println("<b>Item Description:</b>"); // The item description the user is bidding on will be displayed in this area.
            out.println(strItemDescription);
            out.println("<br/>");
            out.println("<b>Seller:</b>"); // The seller of the item is listed in this area.
            out.println(strItemEmailAddress);
            out.println("<br/>");
            out.println("<b>Asking Price:</b>"); // The initial/minimum starting bid price will be displayed in this area.
            out.println(strAskingPrice);
            out.println("<br/>");
            out.println("<b>Current Bid:</b>"); // If the item has received any bids, the current bid amount will be displayed in this area.

            //Using a decimal format object to format our output in currency style
            DecimalFormat currency = new DecimalFormat("$#,##0.00");

            Class.forName(Utility.forClass);  // load the driver

            Connection con = null;
            con = DriverManager.getConnection(Utility.connection);

            Statement statement = null;
            statement = con.createStatement();

            ResultSet rs = null;//variable to hold the data from the database table          

            String strItemID = (String) session.getAttribute("ItemNumber");

            // SQL query to retrieve the highest bid amount from the bid table so that we can compare the bid with what other users placed for that item.
             
            /*DO NOT DELETE THIS LINE OF CODE BELOW YET!!!!!!!!!!!!!!!!*/
            //String QueryString = "SELECT MAX(BidAmount) FROM Bid WHERE ItemID = '" + strItemID + "'";
            
            
            
            String QueryString = "SELECT Max(BidAmount) FROM Bid WHERE ItemID = '" + strItemID + "'";
            rs = statement.executeQuery(QueryString);

            //Displaying the highest bid amount from the database into a table cell
            if (rs.next()) {
                double getMaxBidAmount = rs.getDouble(1);// storing the highest bid amount into a variable
                
                // We will store the max bid amount from the query
                // into a session variable so that we can distinguish
                // the highest bidder for the item later in Bid.java
                
                session.setAttribute("HighestBidAmount", getMaxBidAmount);

                if (rs.wasNull()) {
                    out.println("<font color='#FF8040'>");
                    out.println("No bids received yet.");
                    out.println("</font>");
                } else {
                    out.println(currency.format(getMaxBidAmount));
                }
            }
            
            rs.close(); // closing the result set connection

            out.println("<br/>");
            out.println("<b>Current bidder:</b>");
            
             // Now we will query the Bid table to find out who the highest bidder is for the current item
            Connection conForHighestBidder = null;
            conForHighestBidder = DriverManager.getConnection(Utility.connection);
                  
                      
            
            Statement statementForHighestBidder = null;
            statementForHighestBidder = conForHighestBidder.createStatement();

            ResultSet rsForHighestBidder = null;//variable to hold the data from the database table          
            
            // We will now retrieve the highest bidder from the Bid table so that we can compare the bid with what other users placed for that item.
            // We will also use the session variable "HighestBidAmount" from earlier in the program to gather our result.
            
            //String strMaxBid = (String) session.getAttribute("HighestBidAmount");
            //double strMaxBidConvertToDouble = Double.parseDouble(strMaxBid);
            
            String queryStringForHighestBidder = "SELECT BidderID FROM Bid WHERE ItemID = '" + strItemID + "' ORDER BY BidAmount DESC";
            //String queryStringForHighestBidder = "SELECT BidderID FROM Bid WHERE ItemID = '" + strItemID + "'" + "and BidAmount = " + strMaxBidConvertToDouble + "'";
            
            rsForHighestBidder = statementForHighestBidder.executeQuery(queryStringForHighestBidder);

            //Displaying the name of the highest bidder from the database into a table cell
            if (rsForHighestBidder.next()) {
                out.println(rsForHighestBidder.getString(1));                 
            }
            
            rsForHighestBidder.close(); // closing the result set connection
            
            
            
            out.println("<br/>");          
            
            out.println("<b>Listing Date:</b>");
            out.println(strListingDate);
            out.println("<br/>");
            out.println("<b>Expiration Date:</b>");
            out.println(strExpirationDate);
            out.println("<br/>");


            /**********************Calculating time remaining for auction item*********************************************************/
            /*Calculating time remaining for the auction item.*/
            Date today = new Date();// Getting the current date and time from the server

            String listingDate;
            listingDate = today.toString();// Converting the current date into a string variable



            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date systemDateToCompare = formatter.parse(listingDate); // converting string variable to date type
            Date listingExpirationDateForItem = formatter.parse(strExpirationDate);// converting string variable to date type

            Calendar cal1 = Calendar.getInstance();
            Calendar cal2 = Calendar.getInstance();

            cal1.setTime(systemDateToCompare);
            cal2.setTime(listingExpirationDateForItem);


            long milliseconds1 = cal1.getTimeInMillis();
            long milliseconds2 = cal2.getTimeInMillis();

            if (cal2.before(cal1)) {
                out.println("<font color='red'>");
                out.println("Bidding is now closedBidding for the item.");// Notifying the bidder that the auction is closed
                out.println("</font>");
                
                out.println("<br/>");                
            //for winner
            String winner;           
            Statement statementForwinner = null;
            statementForwinner = conForHighestBidder.createStatement();

            ResultSet rsForwinner = null;//variable to hold the data from the database table          
            String queryStringForwinner = "SELECT Person.FirstName FROM Bid,Person WHERE Bid.BidderID=Person.EmailAddress and ItemID = '" + strItemID + "' and  BidAmount=(SELECT Max(BidAmount) As maxBidAmount from Bid where ItemID = '" + strItemID + "')"; 
            rsForwinner = statementForwinner.executeQuery(queryStringForwinner);
             if (rsForwinner.next()) {
                winner=rsForwinner.getString(1);                 
           
                            
                out.println("<font color='red'>");

                out.println("winner:");
                out.println(winner);
                out.println("</font>");
                
                out.println("<br/>");
                
                 }
                // Disabling the text box to prevent bids after the expiration date has passed
                out.println("<b>You Bid:$ </b> <input type ='text' style='background-color:#B2B2B2;' id='textbox2' name='winner' readonly /> ");
                
                out.println("<br/>");             
                rsForwinner.close(); // closing the result set connection  
            } else {
            out.println("<font color = 'green'>");
            out.println("<b>Time remaining: </b>");
            out.println("</font>");                
                long diff = milliseconds2 - milliseconds1;

                long diffSeconds = diff / 1000;
                long diffMinutes = diff / (60 * 1000);
                long diffHours = diff / (60 * 60 * 1000);
                long diffDays = diff / (24 * 60 * 60 * 1000);


                out.println("<br/>");
                out.println("Days: " + diffDays
                        + " ");

                out.println("Hours: " + diffHours
                        + " ");

                out.println("Minutes: " + diffMinutes
                        + " ");

                out.println("Seconds: " + diffSeconds
                        + " ");
                out.println("</font>");
                out.println("<br/>");
                out.println("<br/>");
                out.println("<b>Your Bid: $</b> <input type ='text' id='textbox1' name='txtUserBidAmount' value=''/> ");
                out.println("<br/>");
                out.println("<br/>");
                
                
                out.println("<input type='submit' value='Submit Bid'/>");
                
                out.println("<input type='reset'/>");
                out.println("<br/>");
                out.println("<br/>");
            }


            /**********************End of calculation time remaining for auction item****************************************************/
            
            out.println("</form>");
            out.println("</div>");
 
            out.println("<br/>");

            out.println("</div>");// Closing tag for "Mainboxer"
            out.println("</div>");// Closing tag for "Container"
            out.println("<script src='css/commonfooter.js'></script>");


            out.println("</body>");// Closing body tag
            out.println("</html>");// End of HTML Code

             // close all the connections.            
            statement.close();
            con.close();

        } catch (ClassNotFoundException e) {
            out.println("Couldn't load database driver: " + e.getMessage());
        } catch (SQLException e) {
            out.println("SQLException caught: " + e.getMessage());
        } catch (Exception e) {
            out.println(e);            
            out.println("Unable to display the Item Catalog.");//error message
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
