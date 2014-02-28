<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Home Page</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="all">
  <![endif]-->
  </head>
    
<!--</header>  -->
    <div id="top">

            <div id="topwrapper">
                <div class="container">

                        <div id="logo"><a href="index.jsp"><img src="images/logo.png"/></a></div>
                        <h1><a href="index.jsp">My E-Auction</a></h1>

                        <div id="user">
 

                        </div>

                        <div id="cart"><a href="cart.jsp"><img src="images/cart.png"/></a></div>
                        <div id="kid"><a href="index.jsp"><img src="images/kid.png"/></a></div>
                        <ul id="menu">
                        <li><a href="index.jsp" class="but1_active">Home Page</a></li>
                        <li><a href="about.jsp" class="but2">About Us</a></li>
                        <li><a href="viewmybiditems.jsp" class="but2">View My Bids</a></li>
                        <li><a href="item.jsp" class="but2">Sell an Item</a></li>
                        <li><a href="viewmysaleitems.jsp" class="but2">View My Items</a></li>
                        <li><a href="help.jsp" class="but2">Service & Help</a></li>
                        </ul>

                </div>

                <div id="welcome">
                    <div class="container">
                        <div id="search">
                            <form name="Search" method="get" action="browseitemlistings.jsp">
                                    <input type=text size=65 name="name" />
                                    <select name="select">
                                    <option value="all" selected="selected">All Categories</option>
                                    <option value="parts">Parts</option>
                                    <option value="books">Books</option>
                                    <option value="business">Business</option>
                                    <option value="clothing">Clothing</option>
                                    <option value="others">Others</option>
                                    </select>
                                    <input type="submit" value="Search"/>
                            </form>
                        </div>
                    </div>
                </div>    
            </div>

    </div>
    
  <div id="mainboxer">
  	<div class="container">
            <div id="design">
