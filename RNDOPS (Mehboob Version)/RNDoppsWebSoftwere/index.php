<!DOCTYPE html>
<html lang="en">
<head>
  <title>RndOps</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="shortcut icon" href="images/headerImg/Logo_Header.jpg" />
  <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
  <link rel="stylesheet" href="css/indexStyle.css">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.3/css/font-awesome.min.css">
  <script src="js/jquery.min.js"></script>
  <script src="bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container-fluid" style="background-color:#ffffff;position:fixed;width:100%;z-index:999;">
		<div class="row">
			<div class="col-lg-offset-2 col-lg-2 col-md-offset-2 col-md-3 col-sm-3 col-xs-9" style="padding:0px;">
				<img src="images/headerImg/Logo_SignIN.png" class="img-responsive" >
			</div>
			
			<div class="col-lg-1 col-md-1 col-sm-1 col-xs-3"><button type="button" class="navbar-toggle btn btn-link" data-toggle="collapse" data-target="#myNavbar">Login</button></div>
			
			<div class="col-lg-6 col-md-6 col-sm-8 col-xs-12" style="padding:0px;">
				<form class="form-inline collapse navbar-collapse logInForm" id="myNavbar" action="pages/welcome.php" method="post">
					<div class="form-group">
						<input type="text" style="border-radius:0;height:25px;padding-bottom:3px;padding-top:3px;" class="form-control" id="username" placeholder="Username">
					</div>
					<div class="form-group">
						<input type="password" style="border-radius:0;height:25px;" class="form-control" id="pwd" placeholder="Password">
					</div>
					<button type="submit" style="border-radius:0;height:25px;font-size:9px;font-weight: bold;" class="btn btn-success">SIGN IN</button>
				</form>
			</div>
		</div>
	</div>
	<div class="container-fluid" id="bodyPart" style="margin-top:80px;position:absolute;">
		<div class="row">
			<div class="col-lg-offset-2 col-lg-6 col-md-offset-2 col-md-6 col-sm-12 col-xs-12" id="carouselDisplay">
				<!--Carousel Start-->
				<div id="myCarousel" class="carousel slide" data-ride="carousel" data-interval="false">
					<!-- Wrapper for slides -->
					<div class="carousel-inner" role="listbox" >
						<div class="item active">
							<img src="images/carouselImg/img1.jpg" class="img-responsive" id="carouselImg1" alt="img1">
						</div>
						<div class="item">
							<img src="images/carouselImg/img2.jpg" class="img-responsive" id="carouselImg2" alt="img2">
						</div>
						<div class="item">
							<img src="images/carouselImg/img3.jpg" class="img-responsive" id="carouselImg3" alt="img3">
						</div>
						<div class="item">
							<img src="images/carouselImg/img4.jpg" class="img-responsive" id="carouselImg4" alt="img4">
						</div>
					</div>
					<!-- Left and right controls -->
					<a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
						<span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
						<span class="sr-only">Previous</span>
					</a>
					<a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
						<span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
						<span class="sr-only">Next</span>
					</a>
					
					<!--<div id="carouselButtons">
						<button id="playButton" type="button" class="btn btn-default btn-xs">
							<span class="glyphicon glyphicon-play"></span>
						</button>
						<button id="pauseButton" type="button" class="btn btn-default btn-xs">
							<span class="glyphicon glyphicon-pause"></span>
						</button>
					</div>-->
				</div>
				<!--Carousel End-->
				<div class="row" style="margin:0px;padding:0px;">
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="purpose">
						<img src="images/innovation.jpg" class="img-responsive" id="purposeImg">
						<h2>Purpose</h2>
						<div class="purposeTxt">Providing support to the faculty members in the preparation of research proposals to various agencies, conforming to the Institute's guidelines.</div>
						<div class="purposeTxt">Monitoring and tracking of budgets and utilization of funds received from sponsors. Preparation of Utilization Certificates and Statement of Expenditures.</div>
						<div class="purposeTxt">Regulating expenditures and administration of projects (project staff appointment, travel, etc.) on behalf of the Institute</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="research">
						<img src="images/innovation.jpg" class="img-responsive" id="researchImg">
						<h2>Research and Innovation</h2>
						<div id="researchTxt">Research is an integral part of the activities of Indian Institute of Technology Guwahati. The Institute conducts researches within its academic programmes under all the departments and the academic centres. Its faculty members also conduct research projects sponsored by various government agencies and companies. The aims of theses sponsored researches are varied starting from the advancement of theoretical knowledge to development of new technology to solve practical problems.</div>
					</div>
					<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12" id="researchCopy">
						<img src="images/innovation.jpg" class="img-responsive" id="researchCopyImg">
						<h2>Research and Innovation</h2>
						<div id="researchCopyTxt">Research is an integral part of the activities of Indian Institute of Technology Guwahati. The Institute conducts researches within its academic programmes under all the departments and the academic centres. Its faculty members also conduct research projects sponsored by various government agencies and companies. The aims of theses sponsored researches are varied starting from the advancement of theoretical knowledge to development of new technology to solve practical problems.</div>
					</div>
				</div>
			</div>
			<div class="col-lg-2 col-md-2 col-sm-12 col-xs-12" id="event">
				<h2>Events</h2>
				<img src="images/eventImg/img5.jpg" class="img-responsive" id="eventImg1" >
				<div class="hrLine">Advertisement notification for admission to PhD programme for session to start from December 2016 and special PhD Admission Programme Only Under CSIR-JRF Scheme for Department of Chemistry</div>
				<div class="hrLine">FABACTCS2016 - 9th TCS Annual Event & Flow Cytometry Workshop on Flow Application in Basic, Applied & Clinical Biology<br/>03 Nov 2016 - 05 Nov 2016</div>
				<img src="images/eventImg/iitgImg1.jpg" class="img-responsive" id="eventImg2">
				<div class="hrLine">58th Annual Conference of the Indian Society of Labour Economics<br/>24 Nov 2016 - 26 Nov 2016</div>
			</div>
		</div>
			<div class="row" style="margin: 30px 0 0px 0;"><!--footer start here-->
				<div class="col-lg-offset-4 col-lg-4 col-md-offset-2 col-md-7 col-sm-offset-1 col-sm-10 col-xs-12 footerLink">
					<ul>
						<li><a href="#">IITG Intranet</a>&nbsp;|</li>
						<li><a href="#">RnD Section</a>&nbsp;|</li>
						<li><a href="#">Contact Us</a>&nbsp;|</li>
						<!--<li><a href="#">FAQ</a>&nbsp;|</li>-->
						<li><a href="#">Our Team</a></li>
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-lg-offset-4 col-lg-5 col-md-offset-3 col-md-9 col-sm-offset-3 col-sm-9 col-xs-12" style="font-size:11px;font-family: Verdana, Arial, Helvetica;color:#666666;">
					&copy;2016 Indian Institute of Technology Guwahati. For queries contact rndops@iitg.ernet.in
				</div>
			</div><!--footer end here-->
	</div>
	<!--jquery start here-->
	<script>
		$(document).ready(function(){
			/*$('#myCarousel').carousel('pause');
			$('#playButton').click(function () {
				$('#myCarousel').carousel('cycle');
				});
			$('#pauseButton').click(function () {
					$('#myCarousel').carousel('pause');
				});*/
		});
	</script>
</body>
</html>