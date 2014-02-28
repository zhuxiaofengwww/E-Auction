package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.Vector _jspx_dependants;

  static {
    _jspx_dependants = new java.util.Vector(2);
    _jspx_dependants.add("/inc/cmnHeader.jsp");
    _jspx_dependants.add("/inc/cmnWebTop.jsp");
  }

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public Object getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html xmlns:layout>\r\n");
      out.write("<head>\r\n");
      out.write("  <title>E-Auction Home Page</title>\r\n");
      out.write("  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"CSSCL/reset.css\" type=\"text/css\" media=\"all\">\r\n");
      out.write("  <link rel=\"stylesheet\" href=\"CSSCL/style_01.css\" type=\"text/css\" media=\"all\">\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/jquery-1.4.2.min.js\" ></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/cufon-yui.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/Humanst521_BT_400.font.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/Humanst521_Lt_BT_400.font.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/roundabout.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/roundabout_shapes.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/gallery_init.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\" src=\"js/cufon-replace.js\"></script>\r\n");
      out.write("  <!--[if lt IE 7]>\r\n");
      out.write("  \t<link rel=\"stylesheet\" href=\"CSSCL/ie/ie6.css\" type=\"text/css\" media=\"all\">\r\n");
      out.write("  <![endif]-->\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body>\n");
      out.write("<!--</header>  -->\n");
      out.write("    <div id=\"top\">\n");
      out.write("\n");
      out.write("            <div id=\"topwrapper\">\n");
      out.write("                <div class=\"container\">\n");
      out.write("\n");
      out.write("                        <div id=\"logo\"><a href=\"index.jsp\"><img src=\"images/logo.png\"/></a></div>\n");
      out.write("                        <h1><a href=\"index.jsp\">My E-Auction</a></h1>\n");
      out.write("\n");
      out.write("                        <div id=\"user\">\n");
      out.write("\n");
      out.write("                            &nbsp;&nbsp;Welcome!\n");
      out.write("                        ");

            // First, we must make sure that the user viewing this page is logged in in order to prevent unauthorized access.
            // JSP automatically creates a session object and it will check the session variable "EmailAddress" in the CheckLogin.java page
            // to verify if a person is logged in.
            if (null == session.getAttribute("customer")) {
         
      out.write("\n");
      out.write("                <a href=\"login.jsp\">Sign in</a> or <a href=\"register.jsp\">register</a>\n");
      out.write("         ");

            }
             else{
         
      out.write("\n");
      out.write("               &nbsp;\n");
      out.write("                ");
      out.print( session.getAttribute("customer"));
      out.write("\n");
      out.write("\n");
      out.write("               &nbsp;&nbsp;\n");
      out.write("               <a href=\"registrationupdateform.jsp\">Edit Information</a>\n");
      out.write("               &nbsp;\n");
      out.write("               <a href= 'LogOut'>Log Out</a>\n");
}


      out.write("  \n");
      out.write("\n");
      out.write("                        </div>\n");
      out.write("\n");
      out.write("                        <div id=\"cart\"><a href=\"cart.jsp\"><img src=\"images/cart.png\"/></a></div>\n");
      out.write("                        <div id=\"kid\"><a href=\"index.jsp\"><img src=\"images/kid.png\"/></a></div>\n");
      out.write("                        <ul id=\"menu\">\n");
      out.write("                        <li><a href=\"index.jsp\" class=\"but1_active\">Home Page</a></li>\n");
      out.write("                        <li><a href=\"viewmybiditems.jsp\" class=\"but2\">View My Bids</a></li>\n");
      out.write("                        <li><a href=\"item.jsp\" class=\"but2\">Sell an Item</a></li>\n");
      out.write("                        <li><a href=\"viewmysaleitems.jsp\" class=\"but2\">View My Items</a></li>\n");
      out.write("                        <li><a href=\"help.jsp\" class=\"but2\">Service & Help</a></li>\n");
      out.write("                        <li><a href=\"about.jsp\" class=\"but2\">About Us</a></li>                        \n");
      out.write("                        </ul>\n");
      out.write("\n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("                <div id=\"welcome\">\n");
      out.write("                    <div class=\"container\">\n");
      out.write("                        <div id=\"search\">\n");
      out.write("                            <form name=\"Search\" method=\"get\" action=\"browseitemlistings.jsp\">\n");
      out.write("                                    <input type=text size=65 name=\"name\" />\n");
      out.write("                                    <select name=\"select\">\n");
      out.write("                                    <option value=\"all\" selected=\"selected\">All Categories</option>\n");
      out.write("                                    <option value=\"parts\">Parts</option>\n");
      out.write("                                    <option value=\"books\">Books</option>\n");
      out.write("                                    <option value=\"business\">Business</option>\n");
      out.write("                                    <option value=\"clothing\">Clothing</option>\n");
      out.write("                                    <option value=\"others\">Others</option>\n");
      out.write("                                    </select>\n");
      out.write("                                    <input type=\"submit\" value=\"Search\"/>\n");
      out.write("                            </form>\n");
      out.write("                        </div>\n");
      out.write("                    </div>\n");
      out.write("                </div>    \n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("    </div>\n");
      out.write("  \r\n");
      out.write("\r\n");
      out.write("  <!-- #gallery-->\r\n");
      out.write("  <div id=\"gallery\"align=\"center\">\r\n");
      out.write("  \t<div class=\"container\">\r\n");
      out.write("    \t<ul id=\"myRoundabout\">\r\n");
      out.write("      \t<li><a href=\"browseitemlistings.jsp?name=all&select=business\"><img src=\"images/slide1.jpg\" alt=\"\" /></a></li>\r\n");
      out.write("        <li><a href=\"browseitemlistings.jsp?name=all&select=parts\"><img src=\"images/slide2.jpg\" alt=\"\" /></a></li>\r\n");
      out.write("        <li><a href=\"browseitemlistings.jsp?name=all&select=clothing\"><img src=\"images/slide3.jpg\" alt=\"\" /></a></li>\r\n");
      out.write("        <li><a href=\"browseitemlistings.jsp?name=all&select=Others\"><img src=\"images/slide4.jpg\" alt=\"\" /></a></li>\r\n");
      out.write("        <li><a href=\"browseitemlistings.jsp?name=all&select=books\"><img src=\"images/slide5.jpg\" alt=\"\" /></a></li>       \r\n");
      out.write("        </ul>\r\n");
      out.write("  \t</div>\r\n");
      out.write("  </div>\r\n");
      out.write("  \r\n");
      out.write("  <!-- /#gallery -->\r\n");
      out.write("<!--<center>--> \r\n");
      out.write("\r\n");
      out.write("  <div class=\"main-box\">\r\n");
      out.write("    <div class=\"container\">\r\n");
      out.write("      <div class=\"inside\">\r\n");
      out.write("        <div class=\"wrapper\">\r\n");
      out.write("                                <div id=\"colors\">\r\n");
      out.write("\t\t\t\t\t<div class=\"color\" style=\"background-color:#828181; margin:auto auto auto 4px \">\r\n");
      out.write("                                        </div> \r\n");
      out.write("\t\t\t\t\t<div class=\"color\" style= \"background-color:#8455c7\">\r\n");
      out.write("                                        </div> \r\n");
      out.write("\t\t\t\t\t<div class=\"color\" style= \"background-color:#6a815e\">\r\n");
      out.write("                                        </div> \r\n");
      out.write("\t\t\t\t\t<div class=\"color\" style= \"background-color:#4894c7\">\r\n");
      out.write("                                        </div>                                                             \r\n");
      out.write("                                </div>\r\n");
      out.write("                                \r\n");
      out.write("        \t\t\t<div id=\"items\">\r\n");
      out.write("\t\t\t\t\t<div class=\"item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"images/item1.jpg\" alt=\"\" width=\"202\" height=\"173\" /><br />\r\n");
      out.write("\t\t\t\t\t\t<span>$99</span><a href=\"browseitemlistings.jsp?name=Sofa chair&select=all\" class=\"view\">View</a><a href=\"browseitemlistings.jsp?name=Sofa chair&select=all\" class=\"buy\">Bid this Product</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"images/item2.jpg\" alt=\"\" width=\"202\" height=\"173\" /><br />\r\n");
      out.write("\t\t\t\t\t\t<span>$150</span><a href=\"browseitemlistings.jsp?name=SamSung TV&select=all\" class=\"view\">View</a><a href=\"browseitemlistings.jsp?name=SamSung TV&select=all\" class=\"buy\">Bid this Product</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"images/item3.jpg\" alt=\"\" width=\"202\" height=\"173\" /><br />\r\n");
      out.write("\t\t\t\t\t\t<span>$250</span><a href=\"browseitemlistings.jsp?name=Golden Watch&select=all\" class=\"view\">View</a><a href=\"browseitemlistings.jsp?name=Golden Watch&select=all\" class=\"buy\">Bid this Product</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("\t\t\t\t\t<div class=\"item\">\r\n");
      out.write("\t\t\t\t\t\t<img src=\"images/item4.jpg\" alt=\"\" width=\"202\" height=\"173\" /><br />\r\n");
      out.write("\t\t\t\t\t\t<span>$120</span><a href=\"browseitemlistings.jsp?name=Android Tablet&select=all\" class=\"view\">View</a><a href=\"browseitemlistings.jsp?name=Android Tablet&select=all\" class=\"buy\">Bid this Product</a>\r\n");
      out.write("\t\t\t\t\t</div>\r\n");
      out.write("                                </div>\r\n");
      out.write("        </div>\r\n");
      out.write("      </div>\r\n");
      out.write("    </div>\r\n");
      out.write("  </div>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("  <script src=\"CSSCL/commonfooter.js\"></script>\r\n");
      out.write("  <script type=\"text/javascript\"> Cufon.now(); </script>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
