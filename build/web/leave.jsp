<%@page contentType="text/html" pageEncoding="GBK" %>

<%
session.invalidate();
response.sendRedirect("index.jsp");
%>