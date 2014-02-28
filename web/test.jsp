<%@page contentType="text/html" pageEncoding="GBK" %>
<html>
<title>
	Chatting Room for Testing
</title>
<link href="CSSCH/style.css" rel="stylesheet">
<script language="javascript">
function check(){
	if(form1.username.value==""){
		alert("请输入用户名！");form1.username.focus();return false;	
	}else if(form1.username.value=="\'"){
		alert("请不要输入非法字符！");form1.username.focus();return false;
	}
}
</script>
<body>
<br>
<form name="form1" method="post" action="Messages?action=loginRoom" onSubmit="return check()">
    <table width="371" height="230"  border="0" align="center" cellpadding="0" cellspacing="0" background="images/login.jpg">
      <tr>
        <td height="158" colspan="3" class="word_dark">&nbsp;</td>
      </tr>
      <tr>
        <td width="53" align="center" valign="top" class="word_dark">&nbsp;</td>
        <td width="216" align="center" valign="top" class="word_dark">用户名：
<input type="text" name="username" class="login"></td>
        <td width="94" valign="top" class="word_dark"><input name="Submit" type="submit" class="btn_bg" value="登 录"></td>
      </tr>
</table>		
</form>
</body>
</html>
