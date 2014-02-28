package auctionclasses;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.ListResourceBundle;
import javax.servlet.http.HttpSession;
import java.util.*;
import auctionclasses.FileUpload;

/**
 *
 * @author Preston
 */
@WebServlet(name = "AddItems", urlPatterns = {"/AddItems"})
public class AddItems extends HttpServlet {

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        try {
            HttpSession session = request.getSession();

            // First, we must make sure that the user bidding on the item is logged in.
            // DoBid.java will check the session variable "EmailAddress" in the CheckLogin.java page.
            if (null == session.getAttribute("EmailAddress")) {
                response.sendRedirect("BadLogin.jsp");
            } 
          

            String strItemPrefix = "ITEM";// All assigned item IDs will begin with 'ITEM'.

            int aNumber = (int) (Math.random() * 400 + 100);//generating a random integer to attach to an Item ID
            int bNumber = (int) (Math.random() * 500 * 10);//generating a random integer to attach to an Item ID

            String s3 = Integer.toString(aNumber);
            String s4 = Integer.toString(bNumber);

            String newItemIDNumber = strItemPrefix + s3 + s4;// This item ID number will be used to identify the sale items in the database  

            String strItemName = request.getParameter("txtItemName");
            String strItemDescription = request.getParameter("txtDescription");
            Double strAskingPrice = Double.parseDouble(request.getParameter("txtAskingPrice"));
            String strselItemCategory = request.getParameter("selItemCategory");

            // Checking to see if the asking price from item.jsp is greater than or equal to zero.
            // If it is less than or equal to zero, an error message will be displayed to the user.

            if (strAskingPrice <= 0) {
                response.sendRedirect("ServletAddItemsAskingPrice.jsp");           
            }
           
            Class.forName(Utility.forClass); // Loading the Microsoft SQL Server JDBC 4.0 driver 

            // Establishing a database connection
            Connection con_for_ISM3232AuctionDB = null;
            con_for_ISM3232AuctionDB = DriverManager.getConnection(Utility.connection);

            Date today = new Date();// Getting the current date from the server

            String listingDate;
            listingDate = today.toString();// Converting the current date into a string variable

            Integer intExpirationMonth = Integer.parseInt(request.getParameter("selMonth"));
            String userExpirationMonth = intExpirationMonth.toString();//converting the integer value from the select month item box to a string variable

            Integer intExpirationDay = Integer.parseInt(request.getParameter("selDay"));
            String userExpirationDay = intExpirationDay.toString();//converting the integer value from the select day item box to a string variable

            Integer intExpirationYear = Integer.parseInt(request.getParameter("selYear"));
            String userExpirationYear = intExpirationYear.toString();//converting the integer value from the select year item box to a string variable

            Integer intExpirationHours = Integer.parseInt(request.getParameter("selHour"));
            String userExpirationHours = intExpirationHours.toString();//converting the integer value from the select year item box to a string variable

            Integer intExpirationMinutes = Integer.parseInt(request.getParameter("selMinutes"));
            String userExpirationMinutes = intExpirationMinutes.toString();//converting the integer value from the select year item box to a string variable     


            // Converting the month, date, year, hours, and minutes from the item form to a string value in order to write into the database
            String strExpirationDate = userExpirationMonth + '/' + userExpirationDay + '/' + userExpirationYear + ' ' + userExpirationHours + ':' + userExpirationMinutes;

            Date convertExpirationDateFromForm = new Date();

            // Formatting the user expiration date from item.jsp
            SimpleDateFormat formatterForExpirationDate = new SimpleDateFormat("MM/dd/yyyy HH:mm");

            /*********************************************************************
            // We must make sure that the formatted expiration date is valid
            // If the formatted expiration date that the user entered is invalid,
            // an exception error will occur and be displayed to the user             
             ********************************************************************/
            formatterForExpirationDate.setLenient(false);
            convertExpirationDateFromForm = formatterForExpirationDate.parse(strExpirationDate);// checking to see if the expiration date is valid

            String expirationDate = convertExpirationDateFromForm.toString();

            /*Now we must compare the system date with the expiration date the user entered
            so that the user can not enter an expiration date prior to the listing date.*/

            SimpleDateFormat formatter = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
            Date systemDateToCompare = formatter.parse(listingDate);// variable to hold the formatted system date
            Date expirationDateToCompare = formatter.parse(expirationDate);// variable to hold the formatted expiration date

            Calendar cal1 = Calendar.getInstance();// getting the formatted current system date at the time of the listing
            Calendar cal2 = Calendar.getInstance();// getting the formatted expiration date the user entered from the form

            cal1.setTime(systemDateToCompare); // converting the system date object to a calendar object
            cal2.setTime(expirationDateToCompare);// converting the expiration date object to a calendar object

            /*Comparing the system date calendar object with the expiration date object
            If the expiration date the user entered in the item.jsp is invalid, the user will be notified.*/

            if (cal2.before(cal1)) {
                response.sendRedirect("ServletAddItemsListingDate.jsp");  
            }

            /* If the expiration date object, asking price, and notifying price are valid, then the user will
            be able to insert the item into the database.*/
            String filePath = request.getSession().getServletContext().getRealPath("");//获取该项目的真实路径

            out.println(filePath+"<br/>"); //output 该项目的真实路径 

            String photo=request.getParameter("imgUpload");//获取file控件里的路径（绝对路径） 

            out.println(photo+"<br/>");
            int indexOfLine = photo.lastIndexOf("\\"); 
            long presentMillis=System.currentTimeMillis(); // Converting the current date into a string variable

            String fileName = presentMillis+photo.substring(indexOfLine+1,photo.length());//文件名（含后缀，不包含路径信息） 
            out.println(fileName+"<br/>");

            String destFilePathAndName = filePath+"\\image\\"+fileName;//要保存文件的路径 
            out.println(destFilePathAndName+"<br/>");
            FileUpload.uploadImage(filePath+"\\image\\",photo,destFilePathAndName);//上传图片到目的路径 

            String relativeFilePath = ".\\image\\"+fileName;//用来显示图片的相对路径 


            out.println(destFilePathAndName+"<br/>"); 
            

            if (cal2.after(cal1) && ((strAskingPrice > 0) && (strAskingPrice > 0))) {
                //We will use the Person ID that is stored in the session variable to link the item to the seller.
                String userPersonID = (String) session.getAttribute("PersonID");

                String sqlQuery = "INSERT INTO ITEM VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = con_for_ISM3232AuctionDB.prepareStatement(sqlQuery);

                pst.setString(1, newItemIDNumber);
                pst.setString(2, userPersonID);
                pst.setString(3, strItemName);
                pst.setString(4, strItemDescription);
                pst.setDouble(5, strAskingPrice);                
                pst.setString(6, listingDate);
                pst.setString(7, expirationDate);
                pst.setString(8, strselItemCategory); 
                pst.setString(9, relativeFilePath); 
                pst.executeUpdate();

                response.sendRedirect("ServletAddItemsSuccessfully.jsp"); 

                pst.close();//closing the preparedstatement
            } 


            // close the database connection.        
            con_for_ISM3232AuctionDB.close();

        } catch (ClassNotFoundException e) {
            out.println("Couldn't load database driver: " + e.getMessage());
        } catch (SQLException e) {
            out.println("SQLException caught: " + e.getMessage());
        } catch (Exception e) {
            out.println(e);
            response.sendRedirect("ServletAddItemsException.jsp"); 
        }

    }

// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
