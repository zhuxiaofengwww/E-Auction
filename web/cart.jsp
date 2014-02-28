<%-- 
    Document   : cart
    Created on : Apr 16, 2012, 11:50:04 PM
    Author     : Xiaofeng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<%@ page import="java.util.*" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.*" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Shopping Cart</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="CSSCL/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="CSSCL/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="CSSCL/ie/ie6.css" type="text/css" media="all">
  <![endif]-->

</head>

<%@include file ="/inc/cmnHeader.jsp" %>  


  <!-- #gallery -->
  <div id="mainbox">
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
                                String sqlQuery = "SELECT Sale.SaleID, Item.ItemName, Item.ItemDescription, Item.AskingPrice,Sale.WinningBid,Sale.CompletionDate FROM Item, Sale   WHERE Item.ItemID = Sale.ItemID  and BidderID = '" + userPersonalEmail + "'";

                                rs = statement.executeQuery(sqlQuery);
                                //Creating an HTML table to hold/display the results from the database
                                //out.println("<TABLE id='tablestyle'>");//Start of HTML Table



                                out.println("<TABLE id='tablestyle'>");//Start of HTML Table                
                                out.println("<tr><th colspan = '6'align='center' >My Shopping Cart</th></tr>");


                                out.println("<th >Sale ID</th>");
                                out.println("<th >Item Name</th>");
                                out.println("<th >Item Description</th>");
                                out.println("<th >Asking Price</th>");
                                out.println("<th >WinningBid</th>");
                                out.println("<th >CompletionDate</th>");


                                //Displaying each record from the database into a table cell
                                while (rs.next()) {

                                    // printing out the Sale ID into an HTML table
                                    out.println("<TR>");
                                    out.println("<td>");
                                    out.println(rs.getString(1));
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

                                    // printing out the WinningBid into an HTML table and formatting it in a currency style
                                    out.println("<td>");
                                    out.println(currency.format(rs.getDouble(5)));
                                    out.println("</td>");

                                    // printing out the Listing Date into an HTML table 
                                    out.println("<td>");
                                    out.println(rs.getString(6));
                                    out.println("</td>");


                                    out.println("</TR>");

                                }

                                out.println("</TABLE>");
                                //End of the HTML table

                %>  
                <%@include file ="/inc/cmnDBClose.jsp"%>        
  	</div>
  </div>
  <!-- /#gallery -->

  <!-- footer -->
<!--
  <footer>
    <div class="container">
    	<div class="wrapper">
        <nav>
        <ul class="footer_menu">
            <li><a href="#">Home Page</a></li>
            <li><a href="#" target="_parent">About Us</a></li>
            <li><a href="#" target="_parent">My Auction</a></li>
            <li><a href="#">Sell Items</a></li>
            <li><a href="#">View Items</a></li>
            <li><a href="#">Customer & Help</a></li>
        </ul>
        </nav>
        <div class="fright">Copyright - Created by Xiaofeng Zhu,Preston Comfort and Christopher Probus</div>        
      </div>
    </div>
  </footer>
  -->
  <script src="CSSCL/commonfooter.js"></script>

</body>
</html>

