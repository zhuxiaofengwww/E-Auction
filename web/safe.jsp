<%@page contentType="text/html" pageEncoding="GBK" %>
<%if (null==session.getAttribute("username") || "".equals(session.getAttribute("username"))){
	out.println("<script language='javascript'>alert('�����˻��Ѿ����ڣ������µ�¼!');window.location.href='test.jsp';</script>");
	return;
}%>
