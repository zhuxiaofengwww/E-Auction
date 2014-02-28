<%
String BidItemID= request.getParameter("BidItemID");
//out.println(BidItemID);
if(!(BidItemID.equals(""))){  
%>                  

                    <%@include file ="/inc/cmnDBOpen.jsp" %> 
                    <%         



                                            // From CheckLogin.java, we stored the user Person ID into a session variable called "PersonID".
                                            // We will now use the PersonID value to query the Item table to display all the bids the seller has received.
                                            //String userPersonalID = (String) session.getAttribute("PersonID");
                                            //String sqlQuery = "SELECT PersonID, ItemName, ItemDescription, AskingPrice, BidderID, BidAmount, TimeStampForBid FROM [Bid] AS bd INNER JOIN Item AS im ON bd.ItemID = im.ItemID  WHERE PersonId  = '" + userPersonalID + "'";
                                            //String sqlQuery = "SELECT Item.ItemID, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Bid.BidderID, Bid.BidAmount, Bid.TimeStampForBid,Item.ExpirationDate, Item.PicturePath, Item.ListingDate  FROM Bid, Item WHERE Bid.ItemID = Item.ItemID  AND PersonId  = '" + userPersonalID + "'ORDER BY ItemID";

                                            String sqlQuery = "SELECT  Item.ItemID, Item.ItemName, Item.ItemDescription, Item.AskingPrice, Bid.BidderID, Bid.BidAmount, Bid.TimeStampForBid,Item.ExpirationDate, Item.PicturePath, Item.ListingDate  FROM Bid, Item WHERE Item.ItemID = '" + BidItemID + "' AND Bid.ItemID = '" + BidItemID + "' ";
                                            
                                            //String sqlQuery = "SELECT DISTINCT Bid.ItemID FROM Bid";
                                            rs = statement.executeQuery(sqlQuery);
                                        


                                            //Creating an HTML table to hold/display the results from the database
                                            out.println("<form action='AuctionItemInfo.jsp' method=post name='grabauctioniteminfo'>");                       
                                            out.println("<TABLE id='tablestyle'>");//Start of HTML Table
                                            out.println("<tr><th align ='center' colspan = '8' style='background-image: url(images/thtop.png);'><font color='white'>Auction Items</font></th></tr>");

                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Item ID</font></th>");
                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Item Name</font></th>");
                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Item Description</font></th>");
                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Asking Price</font></th>");
                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Bidder ID</font></th>");
                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Bid Amount</font></th>");
                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Time of Bid</font></th>");
                                            out.println("<th style='background-image: url(images/thtop.png);'><font color='white'>Bid Statement</font></th>");
                                            //out.println("<th style='background-color: #FF0000;'><font color='white'>Bid Statement</font></th>");

                                            //Displaying each record from the database into a table cell
                                            while (rs.next()) {

                    // Creating a hyperlink with the ItemID number so that the user can
                    // view their bid information on the "AuctionItemInfo.jsp" page.

                                        String delUrl = "AuctionItemInfo.jsp?ItemID=" + rs.getString(1) + "&EmailAddress=" + userPersonalEmail + "&ItemName=" + rs.getString(2) + "&ItemDescription=" + rs.getString(3) + "&AskingPrice=" + (currency.format(rs.getDouble(4))) + "&ListingDate=" + rs.getString(10) + "&ExpirationDate=" + rs.getString(8) + "&PicturePath=" + rs.getString(9) + "'";
                                        delUrl = delUrl.replaceAll("\\s", "%20");


                                        // printing out the Item ID into an HTML table
                                        out.println("<TR>");

                                        // Adding Item Number to the table and passing the value through the URL
                                        out.println("<td>");
                                        out.println("<a href='" + delUrl + "'>");
                                        out.println(rs.getString(1));
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

                                                // printing out the Bidder ID into an HTML table 
                                                out.println("<td>");
                                                out.println(rs.getString(5));
                                                out.println("</td>");

                                                // printing out the Bid Amount into an HTML table
                                                out.println("<td>");
                                                out.println(currency.format(rs.getDouble(6)));
                                                out.println("</td>");

                                                // printing out the Time Stamp (the date the bid was placed) into an HTML table 
                                                out.println("<td>");
                                                out.println(rs.getString(7));
                                                out.println("</td>");

                                        // printing out the Bid satement an HTML table
                                        out.println("<td>");

                                        String strExpirationDate=rs.getString(8);
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
                                String strItemID=rs.getString(1);
                                Double mybidamount=rs.getDouble(6);                           
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
                                            out.println("<font color='red'>");
                                            out.println("<blink>");
                                            out.println("winner");
                                            out.println("</blink>");
                                            out.println("</font>");

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
                    //                        rsForwinner.close();
                    //                        statementForwinner.close();
                                           
                    %> 
                    <%@include file ="/inc/cmnDBClose.jsp"%> 
<%                    
 }else{
                                           
 }  
%>                 