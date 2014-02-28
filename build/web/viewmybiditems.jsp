<%-- 
    Document   : BadLogin
    Created on : Apr 10, 2012, 3:38:35 PM
    Author     : Xiaofeng
--%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>My Bids In Progress Listings</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="CSSCL/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="CSSCL/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="CSSCL/ie/ie6.css" type="text/css" media="all">
  <![endif]-->

</head>

<%@include file ="/inc/cmnHeader.jsp" %>  

  <div id="mainboxer">
  	<div class="container">
        <h3>Here are the current items that you have bid on</h3>
        <input type='button' value='Print This Page' onclick='window.print()'/>
        <%@include file ="/inc/cmnSession.jsp" %>
        <%@include file ="/inc/cmnDBOpen.jsp" %>
        <%

                // From CheckLogin.java, we stored the user email address into a session variable called "EmailAddress".
                // We will now use the EmailAddress value to query the Item table for the registered user's sale items.
                String userPersonalEmail = (String) session.getAttribute("EmailAddress");


                //String sqlQuery = "SELECT Item.ItemID, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Item.ListingDate, Item.ExpirationDate,Item.ItemCategory,Bid.BidAmount FROM Item, Bid   WHERE Item.ItemId = Bid.ItemID  and BidderID = '" + userPersonalEmail + "ORDER BY BidAmount'";
                String sqlQuery = "SELECT Item.ItemID, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Item.ListingDate, Item.ExpirationDate,Item.ItemCategory, PicturePath, Bid.BidAmount,Bid.BidID FROM Item, Bid   WHERE Item.ItemId = Bid.ItemID  and BidderID = '" + userPersonalEmail + "'";

                rs = statement.executeQuery(sqlQuery);
                //Creating an HTML table to hold/display the results from the database
                //out.println("<TABLE id='tablestyle'>");//Start of HTML Table

                out.println("<form action='AuctionItemInfo.jsp' method=post name='grabauctioniteminfo'>");

                out.println("<TABLE id='tablestyle'>");//Start of HTML Table                
                out.println("<tr><th colspan = '9'align='center' >My Current Bids</th></tr>");


                out.println("<th >Item ID</th>");
                out.println("<th >Item Name</th>");
                out.println("<th >Item Description</th>");

                out.println("<th >Asking Price</th>");

                out.println("<th >Listing Date</th>");
                out.println("<th >Expiration Date</th>");
                out.println("<th >Item Category</th>");                 
                out.println("<th >Bid Amount</th>");
                out.println("<th >Bid Statement</th>");

                                          
                //Displaying each record from the database into a table cell
                while (rs.next()) {
                    
// Creating a hyperlink with the ItemID number so that the user can
 // view their bid information on the "AuctionItemInfo" page.

                     String delUrl = "AuctionItemInfo.jsp?ItemID=" + rs.getString(1) + "&EmailAddress=" + userPersonalEmail + "&ItemName=" + rs.getString(2) + "&ItemDescription=" + rs.getString(3) + "&AskingPrice=" + (currency.format(rs.getDouble(4))) + "&ListingDate=" + rs.getString(5) + "&ExpirationDate=" + rs.getString(6) + "&PicturePath=" + rs.getString(8) ;
                     delUrl = delUrl.replaceAll("\\s", "%20");


                     // printing out the Item ID into an HTML table
                     out.println("<TR>");

                     // Adding Item Number to the table and passing the value through the URL
                     out.println("<td>");
                     out.println("<a href='" + delUrl + "'>");
                     String strPicturePath=rs.getString(8);
                     //out.println(rs.getString(1));%>
                     
                     <img src=<%=strPicturePath%> alt="" width="50" height="40"/>
                     <%
                     out.println("</a>");
                     out.println("</td>");


                    // printing out the Item Name into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(2));
                    out.println("</td>");

                    // printing out the Item Description into an HTML table                    
                    out.println("<td>");
                    out.println(rs.getString(3));
                    out.println("</td>");

                    // printing out the Asking Price into an HTML table and formatting it in a currency style
                    out.println("<td>");
                    out.println(currency.format(rs.getDouble(4)));
                    out.println("</td>");

                    // printing out the Listing Date into an HTML table 
                    out.println("<td>");
                    out.println(rs.getString(5));
                    out.println("</td>");

                    // printing out the Expiration Date into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(6));
                    out.println("</td>");
                    // printing out the Categary of Item into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(7));
                    out.println("</td>");                    
                    // printing out the Bid Amount into an HTML table
                    out.println("<td>");
                    out.println(currency.format(rs.getDouble(9)));
                    out.println("</td>");
                    //*********************************************
                    // printing out the Bid satement an HTML table
                    out.println("<td>");
                    
                    String strExpirationDate=rs.getString(6);
//**************************************************************************************               
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
       

             if (cal2.before(cal1)) {
//***********************************************************
            String strItemID=rs.getString(1);    
            Statement statementForcart = null;
            statementForcart = con.createStatement();

            ResultSet rsForcart = null;//variable to hold the data from the database table          
            //String queryStringForwinner = "SELECT Bid.BidderID FROM Bid WHERE ItemID = '" + strItemID + "' and  BidAmount=(SELECT Max(BidAmount) As maxBidAmount from Bid where ItemID = '" + strItemID + "')"; 
            String queryStringForcart = "SELECT ItemID from Sale where ItemID = '" + strItemID + "'"; 
            
            rsForcart = statementForcart.executeQuery(queryStringForcart);               
//***********************************************************              
                               

            Double mybidamount=rs.getDouble(9);                           
            Statement statementForwinner = null;
            statementForwinner = con.createStatement();

            ResultSet rsForwinner = null;//variable to hold the data from the database table          
            //String queryStringForwinner = "SELECT Bid.BidderID FROM Bid WHERE ItemID = '" + strItemID + "' and  BidAmount=(SELECT Max(BidAmount) As maxBidAmount from Bid where ItemID = '" + strItemID + "')"; 
            String queryStringForwinner = "SELECT Max(BidAmount) As maxBidAmount from Bid where ItemID = '" + strItemID + "'"; 
            
            rsForwinner = statementForwinner.executeQuery(queryStringForwinner);
            
             if (rsForwinner.next()) {
                //String winner=rsForwinner.getString(1); 
               Double winneramount=rsForwinner.getDouble("maxBidAmount");                 
               if(mybidamount.equals(winneramount)){
                //if(winner.equals(userPersonalEmail)){
                  if(rsForcart.next()){
                //    if(rsForcart.wasNull()){
                        out.println("<font color='blue'>");
                        out.println("In Cart!");                        
                        out.println("</font>");
                    }
                    else{
                        out.println("<font color='red'>");
                        out.println("You win it!");
                     
                        
                        out.println("<a href='AddToCart?ItemID=" + rs.getString(1) + "&BidID=" + rs.getString(10) + "&BidAmount=" + rs.getDouble(9) + "&BidderID=" + userPersonalEmail + "'>");
                        
                        out.println("<input type = 'button' value ='Add to Cart' />");//Add to CART button
                        out.println("</a>");                       
                        out.println("</font>");
                         }
                                               
                 }else{
                        out.println("<font color='green'>");
                        out.println("Closed");
                        //out.println(winneramount);
                        //out.println(mybidamount);
                        //out.println(mybidamount==winneramount);
                        //out.println(mybidamount.equals(winneramount));
                        //out.println(winner);
                        //out.println(userPersonalEmail);
                        //out.println(winner.equals(what));
                        //out.println(winner==userPersonalEmail);

                        out.println("</font>");
                 }
                }
            }
//************************************************************************************* 
            else{
                out.println("Open");
            }                                                               
                    
                    
                    out.println("</td>");

                    out.println("</TR>");

                }

                out.println("</TABLE>");
                //End of the HTML table
                out.println("</FORM>");
        %>
        <%@include file ="/inc/cmnDBClose.jsp"%>        

 </div>
 </div>


  <script src="CSSCL/commonfooter.js"></script>

</body>
</html>
