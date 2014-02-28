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
//�˴���Ҫ��?nocache="+new Date().getTime()�����򽫳���������Ա�б����µ����
function showOnline(){
	var loader=new net.AjaxRequest("online.jsp?nocache="+new Date().getTime(),deal_online,onerror,"GET");
}
function showContent(){
	var loader1=new net.AjaxRequest("Messages?action=getMessages&nocache="+new Date().getTime(),deal_content,onerror,"GET");
}
function onerror(){
	alert("�ܱ�Ǹ�����������ִ��󣬵�ǰ���ڽ��رգ�");
	window.opener=null;
	window.close();
}
function deal_online(){
	online.innerHTML=this.req.responseText;
}
function deal_content(){
	var returnValue=this.req.responseText;		//��ȡAjax����ҳ�ķ���ֵ
	var h=returnValue.replace(/\s/g,"");	//ȥ���ַ����е�Unicode�հ׷�
	if(h=="error"){
		//alert("�����˻��Ѿ����ڣ������µ�¼��");
		Exit();
	}else{
		content.innerHTML=sysBBS+returnValue+"</span>";
		document.getElementById('content').scrollTop = document.getElementById('content').scrollHeight*2;	//��������Ϣ����һ��ʱ���������ȷ��͵�������Ϣ����ʾ
	}
}

window.onload=function(){
	showContent();						//��ҳ���������ʾ��������
	showOnline();						//��ҳ���������ʾ������Ա�б�
}
</script>
<script language="javascript">
<!--
	function send(){	//��֤������Ϣ������
		if(form1.to.value==""){
			alert("��ѡ���������");return false;
		}
		if(form1.content1.value==""){
			alert("������Ϣ������Ϊ�գ�");form1.content1.focus();return false;
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
		alert("��ӭ���´ι��٣�");
	}
-->
</script>
<script language="javascript">
function set(selectPerson){	//�Զ�����������
	if(selectPerson!="${username}"){
			form1.to.value=selectPerson;

	}else{
		alert("������ѡ���������");
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
	<div style="height:290px; overflow:hidden" id="content">��������</div>
	</td>  
    <td width="171" valign="top" background="images/right.jpg" id="online" style="padding:5px">������Ա�б�</td>
  </tr>
</table>
<table width="778" height="96" border="0" align="center" cellpadding="0" cellspacing="0" bordercolor="#D6D3CE" background="images/bottom.jpg">
<form action="" name="form1" method="post" >
  <tr>
    <td height="30" align="left">&nbsp;</td>
    <td height="37" align="left"><input name="from" type="hidden" value="${username}">[${username} ]��
      <input name="to" type="text" value="" size="35" readonly="readonly">
����
<select name="face" type="hidden" value="" class="wenbenkuang">
  <option value="΢Ц��" selected>΢Ц</option>
  <option value="Ц�Ǻǵ�">Ц�Ǻ�</option>
  <option value="ͬ���">ͬ��</option>
  <option value="�ź���">�ź�</option>
</select>
<input name="isPrivate" type="hidden" class="noborder" id="isPrivate" value="true" ></td>
    <td width="189" align="left"> &nbsp;&nbsp;������ɫ��
      <select name="color" size="1" class="wenbenkuang" id="select">
        <option selected>Ĭ����ɫ</option>
        <option style="color:#FF0000" value="FF0000">��ɫ</option>
        <option style="color:#0000FF" value="0000ff">��ɫ</option>
        <option style="color:#ff00ff" value="ff00ff">��ɫ</option>
        <option style="color:#009900" value="009900">��ɫ</option>
        <option style="color:#009999" value="009999">��ɫ</option>
        <option style="color:#990099" value="990099">��ɫ</option>
        <option style="color:#990000" value="990000">�ڻ�</option>
        <option style="color:#000099" value="000099">����</option>
        <option style="color:#999900" value="999900">����</option>
        <option style="color:#ff9900" value="ff9900">�ֽ�</option>
        <option style="color:#0099ff" value="0099ff">����</option>
        <option style="color:#9900ff" value="9900ff">����</option>
        <option style="color:#ff0099" value="ff0099">����</option>
        <option style="color:#006600" value="006600">ī��</option>
      </select></td>
    <td width="19" align="left">&nbsp;</td>
  </tr>
  <tr>
    <td width="21" height="30" align="left">&nbsp;</td>
    <td width="549" align="left"><input name="content1" type="text" size="70" onKeyDown="if(event.keyCode==13 && event.ctrlKey){send();}">
      <input name="Submit2" type="button" class="btn_grey" value="����" onClick="send()"></td>
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
