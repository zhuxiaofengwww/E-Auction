<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Home Page</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="CSSCL/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="CSSCL/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="CSSCL/ie/ie6.css" type="text/css" media="all">
  <![endif]-->


<script type="text/javascript">
   function checkForm(){
	  var valid=true;
	  var message="Error: \n";
	  with(document.login)
	  {
		  if(userEmailAddress.value==""){
			  valid=false;
			  message=message+"userEmailAddress can not be blank \n";
			  userEmailAddress.style.background="#4bbfbf";
			  }else{
				  userEmailAddress.style.background="white";
				  }				  
				  				  		  
		  if(userPassword.value==""){
			  valid=false;
			  message=message+"userPssword can not be blank \n";
			  userPassword.style.background="#4bbfbf";
			  }else{
				  userPassword.style.background="white";
				  }
                              }
			  
          //alert('made it past the with block');
		  if(!valid)
		     alert(message);
		  return valid;
		  
	   }
   </script>
</head>
    
<%@include file ="/inc/cmnHeader.jsp" %>    


  <!-- #gallery -->
  <div id="mainbox">
  	<div class="container">
            <div id="login">
                        <form name="login" action ="checklogin" onsubmit="return checkForm();">
                            <table height="120px" border ="0">

                                <tr>
                                    <td >
                                        User Email:
                                    </td>
                                    <td>
                                        <input type="text" size="30" name="userEmailAddress" />
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        User Password:
                                    </td>
                                    <td>
                                        <input type="password" size="30" name="userPassword" />
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <input type="submit" size="15"value="Submit"/>
                                    </td>
                                    <td>
                                        <input type="reset" size="15" value="Clear"/> 
                                    </td>
                                </tr>
                            </table>                                                

                        </form>
            </div>
  	</div>
  </div>
  <!-- /#gallery -->

  <!-- footer -->

  <script src="CSSCL/commonfooter.js"></script>

</body>
</html>
