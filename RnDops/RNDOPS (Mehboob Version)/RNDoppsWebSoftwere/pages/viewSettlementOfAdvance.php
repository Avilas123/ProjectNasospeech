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
							<li>Utilities&nbsp;-&nbsp;Settlement of Advance</li>
						</ul>
					</div>
				</div>
				<div class="row" style="margin:0px;padding:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin:0px;padding:0px;">
						<h3 style="margin:10px 0 2px 0;">Settlement of Temporary Advance</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<div class="row settlementHeading" style="margin:0px;margin-top:1%;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
						<p style="background-color:#83C233;padding:10px;color:#ffffff;margin:0px;">Please Note</p>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<ol>
							<li><p>The settlement against this advance should be submitted within a period of 45 days from the date of advance drawn.</p></li>
							<li><p>The purchase process as placed on the webpage of R&D section (Intranet) may be followed.</p></li>
							<li><p>The item is to be purchased from the local market. The total amount at the time of settlement under no circumstances should exceed the approved amount of advance.</p></li>
							<li><p>Make sure that all purchases are made after approval of advance.</p></li>
						</ol>
					</div>
				</div>
				<div class="row settlementInformation">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<p>No pending settlements found !</p>
						<p>Here display the information about pending information from database !!!</p>
					</div>
				</div>
			</div>
			<?php include 'include/notificationBlock.php'?>
		</div>
		<!--hidden div--><div id="thiddenDiv" style="margin-top:450px;"></div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
	</div>
	
</body>
</html>