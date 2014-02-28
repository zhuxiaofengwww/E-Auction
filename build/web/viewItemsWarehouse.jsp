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
  <title>Item WareHouse</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="all">
  <![endif]-->

</head>

<%@include file ="/inc/cmnHeaderForMan.jsp" %>  

  <div id="mainboxer">
  	<div class="container">
        <h3>Here are the Items records in the warehouse </h3>
        <input type='button' value='Print This Page' onclick='window.print()'/>
        <%@include file ="/inc/cmnSession.jsp" %>
        <%@include file ="/inc/cmnDBOpen.jsp" %>
        <%



                //String sqlQuery = "SELECT Item.ItemID, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Item.ListingDate, Item.ExpirationDate,Item.ItemCategory,Bid.BidAmount FROM Item, Bid   WHERE Item.ItemId = Bid.ItemID  and BidderID = '" + userPersonalEmail + "ORDER BY BidAmount'";
                String sqlQuery = "SELECT *FROM Item";

                rs = statement.executeQuery(sqlQuery);
                //Creating an HTML table to hold/display the results from the database
                //out.println("<TABLE id='tablestyle'>");//Start of HTML Table

                out.println("<form action='AuctionItemInfo.jsp' method=post name='grabauctioniteminfo'>");

                out.println("<TABLE id='tablestyle'>");//Start of HTML Table                
                out.println("<tr><th colspan = '9'align='center' >Current Bids Records</th></tr>");

                out.println("<th >Item ID</th>");
                out.println("<th >Person ID</th>");
                out.println("<th >Item Name</th>");
                out.println("<th >Item Description</th>");
                out.println("<th >AskingPrice</th>");
                out.println("<th >ListingDate</th>");
                out.println("<th >ExpirationDate</th>");  
                out.println("<th >ItemCategory</th>");
                out.println("<th >PicturePath</th>");                                                                      
                //Displaying each record from the database into a table cell
                while(rs.next()) {
                    
                // Creating a hyperlink with the ItemID number so that the user can
                // view their bid information on the "AuctionItemInfo" page.

                //   String delUrl = "AuctionItemInfo.jsp?ItemID=" + rs.getString(1) +  "&ItemName=" + rs.getString(2) + "&ItemDescription=" + rs.getString(3) + "&AskingPrice=" + (currency.format(rs.getDouble(4))) + "&ListingDate=" + rs.getString(5) + "&ExpirationDate=" + rs.getString(6) + "&PicturePath=" + rs.getString(7) + "'";
                //   delUrl = delUrl.replaceAll("\\s", "%20");


                     // printing out the Item info into an HTML table
                     out.println("<TR>");

                     // Adding Item Number to the table and passing the value through the URL
                     out.println("<td>");
                     out.println(rs.getString(1));
                     out.println("</td>");
                     
                    // printing out the Bid ID into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(2));
                    out.println("</td>");

                    // printing out the Winning Bid into an HTML table                    
                    out.println("<td>");
                    out.println(rs.getString(3));
                    out.println("</td>");
                    
                     // printing out the Bidder ID of Bid into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(4));
                    out.println("</td>");
                    
                    // printing out the Winning Bid of Bid into an HTML table
                    out.println("<td>");
                    out.println(currency.format(rs.getDouble(5)));
                    out.println("</td>");
                                     
                    // printing out the Completion Date of Bid into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(6));
                    out.println("</td>"); 
                    
                     // printing out the Bidder ID of Bid into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(7));
                    out.println("</td>"); 
                                      
                     // printing out the Bidder ID of Bid into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(8));
                    out.println("</td>"); 
                    
                      // printing out the Bidder ID of Bid into an HTML table
                    out.println("<td>");
                    out.println(rs.getString(9));
                    out.println("</td>");                    
                }

                out.println("</TABLE>");
                //End of the HTML table
                out.println("</FORM>");
        %>
        <%@include file ="/inc/cmnDBClose.jsp"%>        

 </div>
 </div>

  <script src="css/commonfooter.js"></script>

</body>
</html>
