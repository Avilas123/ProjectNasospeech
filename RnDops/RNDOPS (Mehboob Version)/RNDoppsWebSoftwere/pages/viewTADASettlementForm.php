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
			<div class="col-lg-offset-3 col-lg-6 col-md-offset-3 col-md-6 col-sm-12 col-xs-12 rightSideBody">
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xd-12" style="padding:0px;margin:0px;">
						<ul class="breadcrumb" style="padding:0px;margin:0px;">
							<li><a href="welcome.php">Home</a></li>
							<li>Utilities&nbsp;-&nbsp;TA DA Settlement</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="margin:0px;padding:0px;">
						<h3 style="margin:10px 0 2px 0;">Settlement of TA/DA</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<div class="row settlementHeading" style="margin:0px;margin-top:1%;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;">
						<p style="background-color:#83C233;padding:10px;color:#ffffff;margin:0px;">Please Attach</p>
					</div>
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
						<ol>
							<li><p>Copy of approval of the journey.</p></li>
							<li><p>Tickets, where applicable.</p></li>
							<li><p>Proof of payments in all cases.</p></li>
							<li><p>Copy of Participation Certificate in case of Seminar/ Conference etc.</p></li>
							<li><p>All bills should be signed by the claimant.</p></li>
							<li style="color:red;"><p style="color:red;">Any expenditure in foreign currency should be converted into equivalent Indian Rupees and proof of conversion should be attached.</p></li>
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
		<!--hidden div--><div id="thiddenDiv" style="margin-top:350px;"></div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
	</div>
	
</body>
</html>