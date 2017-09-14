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
							<li>Notification</li>
						</ul>
					</div>
				</div>
				<div class="row" style="padding:0px;margin:0px;">
					<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12" style="padding:0px;margin:0px;">
						<h3 style="margin:10px 0 2px 0;">Notifications</h3>
					</div>
				</div><hr style="border: 3px solid #8c8c8c;">
				<div class="row" style="margin-top:1%;">
					<div class="panel panel-default" style="margin-left:15px;margin-right:15px;">
						<div class="panel-body" style="gpadding-top:0px;">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-12 col-xs-12">
									<ul id="notificationMenu">
										<li><a href="#">Temp Adv</a></li>
										<li><a href="#">Travel</a></li>
										<li><a href="#">Leave</a></li>
										<li><a href="#">TA-DA Settlment</a></li>
										<li><a href="#">Temp Adv Settlement</a></li>
										<li><a href="#">Rate Contract</a></li>
										<li><a href="#">Monthly Attendance Report</a></li>
										<li><a href="#">Adhoc Requirement</a></li>
										<li><a href="#" class="lastChild">Selection Committee Report </a></li>
									</ul>
								</div>
								<div class="col-lg-8 col-md-8 col-sm-12 col-xs-12 table-responsive">
									<table class="table" style="color:#595959;margin:0px;">
										<thead>
											<tr id="noticeHeading">
												<th>Type</th>
												<th>Amount</th>
												<th>Project</th>
												<th>Status</th>
												<th>Approval Remark</th>
												<th>View Form</th>
											</tr>
										</thead>
									</table>
								</div>
							</div>
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