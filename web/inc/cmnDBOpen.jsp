
<%
            try {

                //Using a decimal format object to format prices in US dollars
                DecimalFormat currency = new DecimalFormat("$#,##0.00");

                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  // load the driver


                Connection con = null;
                con = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=ISM3232AuctionDB;user=Xiaofeng;password=Xiaofeng;");

                Statement statement = null;
                statement = con.createStatement();

                ResultSet rs = null;//variable to hold the data from the database table         
        %>                 