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
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Advanced Searching</title>
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

                <%@include file ="/inc/cmnSession.jsp" %>
                <% String strID = request.getParameter("ID");
                   String strTable = request.getParameter("select");%>
                <input type="button" value="Print This Page" onclick="window.print()"/>
                </br>
                <h2 align="center"><u>Detail Information for your search</u></h2>               
               
            <div id="biddiv">

            <%@include file ="/inc/cmnDBOpen.jsp" %>

                <%
                String QueryString="";
                String context[]= new String[11];

                                if(strTable.equalsIgnoreCase("Bid"))
                                {   
                                    // SQL query to retrieve all values from the Person table that the registered users listed.
                                    QueryString= "SELECT Bid.ItemID, Bid.BidderID, Bid.TimeStampForBid, Bid.BidAmount, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Item.ListingDate, Item.ExpirationDate, Person.EmailAddress  FROM Bid, Item, Person  WHERE Bid.BidID = '" + strID + "' AND Bid.ItemID = Item.ItemID And  Item.PersonID = Person.PersonID "; 
                                    context[0]="Bid ID";
                                    context[1]="Item ID";                                   
                                    context[2]="Bidder EmailAddress";
                                    context[3]="TimeStampForBid";
                                    context[4]="BidAmount";  
                                    context[5]="Item Name";                                   
                                    context[6]="Item Description";
                                    context[7]="AskingPrice";
                                    context[8]="ListingDate";      
                                    context[9]="ExpirationDate";
                                    context[10]="Seller EmailAddress";                                                                   
                                                                  
                                }
                                if(strTable.equalsIgnoreCase("Sale"))
                                {
                                    // SQL query to retrieve all values from the Person table that the registered users listed.
                                    QueryString= "SELECT Sale.ItemID, Sale.WinningBid, Sale.CompletionDate, Sale.BidderID, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Item.ListingDate, Item.ExpirationDate, Person.EmailAddress FROM Sale, Person, Item   WHERE Sale.SaleID='" + strID + "' AND Sale.ItemID = Item.ItemID AND Item.PersonID = Person.PersonID ";
                                    context[0]="Sale ID";
                                    context[1]="Item ID";                                   
                                    context[2]="Winning Bid";
                                    context[3]="Completion Date";
                                    context[4]="Buyer EmailAddress";  
                                    context[5]="Item Name";                                  
                                    context[6]="Item Description";
                                    context[7]="AskingPrice";
                                    context[8]="ListingDate";      
                                    context[9]="ExpirationDate";
                                    context[10]="Seller EmailAddress";                                
                                }
                                if(strTable.equalsIgnoreCase("Item")){

                                    // SQL query to retrieve all values from the Item table that the registered users listed.
                                    QueryString = "SELECT Item.PersonID, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Item.ListingDate, Item.ExpirationDate, Item.ItemCategory, Person.EmailAddress, Person.Statement, Person.Active FROM Person, Item   WHERE Item.ItemID='" + strID + "' AND Person.PersonId = Item.PersonID ";
                                    context[0]="Item ID";
                                    context[1]="Seller ID";                                     
                                    context[2]="Item Name";                                  
                                    context[3]="Item Description";
                                    context[4]="AskingPrice";
                                    context[5]="ListingDate";      
                                    context[6]="ExpirationDate";
                                    context[7]="Item Category";       
                                    context[8]="Seller EmailAddress";
                                    context[9]="Seller Statement";                                                                                                         
                                    context[10]="Seller Validation";                                
                                }      
                                if(strTable.equalsIgnoreCase("Person")){

                                    // SQL query to retrieve all values from the Person table that the registered users listed.
                                    QueryString = "SELECT FirstName, LastName, City, State, PostalCode, Country, EmailAddress, Statement, Active, Role FROM Person WHERE PersonID = '" + strID + "' ";
                                    context[0]="Person ID";
                                    context[1]="First Name";                                     
                                    context[2]="Last Name";                                  
                                    context[3]="City";
                                    context[4]="State";
                                    context[5]="PostalCode";      
                                    context[6]="Country";
                                    context[7]="User  EmailAddress";       
                                    context[8]="User Statement";
                                    context[9]="Validation";                                                                                                         
                                    context[10]="Account Role";                                  
                                }    
                                rs = statement.executeQuery(QueryString);
  
                    out.println("<br/>");
                    out.println(context[0]);
                    out.print(":  ");
                    out.println(strID);

                    while(rs.next()) {
                    for(int i=1; i<11; i++){
                    out.println("<br/>");
                    out.println(context[i]);
                    out.print(":  ");
                    out.println(rs.getString(i));
                    }
                    }

                    rs.close();
                    statement.close();     
                %>
                <%@include file ="/inc/cmnDBClose.jsp"%> 
             </div>
            
            </div>
        </div>

       <script src="css/commonfooter.js"></script>

    </body>
</html>    