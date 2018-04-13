<!DOCTYPE html>
<html lang="en">
<head>
  <title>RndOps</title>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <?php include 'include/linkScript.php'?>
</head>
<body>
	<?php include 'include/headerPage.php'?>
	<div class="container-fluid" id="bodyPart">
		<div class="row">
			<?php include 'include/accordianPage.php'?>
			<div class="col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 col-sm-12 col-xs-12">
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xd-12" style="padding:0px;margin:0px;">
						<ul class="breadcrumb" style="padding:0px;margin:0px;">
							<li><a href="welcome.php">Home</a></li>
							<li>Utilities&nbsp;-&nbsp;Mark Attendance</li>
						</ul>
					</div>
				</div>
				<!-- Responsive calendar - START -->
					<div class="responsive-calendar">
							<div class="row">
								<div class="col-lg-8 col-md-6 col-sm-6 col-xs-3">
									<span data-head-month style="font-size:20px;"></span><br/><span data-head-year style="font-size:14px;"></span>
								</div>
								<div class="col-lg-4 col-md-6 col-sm-6 col-xs-9">
									<div class="btn-group btn-block"><span style="float:right;">
										<span class="btn btn-default" data-go="prev">
											<i class="fa fa-angle-left" aria-hidden="true"></i>
											<i class="fa fa-angle-left" aria-hidden="true"></i>&nbsp;&nbsp;Prev
										</span>
										<span class="btn btn-default" data-go="next">Next&nbsp;&nbsp;
											<i class="fa fa-angle-right" aria-hidden="true"></i>
											<i class="fa fa-angle-right" aria-hidden="true"></i>
										</span></span>
									</div>
								</div>
							</div>
							<div class="controls">
								<div class="day-headers" style="kpadding:5px 0;">
									<div class="day header">Mon</div>
									<div class="day header">Tue</div>
									<div class="day header">Wed</div>
									<div class="day header">Thu</div>
									<div class="day header">Fri</div>
									<div class="day header">Sat</div>
									<div class="day header">Sun</div>
								</div>
						</div>
        
						<div class="days" data-group="days" style="hborder:1px solid #bfbfbf;">
          
						</div>
					</div>
				<!-- Responsive calendar - END -->
			</div>
			<?php include 'include/notificationBlock.php'?>
		</div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
	</div>
</body>
</html>