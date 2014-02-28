<%@page contentType="text/html" pageEncoding="GBK" %>
<%@ include file="safe.jsp"%>
<html>
<head>
<title>My E-Auction Chatting Room</title>
<link href="CSSCH/style.css" rel="stylesheet">
<script language="javascript" src="AJAX/AjaxRequest.js"></script>
<script language="javascript">
window.setInterval("showContent();",1000);
window.setInterval("showOnline();",10000);
var sysBBS="<span style='font-size:14px; line-height:30px;'>Welcome to My E-Auction.</span><br><span style='line-height:22px;'>";
//此处需要加?nocache="+new Date().getTime()，否则将出现在线人员列表不更新的情况
function showOnline(){
	var loader=new net.AjaxRequest("online.jsp?nocache="+new Date().getTime(),deal_online,onerror,"GET");
}
function showContent(){
	var loader1=new net.AjaxRequest("Messages?action=getMessages&nocache="+new Date().getTime(),deal_content,onerror,"GET");
}
function onerror(){
	alert("很抱歉，服务器出现错误，当前窗口将关闭！");
	window.opener=null;
	window.close();
}
function deal_online(){
	online.innerHTML=this.req.responseText;
}
function deal_content(){
	var returnValue=this.req.responseText;		//获取Ajax处理页的返回值
	var h=returnValue.replace(/\s/g,"");	//去除字符串中的Unicode空白符
	if(h=="error"){
		//alert("您的账户已经过期，请重新登录！");
		Exit();
	}else{
		content.innerHTML=sysBBS+returnValue+"</span>";
		document.getElementById('content').scrollTop = document.getElementById('content').scrollHeight*2;	//当聊天信息超过一屏时，设置最先发送的聊天信息不显示
	}
}

window.onload=function(){
	showContent();						//当页面载入后显示聊天内容
	showOnline();						//当页面载入后显示在线人员列表
}
</script>
<script language="javascript">
<!--
	function send(){	//验证聊天信息并发送
		if(form1.to.value==""){
			alert("请选择聊天对象！");return false;
		}
		if(form1.content1.value==""){
			alert("发送信息不可以为空！");form1.content1.focus();return false;
		}
                isPrivate="true";
//		if(form1.isPrivate.checked){
//			isPrivate="true";
//		}else{
//			isPrivate="false";
//		}
		var param="from="+form1.from.value+"&isPrivate="+isPrivate+"&face="+
		form1.face.value+"&color="+form1.color.value+"&to="+form1.to.value+"&content="+	form1.content1.value;
                var loader=new net.AjaxRequest("Messages?action=sendMessage",deal_send,onerror,"POST",param);

	}
function deal_send(){
content.innerHTML=sysBBS+this.req.responseText+"</span>";
}
	function Exit(){
		window.location.href="leave.jsp";
		alert("欢迎您下次光临！");
	}
-->
</script>
<script language="javascript">
function set(selectPerson){	//自动添加聊天对象
	if(selectPerson!="${username}"){
			form1.to.value=selectPerson;

	}else{
		alert("请重新选择聊天对象！");
	}
}

</script>
</head>
<body>
<table width="778" height="157" border="0" align="center" cellpadding="0" cellspacing="0" background="images/top.jpg">
  <tr>
    <td>&nbsp;</td>
  </tr>
</table>
<table width="778" height="276" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="599"  height="200" valign="top" bgcolor="#fcf5eb" style="padding:5px; ">
	<div style="height:290px; overflow:hidden" id="content">聊天内容</div>
	</td>  
    <td width="171" valign="top" background="images/right.jpg" id="online" style="padding:5px">在线人员列表</td>
  </tr>
</table>
<table width="778" height="96" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#D6D3CE" background="images/bottom.jpg">
<form action="" name="form1" method="post" >
  <tr>
    <td height="30" align="left">&nbsp;</td>
    <td height="37" align="left"><input name="from" type="hidden" value="${username}">[${username} ]对
      <input name="to" type="text" value="" size="35" readonly="readonly">
表情
<select name="face" type="hidden" value="" class="wenbenkuang">
  <option value="微笑着" selected>微笑</option>
  <option value="笑呵呵地">笑呵呵</option>
  <option value="同情的">同情</option>
  <option value="遗憾的">遗憾</option>
</select>
<input name="isPrivate" type="hidden" class="noborder" id="isPrivate" value="true" ></td>
    <td width="189" align="left"> &nbsp;&nbsp;字体颜色：
      <select name="color" size="1" class="wenbenkuang" id="select">
        <option selected>默认颜色</option>
        <option style="color:#FF0000" value="FF0000">红色</option>
        <option style="color:#0000FF" value="0000ff">蓝色</option>
        <option style="color:#ff00ff" value="ff00ff">桃色</option>
        <option style="color:#009900" value="009900">绿色</option>
        <option style="color:#009999" value="009999">青色</option>
        <option style="color:#990099" value="990099">紫色</option>
        <option style="color:#990000" value="990000">黑灰</option>
        <option style="color:#000099" value="000099">深蓝</option>
        <option style="color:#999900" value="999900">卡其</option>
        <option style="color:#ff9900" value="ff9900">镏金</option>
        <option style="color:#0099ff" value="0099ff">碧绿</option>
        <option style="color:#9900ff" value="9900ff">蓝紫</option>
        <option style="color:#ff0099" value="ff0099">明紫</option>
        <option style="color:#006600" value="006600">墨绿</option>
      </select></td>
    <td width="19" align="left">&nbsp;</td>
  </tr>
  <tr>
    <td width="21" height="30" align="left">&nbsp;</td>
    <td width="549" align="left"><input name="content1" type="text" size="70" onKeyDown="if(event.keyCode==13 && event.ctrlKey){send();}">
      <input name="Submit2" type="button" class="btn_grey" value="发送" onClick="send()"></td>
    <td align="right"><input name="button_exit" type="button" class="btn_grey" value="Exit" onClick="Exit()"></td>
    <td align="center">&nbsp;</td>
  </tr>
  <tr>
    <td height="30" align="left">&nbsp;</td>
    <td colspan="2" align="center" class="word_dark">&nbsp;All CopyRights  reserved Weifeng Guo and Xiaofeng Zhu</td>
    <td align="center">&nbsp;</td>
  </tr>
</form>
</table>
</body>
</html>
