<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns:layout>
<head>
  <title>E-Auction Home Page</title>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <link rel="stylesheet" href="CSSCL/reset.css" type="text/css" media="all">
  <link rel="stylesheet" href="CSSCL/style_01.css" type="text/css" media="all">
  <script type="text/javascript" src="js/jquery-1.4.2.min.js" ></script>
  <script type="text/javascript" src="js/cufon-yui.js"></script>
  <script type="text/javascript" src="js/Humanst521_BT_400.font.js"></script>
  <script type="text/javascript" src="js/Humanst521_Lt_BT_400.font.js"></script>
  <script type="text/javascript" src="js/roundabout.js"></script>
  <script type="text/javascript" src="js/roundabout_shapes.js"></script>
  <script type="text/javascript" src="js/gallery_init.js"></script>
  <script type="text/javascript" src="js/cufon-replace.js"></script>
  <!--[if lt IE 7]>
  	<link rel="stylesheet" href="CSSCL/ie/ie6.css" type="text/css" media="all">
  <![endif]-->
</head>

<%@include file ="/inc/cmnHeader.jsp" %>  

  <!-- #gallery-->
  <div id="gallery"align="center">
  	<div class="container">
    	<ul id="myRoundabout">
      	<li><a href="browseitemlistings.jsp?name=all&select=business"><img src="images/slide1.jpg" alt="" /></a></li>
        <li><a href="browseitemlistings.jsp?name=all&select=parts"><img src="images/slide2.jpg" alt="" /></a></li>
        <li><a href="browseitemlistings.jsp?name=all&select=clothing"><img src="images/slide3.jpg" alt="" /></a></li>
        <li><a href="browseitemlistings.jsp?name=all&select=Others"><img src="images/slide4.jpg" alt="" /></a></li>
        <li><a href="browseitemlistings.jsp?name=all&select=books"><img src="images/slide5.jpg" alt="" /></a></li>       
        </ul>
  	</div>
  </div>
  
  <!-- /#gallery -->
<!--<center>--> 

  <div class="main-box">
    <div class="container">
      <div class="inside">
        <div class="wrapper">
                                <div id="colors">
					<div class="color" style="background-color:#828181; margin:auto auto auto 4px ">
                                        </div> 
					<div class="color" style= "background-color:#8455c7">
                                        </div> 
					<div class="color" style= "background-color:#6a815e">
                                        </div> 
					<div class="color" style= "background-color:#4894c7">
                                        </div>                                                             
                                </div>
                                
        			<div id="items">
					<div class="item">
						<img src="images/item1.jpg" alt="" width="202" height="173" /><br />
						<span>$99</span><a href="browseitemlistings.jsp?name=Sofa chair&select=all" class="view">View</a><a href="browseitemlistings.jsp?name=Sofa chair&select=all" class="buy">Bid this Product</a>
					</div>
					<div class="item">
						<img src="images/item2.jpg" alt="" width="202" height="173" /><br />
						<span>$150</span><a href="browseitemlistings.jsp?name=SamSung TV&select=all" class="view">View</a><a href="browseitemlistings.jsp?name=SamSung TV&select=all" class="buy">Bid this Product</a>
					</div>
					<div class="item">
						<img src="images/item3.jpg" alt="" width="202" height="173" /><br />
						<span>$250</span><a href="browseitemlistings.jsp?name=Golden Watch&select=all" class="view">View</a><a href="browseitemlistings.jsp?name=Golden Watch&select=all" class="buy">Bid this Product</a>
					</div>
					<div class="item">
						<img src="images/item4.jpg" alt="" width="202" height="173" /><br />
						<span>$120</span><a href="browseitemlistings.jsp?name=Android Tablet&select=all" class="view">View</a><a href="browseitemlistings.jsp?name=Android Tablet&select=all" class="buy">Bid this Product</a>
					</div>
                                </div>
        </div>
      </div>
    </div>
  </div>


  <script src="CSSCL/commonfooter.js"></script>
  <script type="text/javascript"> Cufon.now(); </script>

</body>
</html>
