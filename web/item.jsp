<%-- 
    Document   : BadLogin
    Created on : Apr 10, 2012, 3:38:35 PM
    Author     : Xiaofeng
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>Seller Item Form</title>
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
	  with(document.listitemform)
	  {
		  if(txtItemName.value==""){
			  valid=false;
			  message=message+"ItemName can not be blank \n";
			  txtItemName.style.background="#ae1d54";
			  }else{
				  txtItemName.style.background="white";
				  }				  
				  				  		  
		  if(txtDescription.value==""){
			  valid=false;
			  message=message+"Description can not be blank \n";
			  txtDescription.style.background="#ae1d54";
			  }else{
				  txtDescription.style.background="white";
				  }
   		  if(txtAskingPrice.value==""||isNaN(txtAskingPrice.value)){
			  valid=false;
			  message=message+"AskingPrice should be a valid number \n";
			  txtAskingPrice.style.background="#ae1d54";
			  }else{
				  txtAskingPrice.style.background="white";
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
                                    <form action="AddItems" method=post name = 'listitemform' onSubmit="return checkForm();">
                                              <table height="380px" border = "1">
                                                    <tr><th align= 'center' colspan='2'>Seller Item Form</th></tr>
                                                    <tr>
                                                        <td>Item Name : </td>
                                                        <td><input type="text" name="txtItemName" size="37"/></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Description : </td>
                                                        <td><textarea rows="5" cols="40" name="txtDescription"></textarea></td>
                                                    </tr>
                                                    <tr>
                                                        <td>Asking Price : </td>
                                                        <td><input type="text" name="txtAskingPrice" size="5"/><INPUT type="file" name="imgUpload" /></td>
                                                    </tr> 
                                                    <tr>
                                                        <td>Select a category for your item : </td>

                                                        <td>
                                                            <select name="selItemCategory" >

                                                                <option value="books">Books</option>
                                                                <option value="business">Business</option>
                                                                <option value="clothing">Clothing</option>
                                                                <option value="parts">Parts</option> 
                                                                <option value="others">Others</option>                            

                                                            </select>               
                                                    <tr>
                                                        <td>Enter an expiration date for your listed item : </td>

                                                        <td>
                                                            <select name="selMonth" >
                                                                <option value="01">January</option>
                                                                <option value="02">February</option>
                                                                <option value="03">March</option>
                                                                <option value="04">April</option>
                                                                <option value="05">May</option>
                                                                <option value="06">June</option>
                                                                <option value="07">July</option>
                                                                <option value="08">August</option>
                                                                <option value="09">September</option>
                                                                <option value="10">October</option>
                                                                <option value="11">November</option>
                                                                <option value="12">December</option>
                                                            </select>
                                                            <select name="selDay">
                                                                <option value="01">01</option>
                                                                <option value="02">02</option>
                                                                <option value="03">03</option>
                                                                <option value="04">04</option>
                                                                <option value="05">05</option>
                                                                <option value="06">06</option>
                                                                <option value="07">07</option>
                                                                <option value="08">08</option>
                                                                <option value="09">09</option>
                                                                <option value="10">10</option>
                                                                <option value="11">11</option>
                                                                <option value="12">12</option>
                                                                <option value="13">13</option>
                                                                <option value="14">14</option>
                                                                <option value="15">15</option>
                                                                <option value="16">16</option>
                                                                <option value="17">17</option>
                                                                <option value="18">18</option>
                                                                <option value="19">19</option>
                                                                <option value="20">20</option>
                                                                <option value="21">21</option>
                                                                <option value="22">22</option>
                                                                <option value="23">23</option>
                                                                <option value="24">24</option>
                                                                <option value="25">25</option>
                                                                <option value="26">26</option>
                                                                <option value="27">27</option>
                                                                <option value="28">28</option>
                                                                <option value="29">29</option>
                                                                <option value="30">30</option>
                                                                <option value="31">31</option>
                                                            </select> 
                                                            <select name="selYear">
                                                                <option value="2012">2012</option>   
                                                            </select>                        
                                                        </td>
                                                    </tr>
                                                    <tr>
                                    <td>Enter the length of time you want to list your sale item : </td>
                                    <td>Hours:

                                    <select name="selHour">
                                        <option value="00">00</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>     
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>      
                                    </select>
                                    Minutes:

                                    <select name="selMinutes">
                                        <option value="00">00</option>
                                        <option value="01">01</option>
                                        <option value="02">02</option>
                                        <option value="03">03</option>
                                        <option value="04">04</option>
                                        <option value="05">05</option>
                                        <option value="06">06</option>
                                        <option value="07">07</option>     
                                        <option value="08">08</option>
                                        <option value="09">09</option>
                                        <option value="10">10</option>
                                        <option value="11">11</option>
                                        <option value="12">12</option>
                                        <option value="13">13</option>
                                        <option value="14">14</option>
                                        <option value="15">15</option>
                                        <option value="16">16</option>
                                        <option value="17">17</option>
                                        <option value="18">18</option>
                                        <option value="19">19</option>
                                        <option value="20">20</option>
                                        <option value="21">21</option>
                                        <option value="22">22</option>
                                        <option value="23">23</option>     
                                        <option value="24">24</option>
                                        <option value="25">25</option>
                                        <option value="26">26</option>
                                        <option value="27">27</option>
                                        <option value="28">28</option>
                                        <option value="29">29</option>
                                        <option value="30">30</option>
                                        <option value="31">31</option>
                                        <option value="32">32</option>
                                        <option value="33">33</option>
                                        <option value="34">34</option>
                                        <option value="35">35</option>
                                        <option value="36">36</option>
                                        <option value="37">37</option>
                                        <option value="38">38</option>
                                        <option value="39">39</option>     
                                        <option value="40">40</option>
                                        <option value="41">41</option>
                                        <option value="42">42</option>
                                        <option value="43">43</option>
                                        <option value="44">44</option>
                                        <option value="45">45</option>
                                        <option value="46">46</option>
                                        <option value="47">47</option>
                                        <option value="48">48</option>
                                        <option value="49">49</option>
                                        <option value="50">50</option>
                                        <option value="51">51</option>
                                        <option value="52">52</option>
                                        <option value="53">53</option>
                                        <option value="54">54</option>
                                        <option value="55">55</option>      
                                        <option value="56">56</option>
                                        <option value="57">57</option>
                                        <option value="58">58</option>
                                        <option value="59">59</option>          
                                    </select>
                                    </td>
                                    </tr>
                                    <tr>
                                        <td>
                                                    <input type="submit" size="15" value="Submit"/>
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

  <script src="CSSCL/commonfooter.js"></script>

</body>
</html>
