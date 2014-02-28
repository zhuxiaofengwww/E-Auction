<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Home Page</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="CSSCL/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="CSSCL/style.css" type="text/css" media="all">
  <script type="text/javascript" src="js/jquery-1.4.2.min.js" ></script>
  <script type="text/javascript" src="js/cufon-yui.js"></script>
  <script type="text/javascript" src="js/Humanst521_BT_400.font.js"></script>
  <script type="text/javascript" src="js/Humanst521_Lt_BT_400.font.js"></script>
	<script type="text/javascript" src="js/roundabout.js"></script>
  <script type="text/javascript" src="js/roundabout_shapes.js"></script>
  <script type="text/javascript" src="js/gallery_init.js"></script>
  <script type="text/javascript" src="js/cufon-replace.js"></script>
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="CSSCL/ie/ie6.css" type="text/css" media="all">
  <![endif]-->


<script type="text/javascript">
   function checkForm(){
	  var valid=true;
	  var message="Error: \n";
	  with(document.register)
	  {
		  if(txtFirstName.value==""){
			  valid=false;
			  message=message+"FirstName can not be blank \n";
			  txtFirstName.style.background="#bdbec2";
			  }else{
				  txtFirstName.style.background="white";
				  }				  
				  				  		  
		  if(txtLastName.value==""){
			  valid=false;
			  message=message+"LastName can not be blank \n";
			  txtLastName.style.background="#bdbec2";
			  }else{
				  txtLastName.style.background="white";
				  }
 		  if(txtEmailAddress.value==""){
			  valid=false;
			  message=message+"EmailAddress can not be blank \n";
			  txtEmailAddress.style.background="#bdbec2";
			  }else{
				  txtEmailAddress.style.background="white";
				  } 
 
 		  if(txtPassword.value==""){
			  valid=false;
			  message=message+"userPssword can not be blank \n";
			  txtPassword.style.background="#bdbec2";
			  }else{
				  txtPassword.style.background="white";
				  }
  		  if(txtStreetAddress1.value==""){
			  valid=false;
			  message=message+"StreetAddress can not be blank \n";
			  txtStreetAddress1.style.background="#bdbec2";
			  }else{
				  txtStreetAddress1.style.background="white";
				  }
                  /*                
  		  if(txtStreetAddress2.value==""){
			  valid=false;
			  message=message+"StreetAddress can not be blank \n";
			  txtStreetAddress2.style.background="#bdbec2";
			  }else{
				  txtStreetAddress2.style.background="white";
				  }
                                  */
  		  if(txtCity.value==""){
			  valid=false;
			  message=message+"City can not be blank \n";
			  txtCity.style.background="#bdbec2";
			  }else{
				  txtCity.style.background="white";
				  } 
   		  if(txtState.value==""){
			  valid=false;
			  message=message+"State can not be blank \n";
			  txtState.style.background="#bdbec2";
			  }else{
				  txtState.style.background="white";
				  } 
   		  if(txtPostalCode.value==""||isNaN(txtPostalCode.value)){
			  valid=false;
			  message=message+"PostalCode should be a valid number \n";
			  txtPostalCode.style.background="#bdbec2";
			  }else{
				  txtPostalCode.style.background="white";
				  } 
   		  if(txtCountry.value==""){
			  valid=false;
			  message=message+"Country can not be blank \n";
			  txtCountry.style.background="#bdbec2";
			  }else{
				  txtCountry.style.background="white";
				  }                                  
          }
			  
		  if(!valid)
		     alert(message);
		  return valid;
		  
	   }
   </script>
  </head>
<%@include file ="/inc/cmnHeader.jsp" %>  

  <div id="mainbox">
  	<div class="container">
            <div id="register">
                    <form action="RegistrationServlet"  name="register" method="post" onSubmit="return checkForm();">
                        <table height="300px" border = "0">
                            <tr><th align= 'center' colspan='2'>Registration Form</th></tr>
                            <tr>
                                <td>First Name : </td>
                                <td><input type="text" name="txtFirstName" size="40"/></td>
                            </tr>
                            <tr>
                                <td>Last Name : </td>
                                <td><input type="text" name="txtLastName" size="40"/></td>
                            </tr>
                            <tr>
                                <td>Email Address : </td>
                                <td><input type="text" name="txtEmailAddress" size="40"/></td>
                            </tr>

                            <tr>
                                <td>Password : </td>
                                <td><input type="password" name="txtPassword" size="40"/></td>
                            </tr>
                            <tr>
                                <td>Street Address 1 : </td>
                                <td><input type="text" name="txtStreetAddress1" size="40"/></td>
                            </tr>
                            <tr>
                                <td>Street Address 2 : </td>
                                <td><input type="text" name="txtStreetAddress2" size="40"/></td>
                            </tr>

                            <tr>
                                <td>City : </td>
                                <td><input type="text" name="txtCity" size="40"/></td>
                            </tr>
                            <tr>
                                <td>State : </td>
                                <td><input type="text" name="txtState" size=10/></td>
                            </tr>
                            <tr>
                                <td>Postal Code : </td>
                                <td><input type="text" name="txtPostalCode" size=10/></td>
                            </tr>

                            <tr>
                                <td>Country : </td>
                                <td><input type="text" name="txtCountry" size=10/></td>
                            </tr>
                            <tr>
                                <td>
                                    <input type="submit" size="15"value="Submit"/>
                                </td>
                                <td>
                                    <input type="reset" size="15" value="clear"/> 
                                </td>
                            </tr>                    

                        </table>


                    </form>
            </div>
  	</div>
  </div>
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
  <script type="text/javascript"> Cufon.now(); </script>
</body>
</html>
