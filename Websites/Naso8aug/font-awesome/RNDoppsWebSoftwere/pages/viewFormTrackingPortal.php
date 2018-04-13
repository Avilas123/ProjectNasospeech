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
							<li>Utilities&nbsp;-&nbsp;Form Tracking</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Form Tracking Module</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<div class="row" style="margin-top:2%;">
					<div class="col-lg-6 col-md-6 col-sm-8 col-xs-12 formTrackingPortal">
						<ul class="list-group">
							<li class="list-group-item"><a href="#">Applied</a></li>
							<li class="list-group-item"><a href="#">Processed</a></li>
							<li class="list-group-item"><a href="#">Approved</a></li>
							<li class="list-group-item"><a href="#">Rejected</a></li>
							<li class="list-group-item"><a href="#">Cancelled</a></li>
							<li class="list-group-item"><a href="#">Form Search</a></li>
						</ul>
					</div>
					<div class="col-lg-6 col-md-6 col-sm-4 col-xs-12 formTrackingPortal">
						<div class="checkbox" style="margin-top:0px;">
								<label><input type="checkbox" value="">Temporary Advance and TA/DA Settlement</label>
						</div>
						<div class="checkbox">
							<label><input type="checkbox" value="">Temporary Advance</label>
						</div>
					</div>
				</div>
				<!--hidden div--><div id="hiddenDiv"></div>
				
			</div>
				<?php include 'include/notificationBlock.php';?>
		</div>
			<!--footer start here-->
				<?php include 'include/footerPage.php';?>
			<!--footer end here-->
	</div>
</body>
</html>