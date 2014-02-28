<%-- 
    Document   : help
    Created on : Apr 15, 2012, 1:37:02 PM
    Author     : Xiaofeng Zhu
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Service and Help</title>
  <meta charset="utf-8">
  <link rel="stylesheet" href="CSSCL/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="CSSCL/style.css" type="text/css" media="all">
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="mailsomeie/ie6.css" type="text/css" media="all">
  <![endif]-->
<script type="text/javascript">
   function checkForm(){
	  var valid=true;
	  var message="Error: \n";
	  with(document.help)
	  {
		  if(to.value==""){
			  valid=false;
			  message=message+"userEmailAddress can not be blank \n";
			  to.style.background="#bdbec2";
			  }else{
				  to.style.background="white";
				  }				  
		/*		  				  		  
		  if(from.value==""){
			  valid=false;
			  message=message+"userPssword can not be blank \n";
			  from.style.background="#4bbfbf";
			  }else{
				  from.style.background="white";
				  }
                    */              
 		  if(subject.value==""){
			  valid=false;
			  message=message+"userPssword can not be blank \n";
			  subject.style.background="#bdbec2";
			  }else{
				  subject.style.background="white";
				  } 
 
 		  if(body.value==""){
			  valid=false;
			  message=message+"userPssword can not be blank \n";
			  body.style.background="#bdbec2";
			  }else{
				  body.style.background="white";
				  }
          }
			  
		  if(!valid)
		     alert(message);
		  return valid;
		  
	   }
 function mailsome(){
  if(checkForm()){    
    with(document.help)
	  {

                    if (confirm("Are you sure you want to mail "+to.value+" with the subject of "+subject.value+"?")==true){
                    parent.location.href='mailto:'+to.value+'?subject='+subject.value+'&body='+body.value+'';
                    //parent.location.href='mailto:zhuxiaofengwww@Gmail.com';
                   }
     return true;
         }
    }else{
            return false;
            
        }
          }        
           
   </script>
</head>

<%@include file ="/inc/cmnHeader.jsp" %>  

  <div id="mainbox">
      
            <div class="container">

                <div id="help">
                            <h5>Help Request Form</h5>
                <!--<FORM  method="post" name = 'help' onSubmit=" return checkForm();" action="sendmail.jsp">-->
                            <FORM  method="post" name = 'help' onSubmit="mailsome()" action="">    

                                    <TABLE id="tablehelp">
                                        <TR>
                                        <TD> Seller's  Email Address : </TD>
                                        <TD><INPUT name="to" size="29"></TD>
                                        </TR>
                                <!--
                                    <TR>
                                        <TD> Your Email Address : </TD>
                                        <TD><INPUT name="from" size="29"></TD>
                                        </TR>
                                -->
                                        <TR>
                                        <TD> Problem Type : </TD>
                                        <TD><INPUT name="subject" size="29"></TD>
                                        </TR>
                                        <br/>
                                        <TR>     
                                        <TD> Problem Description : </TD>

                                        <TD><TEXTAREA name="body" rows=8 cols=32></TEXTAREA></TD>
                                        </TR>

                                        <TR>
                                        <TD>
                                            <INPUT type="button" name="cb_submit" size="20" value="send" onclick="mailsome()">
                                        </TD>
                                        <TD>
                                            <INPUT type="reset" name="cb_reset" size="20" value="reset">
                                        </TD>
                                        </TR>
                                    </TABLE>
                                </FORM>
                </div>
    </div>



                <SCRIPT LANGUAGE="JavaScript">

                function mailsome1(){
                who=prompt("Enter recipient's email address: ","antispammer@earthling.net");
                what=prompt("Enter the subject: ","none");
                if (confirm("Are you sure you want to mail "+who+" with the subject of "+what+"?")==true){
                parent.location.href='mailto:'+who+'?subject='+what+'';
                    }
                }
                </SCRIPT>
  </div> 
<!--
<a href='javascript:mailsome1()'>E-Mail Someone!</a>
 <FORM>
 <input type=button value="E-Mail Someone!" onClick="mailsome1()">
 </FORM>
-->   
  <script src="mailsomecommonfooter.js"></script>

</body>
</html>
