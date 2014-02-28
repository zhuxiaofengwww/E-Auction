<%
            // First, we must make sure that the user viewing this page is logged in in order to prevent unauthorized access.
            // JSP automatically creates a session object and it will check the session variable "EmailAddress" in the CheckLogin.java page
            // to verify if a person is logged in.
            if (null == session.getAttribute("customer")) {
         %>
                <a href="login.jsp">Sign in</a> or <a href="registerForMan.jsp">register</a>
         <%
            }
             else{
         %>
               &nbsp;
                <%= session.getAttribute("customer")%>

               &nbsp;&nbsp;
               <a href="registrationupdateForMan.jsp">Edit Information</a>
               &nbsp;
               <a href= 'LogOut'>Log Out</a>
<%}

%>