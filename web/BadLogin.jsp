<%-- 
    Document   : BadLogin
    Created on : Apr 10, 2012, 3:38:35 PM
    Author     : Xiaofeng
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Error Login</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="css/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="css/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="css/ie/ie6.css" type="text/css" media="all">
  <![endif]-->

</head>

<%@include file ="/inc/cmnHeader.jsp" %>   

  <div id="mainbox">
  	<div class="container">
            
             <div id="login">
                         <h2>INVALID LOGIN. PLEASE TRY AGAIN!</h2>
                         <br/>
                         <a href = "login.jsp">Click here to try again.</a>
                         <br/>
                         <br/>
                         Do you not have an account? <a href="register.jsp">Register</a>
             </div>
  	</div>
  </div>

  <script src="css/commonfooter.js"></script>

</body>
</html>
