<%-- 
    Document   : BadLogin
    Created on : Apr 10, 2012, 3:38:35 PM
    Author     : Xiaofeng
--%>
<%@ page import="java.sql.*" %>
<%@ page import="java.io.*" %> 
<%@ page import="java.text.*" %> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Items Available For Sale</title>
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
                <% String strItemName = request.getParameter("name");
                    String strselItemCategory = request.getParameter("select");%>
                <%@include file ="/inc/cmnDBOpen.jsp" %> 
                <%


                                if(strItemName.equalsIgnoreCase("all"))
                                {String QueryString0= "SELECT ItemID, EmailAddress,ItemName,ItemDescription, AskingPrice, ListingDate, ExpirationDate, PicturePath FROM Person, Item   WHERE ItemCategory LIKE'%" + strselItemCategory + "%' AND Person.PersonId = Item.PersonID ORDER BY ListingDate ";
                                 //String QueryString0= "SELECT ItemID, EmailAddress,ItemName,ItemDescription, AskingPrice, ListingDate, ExpirationDate, PicturePath FROM Person, Item   WHERE ItemCategory = '" + strselItemCategory + "' AND Person.PersonId = Item.PersonID ORDER BY ListingDate ";
                                rs = statement.executeQuery(QueryString0);
                                }
                                        else{
                                if(strselItemCategory.equalsIgnoreCase("all"))
                                {
                                String QueryString1= "SELECT ItemID, EmailAddress,ItemName,ItemDescription, AskingPrice, ListingDate, ExpirationDate, PicturePath FROM Person, Item   WHERE Item.ItemName LIKE'%" + strItemName + "%' AND Person.PersonId = Item.PersonID ORDER BY ListingDate ";
                                //String QueryString1= "SELECT ItemID, EmailAddress,ItemName,ItemDescription, AskingPrice, ListingDate, ExpirationDate, PicturePath FROM Person, Item   WHERE Item.ItemName='" + strItemName + "' AND Person.PersonId = Item.PersonID ORDER BY ListingDate ";

                                    rs = statement.executeQuery(QueryString1);
                                }
                                else{

                                // SQL query to retrieve all values from the Item table that the registered users listed.
                                String QueryString2 = "SELECT ItemID, EmailAddress,ItemName,ItemDescription, AskingPrice, ListingDate, ExpirationDate, PicturePath FROM Person, Item   WHERE ItemCategory = '" + strselItemCategory + "' AND Item.ItemName LIKE'%" + strItemName + "%'AND Person.PersonId = Item.PersonID ORDER BY ListingDate ";

                                    rs = statement.executeQuery(QueryString2);
                            } }
                                out.println("<form action='AuctionItemInfo.jsp' method=post name='grabauctioniteminfo'>");
                                //Creating an HTML table to hold/display the results from the database
                                out.println("<TABLE id='tablestyle'");//Start of HTML Table
                                out.println("<tr><th colspan = '9'align='center'>Item Catalog</th></tr>");
                                out.println("<th >Item ID</th>");
                                out.println("<th >Seller E-Mail</th>");
                                out.println("<th >Item Name</th>");
                                out.println("<th >Description</th>");
                                out.println("<th >Asking Price</th>");
                                out.println("<th >Listing Date</th>");
                                out.println("<th >Expiration Date</th>");
                                out.println("<th >Bid</th>");

                                //Displaying each record from the database into a table cell
                                while (rs.next()) {

                                    out.println("<TR>");

                                    // Adding Item ID to the table with hyperlink to the item number
                                    String delUrl = "AuctionItemInfo.jsp?ItemID=" + rs.getString(1) + "&EmailAddress=" + rs.getString(2) + "&ItemName=" + rs.getString(3) + "&ItemDescription=" + rs.getString(4) + "&AskingPrice=" + (currency.format(rs.getDouble(5))) + "&ListingDate=" + rs.getString(6) + "&ExpirationDate=" + rs.getString(7) + "&PicturePath=" + rs.getString(8) ;

                                    delUrl = delUrl.replaceAll("\\s", "%20");

                                    // Adding Item Number to the table
                                    out.println("<td>");
                                    out.println("<a href='" + delUrl + "'>");
                                    String strPicturePath=rs.getString(8);
                                    //out.println(rs.getString(1));%>

                                    <img src=<%=strPicturePath%> alt="" width="50" height="40"/>
                                    <%                   
                                    out.println("</a>");
                                    out.println("</td>");

                                    // Adding Seller E-Mail to the table
                                    out.println("<td>");
                                    out.println(rs.getString(2));
                                    out.println("</td>");

                                    // Adding Item Name to the table
                                    out.println("<td>");
                                    out.println(rs.getString(3));
                                    out.println("</td>");

                                    // Adding Item Description to the table
                                    out.println("<td>");
                                    out.println(rs.getString(4));
                                    out.println("</td>");

                                    // Adding Asking Price to the table
                                    out.println("<td>");
                                    out.println(currency.format(rs.getDouble(5)));
                                    out.println("</td>");

                                    // Adding Listing Date to the table
                                    out.println("<td>");
                                    out.println(rs.getString(6));
                                    out.println("</td>");

                                    // Adding Expiration Date to the table 
                                    out.println("<td>");
                                    out.println(rs.getString(7));
                                    out.println("</td>");

                                    // Adding a "Place Your Bid button to the screen"
                                    out.println("<td>");
                                    
                                    out.println("<a href='" + delUrl + "'>");
                                    //value ='Place Bid'
                                    out.println("<input type = 'button' value ='Place Bid'/>");//bid button
                                    
                                    out.println("</a>");
                                    out.println("</td>");

                                    // Adding a bid button to each row in the table so a user can place a bid.
                                    /*out.println("<td>");                    
                                    out.println("<a href='Bid?ItemID=" + rs.getString(1) + "&EmailAddress=" + rs.getString(2) + "&ItemName=" + rs.getString(3) + "&ItemDescription=" + rs.getString(4) + "&AskingPrice=" + (currency.format(rs.getDouble(5))) + "&ListingDate=" + rs.getString(6) + "&ExpirationDate=" + rs.getString(7) + "'>");
                                    out.println("<input type = 'button' value ='Place Your Bid' />");//bid button
                                    out.println("</a>");
                                    out.println("</td>");*/

                                    /*out.println("<td>");
                                    out.println("<a href='UpdateItem?ItemID=" + rs.getString(1) +"'>");                    
                                    out.println("<input type = 'button' value ='Update Item' />");//update button
                                    out.println("</a>");
                                    out.println("</td>");*/

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
