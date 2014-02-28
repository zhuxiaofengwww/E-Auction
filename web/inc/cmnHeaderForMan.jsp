<body>
<!--</header>  -->
    <div id="top">

            <div id="topwrapper">
                <div class="container">

                        <div id="logo"><a href="management.jsp"><img src="images/logo.png"/></a></div>
                        <h1><a href="management.jsp">My E-Auction</a></h1>

                        <div id="user">

                            Welcome!
                        <%@include file ="/inc/cmnWebTopForMan.jsp" %>  

                        </div>

                        <div id="cart"><a href="cart.jsp"><img src="images/cart.png"/></a></div>
                        <div id="kid"><a href="management.jsp"><img src="images/kid.png"/></a></div>
                        <ul id="menu">
                        <li><a href="management.jsp" class="but1_active">Home Page</a></li>
                        <li><a href="viewBidsRecords.jsp" class="but2">Bids Records</a></li>
                        <li><a href="viewSellingRecords.jsp" class="but2">Selling</a></li>
                        <li><a href="viewItemsWarehouse.jsp" class="but2">Warehouse</a></li>
                        <li><a href="viewPersonRecords.jsp" class="but2">personnel</a></li>
                        <li><a href="aboutForMan.jsp" class="but2">About Us</a></li>                        
                        </ul>

                </div>

                <div id="welcome">
                    <div class="container">
                        <div id="search">
                            <form name="Search" method="get" action="Managmentlistings.jsp">
                                    <input type=text size=65 name="ID" />
                                    <select name="select">
                                    <option value="Bid" selected="selected">Bids Records</option>
                                    <option value="Sale">Selling Records</option>
                                    <option value="Item">Items Warehouse</option>
                                    <option value="Person">Personal Records</option>
                                    </select>
                                    <input type="submit" value="Search"/>
                            </form>
                        </div>
                    </div>
                </div>    
            </div>

    </div>
