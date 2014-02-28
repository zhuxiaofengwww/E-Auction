<body>
<!--</header>  -->
    <div id="top">

            <div id="topwrapper">
                <div class="container">

                        <div id="logo"><a href="index.jsp"><img src="imagesCL/logo.png"/></a></div>
                        <h1><a href="index.jsp">My E-Auction</a></h1>

                        <div id="user">

                            &nbsp;&nbsp;Welcome!
                        <%@include file ="/inc/cmnWebTop.jsp" %>  

                        </div>

                        <div id="cart"><a href="cart.jsp"><img src="imagesCL/cart.png"/></a></div>
                        <div id="kid"><a href="index.jsp"><img src="imagesCL/kid.png"/></a></div>
                        <ul id="menu">
                        <li><a href="index.jsp" class="but1_active">Home Page</a></li>
                        <li><a href="viewmybiditems.jsp" class="but2">View My Bids</a></li>
                        <li><a href="item.jsp" class="but2">Sell an Item</a></li>
                        <li><a href="viewmysaleitems.jsp" class="but2">View My Items</a></li>
                        <li><a href="help.jsp" class="but2">Service & Help</a></li>
                        <li><a href="about.jsp" class="but2">About Us</a></li>                        
                        </ul>

                </div>

                <div id="welcome">
                    <div class="container">
                        <div id="search">
                            <form name="Search" method="get" action="browseitemlistings.jsp">
                                    <input type=text size=65 name="name" />
                                    <select name="select">
                                    <option value="all" selected="selected">All Categories</option>
                                    <option value="parts">Parts</option>
                                    <option value="books">Books</option>
                                    <option value="business">Business</option>
                                    <option value="clothing">Clothing</option>
                                    <option value="others">Others</option>
                                    </select>
                                    <input type="submit" value="Search"/>
                            </form>
                        </div>
                    </div>
                </div>    
            </div>

    </div>
