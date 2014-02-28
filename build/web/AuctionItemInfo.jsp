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
  <title>My Bids In Progress Listings</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="all">
  <![endif]-->

</head>

<%@include file ="/inc/cmnHeader.jsp" %>  

        <div id="mainboxer">
                <div class="container">

                <%@include file ="/inc/cmnSession.jsp" %>
                <%String userPersonalID = (String) session.getAttribute("PersonID"); %>
                <%String userPersonalEmail = (String) session.getAttribute("EmailAddress");%>
                <input type="button" value="Print This Page" onclick="window.print()"/>
                </br>
                <h2 align="center"><u>Item Information</u></h2>               

                <%
                    String strItemListingNumber = request.getParameter("ItemID"); 
                    String strItemListingName = request.getParameter("ItemName"); 
                    String strItemDescription = request.getParameter("ItemDescription");

                    String strAskingPrice = request.getParameter("AskingPrice"); 
                    String strListingDate = request.getParameter("ListingDate"); 
                    String strExpirationDate = request.getParameter("ExpirationDate"); 
                    String strPicturePath = request.getParameter("PicturePath"); 
   
                    //Assigning the ItemNumber from the listing to a session variable
                    //so that we can write the item number into the database
                    session.setAttribute("ItemNumber", strItemListingNumber );
                    String strItemID = (String) session.getAttribute("ItemNumber");                                                     
                %>
               
            <div id="biddiv">
            <!--form action="DoBid" name="bid" onSubmit="return checkForm();>">--> 

            <form action="DoBid" name="bid">
             <%@include file ="/inc/cmnDBOpen.jsp" %>
            <%
            String QueryString = "SELECT EmailAddress FROM Item, Person WHERE Item.ItemID = '" + strItemID + "' AND Person.PersonId = Item.PersonID";
            rs = statement.executeQuery(QueryString);

            //Displaying the highest bid amount from the database into a table cell
            if (rs.next()) {

                            String strItemEmailAddress = rs.getString("EmailAddress");                
            %>
                
            <!--item's picture-->           
            <img src=<%=strPicturePath%> alt="" width="200" height="160"/>
            <br/>
            <b>Item Number:</b>
            <%=strItemListingNumber%>
            <br/>
            <b>Item Name:</b> 
            <font color="#FF8040"><b>
            <%=strItemListingName%>
            </b></font>
            <br/>
            <b>Item Description:</b> 
            <%=strItemDescription%>

            <br/>
            <b>Seller Email:</b>
            <%
            Statement personstatement = null;
            personstatement = con.createStatement();

            ResultSet rsForState = null;
            String QueryState = "SELECT Statement FROM Person WHERE EmailAddress='" +strItemEmailAddress + "'";
            rsForState = personstatement.executeQuery(QueryState);
            String delUrl="";
            String st="";
            //Displaying the highest bid amount from the database into a table cell
            if (rsForState.next()) {
                     //out.println("<a target='blank' href='com.zi.cilent.Chatting'>");
                     //String delUrl = "Messages?action=loginRoom&username="+strItemEmailAddress;
                     st=rsForState.getString(1);
                     if(st.equals("1")){
                     delUrl = "Messages?action=loginRoom&username="+userPersonalEmail;

                     }else{
                     delUrl = "help.jsp";                 
                     }
                
            }
                     out.println("<a target='blank' href='" + delUrl + "'>");
                     out.println(strItemEmailAddress);
                     out.println("</a>");          
               
                rsForState.close();
                personstatement.close();     
             %>            

            
            <!--
            <a herf ="chatting">
            <%--<%=strItemEmailAddress%>--%>
            </a>
            -->
            <br/>
            <b>Asking Price:</b> 
            <%=strAskingPrice%>
            <br/>
            <%            
               }
                rs.close();
                statement.close();     
             %>
            <%@include file ="/inc/cmnDBClose.jsp"%>            
            <b>Current Bid:</b> 
            <!--start for the 1st sql search-->              
            <%@include file ="/inc/cmnDBOpen.jsp" %>             
            <%        

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
            %>  
            <font color="#FF8040">
             No bids received yet.   
            </font>
            <%
            } else {
                    out.println(currency.format(getMaxBidAmount));
                }
            } 
            rs.close();
            statement.close();                    
            %> 
            <!--start for the 2nd sql search-->            
            <%@include file ="/inc/cmnDBClose.jsp"%>
 
            <!--close for the 1st sql search--> 
            
            <br/>
            <b>Current bidder:</b>
            <%@include file ="/inc/cmnDBOpen.jsp" %>
            <%      
            
            // We will now retrieve the highest bidder from the Bid table so that we can compare the bid with what other users placed for that item.
            // We will also use the session variable "HighestBidAmount" from earlier in the program to gather our result.            
           String queryStringForHighestBidder = "SELECT BidderID FROM Bid WHERE ItemID = '" + strItemID + "' ORDER BY BidAmount DESC";
            //String queryStringForHighestBidder = "SELECT BidderID FROM Bid WHERE ItemID = '" + strItemID + "'" + "and BidAmount = " + strMaxBidConvertToDouble + "'";
            
            rs = statement.executeQuery(queryStringForHighestBidder);

            //Displaying the name of the highest bidder from the database into a table cell
            if (rs.next()) {
                out.println(rs.getString(1));                 
            }  
            rs.close();
            statement.close();
            %>
            <%@include file ="/inc/cmnDBClose.jsp"%>  
            <!--close for the 2nd sql search-->
            <br/>
            <b>Listing Date:</b>
            <%=strListingDate%>
            <br/>
            <b>Expiration Date:</b>
            <%=strListingDate%>
            <br/>

           <%
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
            %>
            <font color="red">Bidding is now closedBidding for the item.</font>
            <br/>
            <!--start for the 3rd sql search-->            
            <%@include file ="/inc/cmnDBOpen.jsp" %>            
            <%
           //for winner
            String winner;                 
            String queryStringForwinner = "SELECT Person.FirstName FROM Bid,Person WHERE Bid.BidderID=Person.EmailAddress and ItemID = '" + strItemID + "' and  BidAmount=(SELECT Max (BidAmount) As maxBidAmount from Bid where ItemID = '" + strItemID + "')"; 
            rs = statement.executeQuery(queryStringForwinner);
             if (rs.next()) {
                winner=rs.getString(1);             
            %>
            <font color="red">winner:</font>
            <br/>
            <b>You Bid:$ </b> <input type ="text" style="background-color:#B2B2B2;" id="textbox2" name="winner" readonly />
            <br/>
            <%
              }
            %>
            
            <%@include file ="/inc/cmnDBClose.jsp"%> 
            <!--close for the 3rd sql search-->
            <%
            } else {
            %>
            <font color="green"><b>Time remaining: </b>
            <%
                long diff = milliseconds2 - milliseconds1;

                long diffSeconds = diff / 1000;
                long diffMinutes = diff / (60 * 1000);
                long diffHours = diff / (60 * 60 * 1000);
                long diffDays = diff / (24 * 60 * 60 * 1000);
            %>
            <br/>
            Days:<%=diffDays%>   Hours:<%=diffHours%> Minutes:<%=diffMinutes%> Seconds:<%=diffSeconds%>
            </font>
            <br/>
            <br/>
            <b>Your Bid: $</b> <input type ="text" id="textbox1" name="txtUserBidAmount" value=""/>            
            <br/>
            <br/>
            <input type="submit" value="Submit Bid"/>
            <input type="reset"/>
            <br/>
            <br/>
            <%}%>  
                      

            </form>

            </div>
            
            </div>
        </div>

        <script src="css/commonfooter.js"></script>

    </body>
</html>        