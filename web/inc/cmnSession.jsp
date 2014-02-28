<%
                 // First, we must make sure that the user viewing this page is logged in in order to prevent unauthorized access.
                // JSP automatically creates a session object and it will check the session variable "EmailAddress" in the CheckLogin.java page
                // to verify if a person is logged in.
                if (null == session.getAttribute("EmailAddress")) {
                    response.sendRedirect("BadLogin.jsp");
                }  
        %>
